package sample.services;

import sample.entities.FactoryEntity;
import sample.entities.OrderingEntity;
import sample.entities.ProductEntity;
import sample.repositories.FactoryRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FactoryService implements FactoryServiceInterface{

    @Autowired(required = true)
    private FactoryRepository repository;

    @Autowired(required = true)
    private ProductService productService;

    @Override
    public FactoryEntity findEntityById(int id) {
        FactoryEntity factoryEntity = repository.findById(id).get();
        return factoryEntity;
    }

    public FactoryEntity findEntityByName(String name) {
        FactoryEntity factoryEntity = repository.getFactoryEntityByName(name);
        return factoryEntity;
    }

    public String shortestWay(int origId, int destId) {
        return repository.getShortestWay(origId, destId);
    }

    public int shortestWayWeight(int origId, int destId) {
        return repository.getShortestWayWeight(origId, destId);
    }

    public List<FactoryEntity> findEntitiesByProductIdWithoutFactoryId(int productId, int factoryId) {
        return repository.getFactoryEntityByProductIdWithoutFactoryId(productId, factoryId);
    }

    @Override
    public void delete(FactoryEntity factoryEntity) {
        repository.delete(factoryEntity);
    }

    public List<FactoryEntity> findAllEntities() {
        return repository.findAllEntities();
    }

    @Override
    public void save(FactoryEntity factoryEntity) {
        repository.save(factoryEntity);
    }

    public void saveNewEntity(FactoryEntity factoryEntity) {
        int k = 1;
        List<FactoryEntity> factoryEntities = repository.findAllEntities();
        //System.out.println("SIZE " + factoryEntities.size());
        for (FactoryEntity factoryEntity1: factoryEntities) {
            if (factoryEntity1.getId() > k) {
                factoryEntity.setId(k);
                save(factoryEntity);
                return;
            }
            k++;
        }
        //System.out.println("K= " + k);
        factoryEntity.setId(k);
        save(factoryEntity);
        return;
    }

}
