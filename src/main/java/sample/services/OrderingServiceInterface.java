package sample.services;

import sample.entities.OrderingEntity;

import java.util.List;

public interface OrderingServiceInterface {

    OrderingEntity findEntityById(int id);

    public List<OrderingEntity> findEntitiesByFactoryIdAccepted(int idFactory);

    public List<OrderingEntity> findEntitiesByFactoryIdUnaccepted(int idFactory);

    public List<OrderingEntity> findEntitiesByTransporterIdAccepted(int idTransporter);

    public List<OrderingEntity> findEntitiesByTransporterIdUnaccepted(int idTransporter);

    public List<OrderingEntity> findEntitiesWithoutIdTransporterUnaccepted(int idTransporter);

    public void saveNewEntity(OrderingEntity orderingEntity);

    public void save(OrderingEntity orderingEntity);

    public List<OrderingEntity> findEntitiesByIdTransporter(int idTransporter);

    public List<OrderingEntity> findEntitiesByIdFactory(int idFactory);

    void deleteById(int id);
}
