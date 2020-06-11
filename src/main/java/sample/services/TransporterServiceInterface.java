package sample.services;

import sample.entities.TransporterEntity;

import java.util.List;

public interface TransporterServiceInterface {

    TransporterEntity findEntityById(int id);

    public void saveNewEntity(TransporterEntity transporterEntity);

    public void save(TransporterEntity TransporterEntity);

    public void delete(TransporterEntity TransporterEntity);

    public List<TransporterEntity> findAllEntities();

    TransporterEntity findEntityByName(String name);
}
