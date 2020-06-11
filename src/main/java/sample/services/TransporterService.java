package sample.services;

import sample.entities.ProductEntity;
import sample.entities.TransporterEntity;
import sample.repositories.TransporterRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TransporterService implements TransporterServiceInterface{

    @Autowired(required = true)
    private TransporterRepository repository;

    @Override
    public TransporterEntity findEntityById(int id) {
        TransporterEntity TransporterEntity = repository.findById(id).get();
        return TransporterEntity;
    }

    public TransporterEntity findEntityByName(String name) {
        TransporterEntity transporterEntity = repository.getTransporterEntityByName(name);
        return transporterEntity;
    }

    @Override
    public void delete(TransporterEntity TransporterEntity) {
        repository.delete(TransporterEntity);
    }

    public List<TransporterEntity> findAllEntities() {
        return repository.findAllEntities();
    }

    @Override
    public void save(TransporterEntity TransporterEntity) {
        repository.save(TransporterEntity);
    }

    public void saveNewEntity(TransporterEntity transporterEntity) {
        int k = 1;
        List<TransporterEntity> transporterEntities = repository.findAllEntities();
        for (TransporterEntity transporterEntity1 : transporterEntities) {
            if (transporterEntity1.getId() > k) {
                transporterEntity.setId(k);
                save(transporterEntity);
                return;
            }
            k++;
        }
        transporterEntity.setId(k);
        save(transporterEntity);
        return;
    }
}
