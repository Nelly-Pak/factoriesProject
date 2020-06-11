package sample.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.entities.OrderingEntity;

import java.util.List;

@Repository
public interface OrderingRepository extends CrudRepository<OrderingEntity, Integer>{
    @Query("SELECT e FROM OrderingEntity e WHERE e.idFactory = :idFactory AND e.accepted = true")
    List<OrderingEntity> findOrderingEntitiesByIdFactoryAndAcceptedTrue(@Param("idFactory") int idFactory);
    @Query("SELECT e FROM OrderingEntity e WHERE e.idFactory = :idFactory AND e.accepted = false")
    List<OrderingEntity> findOrderingEntitiesByIdFactoryAndAcceptedFalse(@Param("idFactory") int idFactory);

    @Query("SELECT e FROM OrderingEntity e WHERE e.idTransporter = :idTransporter AND e.accepted = true")
    List<OrderingEntity> findOrderingEntitiesByIdTransporterAndAcceptedTrue(@Param("idTransporter") int idTransporter);
    @Query("SELECT e FROM OrderingEntity e WHERE e.idTransporter = :idTransporter AND e.accepted = false")
    List<OrderingEntity> findOrderingEntitiesByIdTransporterAndAcceptedFalse(@Param("idTransporter") int idTransporter);

    @Query("SELECT e FROM OrderingEntity e WHERE (e.idTransporter <> :idTransporter OR e.idTransporter IS NULL) AND e.accepted = false ")
    List<OrderingEntity> findAllOrderingEntitiesWithoutIdTransporterAndAcceptedFalse(@Param("idTransporter") int idTransporter);

    @Query("SELECT e FROM OrderingEntity e WHERE (e.idTransporter = :idTransporter)")
    List<OrderingEntity> findAllOrderingEntitiesByIdTransporter(@Param("idTransporter") int idTransporter);
    @Query("SELECT e FROM OrderingEntity e WHERE (e.idFactory = :idFactory)")
    List<OrderingEntity> findAllOrderingEntitiesByIdFactory(@Param("idFactory") int idFactory);


    @Query("SELECT e FROM OrderingEntity e")
    List<OrderingEntity> findAllEntities();




}
