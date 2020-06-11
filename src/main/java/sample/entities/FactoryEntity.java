package sample.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "factory", schema = "maindb", catalog = "")
public class FactoryEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "exportid", nullable = false)
    private int exportId;

    public int getExportId() {
        return exportId;
    }

    public void setExportId(int exportId) {
        this.exportId= exportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactoryEntity that = (FactoryEntity) o;
        return id == that.id &&
                exportId == that.exportId &&
                name.equals(that.name) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, exportId);
    }

    /*
    @ManyToOne(targetEntity=ProductEntity.class, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="exportid",nullable = false)
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product=product;
    }

    @OneToMany(targetEntity=OrderingEntity.class, fetch = FetchType.EAGER, mappedBy = "factory")
    private Set<OrderingEntity> orderings;

    public Set<OrderingEntity> getOrderings(){
        return orderings;
    };

    public void setOrderings(Set<OrderingEntity>orderings){
        this.orderings=orderings;
    }

 */

}
