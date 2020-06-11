package sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entities.AdminEntity;
import sample.repositories.AdminRepository;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired(required = true)
    private AdminRepository repository;

    @Override
    public AdminEntity findEntityById(int id) {
        AdminEntity adminEntity = repository.findById(id).get();
        return adminEntity;
    }

    public AdminEntity findEntityByName(String name) {
        AdminEntity adminEntity = repository.getAdminEntityByName(name);
        return adminEntity;
    }

}
