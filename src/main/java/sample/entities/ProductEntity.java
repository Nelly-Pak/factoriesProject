package sample.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product", schema = "maindb", catalog = "")
public class ProductEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /*
    @OneToMany(targetEntity=FactoryEntity.class, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<FactoryEntity>factories;

    public Set<FactoryEntity> getFactories(){
        return factories;
    };

    public void setFactories(Set<FactoryEntity>factories){
         this.factories=factories;
     }

    @OneToMany(targetEntity=OrderingEntity.class, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<OrderingEntity> orderings;

    public Set<OrderingEntity> getOrderings(){
        return orderings;
    };
    public void setOrderings(Set<OrderingEntity>orderings){
        this.orderings=orderings;
    }

 */
}
