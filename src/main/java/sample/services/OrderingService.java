package sample.services;

import sample.entities.OrderingEntity;
import sample.repositories.OrderingRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderingService implements OrderingServiceInterface {

    @Autowired(required = true)
    private OrderingRepository repository;

    @Override
    public OrderingEntity findEntityById(int id) {
        OrderingEntity orderingEntity = repository.findById(id).get();
        return orderingEntity;
    }

    public List<OrderingEntity> findEntitiesByFactoryIdAccepted(int idFactory) {
        return repository.findOrderingEntitiesByIdFactoryAndAcceptedTrue(idFactory);
    }

    public List<OrderingEntity> findEntitiesByFactoryIdUnaccepted(int idFactory) {
        return repository.findOrderingEntitiesByIdFactoryAndAcceptedFalse(idFactory);
    }

    public List<OrderingEntity> findEntitiesByTransporterIdAccepted(int idTransporter) {
        return repository.findOrderingEntitiesByIdTransporterAndAcceptedTrue(idTransporter);
    }

    public List<OrderingEntity> findEntitiesByTransporterIdUnaccepted(int idTransporter) {
        return repository.findOrderingEntitiesByIdTransporterAndAcceptedFalse(idTransporter);
    }

    public List<OrderingEntity> findEntitiesWithoutIdTransporterUnaccepted(int idTransporter) {
        return repository.findAllOrderingEntitiesWithoutIdTransporterAndAcceptedFalse(idTransporter);
    }

    public List<OrderingEntity> findEntitiesByIdTransporter(int idTransporter) {
        return repository.findAllOrderingEntitiesByIdTransporter(idTransporter);
    }

    public List<OrderingEntity> findEntitiesByIdFactory(int idFactory) {
        return repository.findAllOrderingEntitiesByIdFactory(idFactory);

    }


    @Override
    public void save(OrderingEntity orderingEntity) {
        repository.save(orderingEntity);
    }

    public void saveNewEntity(OrderingEntity orderingEntity) {
        int k = 1;
        List<OrderingEntity> orderingEntities = repository.findAllEntities();
        for (OrderingEntity orderingEntity1 : orderingEntities) {
            if (orderingEntity1.getId() > k) {
                orderingEntity.setId(k);
                save(orderingEntity);
                return;
            }
            k++;
        }
        orderingEntity.setId(k);
        save(orderingEntity);
        return;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
