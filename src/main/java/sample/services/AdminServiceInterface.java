package sample.services;

import sample.entities.AdminEntity;

public interface AdminServiceInterface {
    public AdminEntity findEntityById(int id);

    public AdminEntity findEntityByName(String name);
}
