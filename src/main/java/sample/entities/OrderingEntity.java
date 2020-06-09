package sample.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ordering", schema = "maindb", catalog = "")
public class OrderingEntity {
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
    @Column(name = "price", nullable = true)
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "accepted", nullable = true)
    private boolean accepted;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Basic
    @Column(name = "id_factory", nullable = false)
    private int idFactory;

    public int getIdFactory() {
        return idFactory;
    }

    public void setIdFactory(int idFactory) {
        this.idFactory = idFactory;
    }

    @Basic
    @Column(name = "id_product", nullable = false)
    private int idProduct;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct= idProduct;
    }

    @Basic
    @Column(name = "id_transporter", nullable = true)
    private Integer idTransporter;

    public Integer getIdTransporter() {
        return idTransporter;
    }

    public void setIdTransporter(Integer idTransporter) {
        this.idTransporter = idTransporter;
    }

    @Basic
    @Column(name = "id_factory_from", nullable = false)
    private int idFactoryFrom;

    public int getIdFactoryFrom() {
        return idFactoryFrom;
    }

    public void setIdFactoryFrom(int idFactoryFrom) {
        this.idFactoryFrom= idFactoryFrom;
    }

    @Basic
    @Column(name = "distance", nullable = false)
    private Integer distance;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderingEntity that = (OrderingEntity) o;
        return id == that.id &&
                accepted == that.accepted &&
                idFactory == that.idFactory &&
                idProduct == that.idProduct &&
                idFactoryFrom == that.idFactoryFrom &&
                price.equals(that.price) &&
                idTransporter.equals(that.idTransporter) &&
                distance.equals(that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, accepted, idFactory, idProduct, idTransporter, idFactoryFrom, distance);
    }


    /*
    @ManyToOne(targetEntity=FactoryEntity.class, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_factory", referencedColumnName = "id")
    private FactoryEntity factory;

    public FactoryEntity getFactory() {
        return factory;
    }

    public void setFactory(FactoryEntity factoryByIdFactory) {
        this.factory = factoryByIdFactory;
    }

    @ManyToOne(targetEntity=ProductEntity.class, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_product", referencedColumnName="id", nullable=false)
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @ManyToOne(targetEntity=TransporterEntity.class, fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "id_transporter", nullable=true)
    private TransporterEntity transporter;

    public TransporterEntity getTransporter() {
        return transporter;
    }

    public void setTransporter(TransporterEntity transporter) {
        this.transporter = transporter;
    }

 */
}
