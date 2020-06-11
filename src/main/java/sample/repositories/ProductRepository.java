package sample.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.entities.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{
    @Query("SELECT e FROM ProductEntity e ORDER BY e.id")
    List<ProductEntity> findAllEntities();

    @Query("SELECT e FROM ProductEntity e WHERE e.name = :name")
    ProductEntity findProductEntityByName(@Param("name") String name);
}
