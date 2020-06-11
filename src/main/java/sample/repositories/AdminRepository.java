package sample.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sample.entities.AdminEntity;
import sample.entities.FactoryEntity;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
    @Query("SELECT e FROM AdminEntity e WHERE e.username = :username")
    AdminEntity getAdminEntityByName(@Param("username") String username);
}
