package sample.services;

import sample.entities.ProductEntity;

import java.util.List;

public interface ProductServiceInterface {

    ProductEntity findEntityById(int id);

    public List<ProductEntity> findAllEntities();

    public ProductEntity findEntityByName(String name);

    public void save(ProductEntity productEntity);

    public void delete(ProductEntity productEntity);

    public void saveNewEntity(ProductEntity productEntity);


}
