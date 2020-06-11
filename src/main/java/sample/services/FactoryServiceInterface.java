package sample.services;

import sample.entities.FactoryEntity;

import java.util.List;

public interface FactoryServiceInterface {
    FactoryEntity findEntityById(int id);

    public void save(FactoryEntity factoryEntity);

    public void delete(FactoryEntity factoryEntity);

    public List<FactoryEntity> findAllEntities();


    public String shortestWay(int origId, int destId);

    public int shortestWayWeight(int origId, int destId);

    public List<FactoryEntity> findEntitiesByProductIdWithoutFactoryId(int productId, int factoryId);

    FactoryEntity findEntityByName(String name);

    public void saveNewEntity(FactoryEntity factoryEntity);
}
