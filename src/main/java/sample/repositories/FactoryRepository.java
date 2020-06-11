package sample.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.entities.FactoryEntity;


import java.util.List;

@Repository
public interface FactoryRepository extends CrudRepository<FactoryEntity, Integer>{
    @Query("SELECT e FROM FactoryEntity e WHERE e.name = :username")
    FactoryEntity getFactoryEntityByName(@Param("username") String username);

    @Query("SELECT e FROM FactoryEntity e WHERE e.exportId = :exportId AND e.id <> :factoryId")
    List<FactoryEntity> getFactoryEntityByProductIdWithoutFactoryId(@Param("exportId") int exportId, @Param("factoryId") int factoryId);

    @Query(value = "CALL maindb.Shortest_Way(:origidIN, :destidIN);", nativeQuery = true)
    String getShortestWay(@Param("origidIN") int origidIN, @Param("destidIN") int destidIN);


    @Query(value = "CALL maindb.Shortest_Way_Weight(:origidIN, :destidIN);", nativeQuery = true)
    int getShortestWayWeight(@Param("origidIN") int origidIN, @Param("destidIN") int destidIN);

    @Query("SELECT e FROM FactoryEntity e")
    List<FactoryEntity> findAllEntities();


}
