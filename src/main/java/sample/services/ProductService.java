package sample.services;

import sample.entities.OrderingEntity;
import sample.entities.ProductEntity;
import sample.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired(required = true)
    private ProductRepository repository;

    @Override
    public ProductEntity findEntityById(int id) {
        ProductEntity productEntity = repository.findById(id).get();
        return productEntity;
    }


    public List<ProductEntity> findAllEntities() {
        return repository.findAllEntities();
    }

    public ProductEntity findEntityByName(String name) {
        return repository.findProductEntityByName(name);
    }

    @Override
    public void save(ProductEntity productEntity) {
        repository.save(productEntity);
    }

    @Override
    public void delete(ProductEntity productEntity) {
        repository.delete(productEntity);
    }

    public void saveNewEntity(ProductEntity productEntity) {
        int k = 1;
        List<ProductEntity> productEntities = repository.findAllEntities();
        for (ProductEntity productEntity1 : productEntities) {
            if (productEntity1.getId() > k) {
                productEntity.setId(k);
                save(productEntity);
                return;
            }
            k++;
        }
        productEntity.setId(k);
        save(productEntity);
        return;
    }
}
