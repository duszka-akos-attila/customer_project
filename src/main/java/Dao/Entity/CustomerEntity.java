package Dao.Entity;

import Dao.Entity.Address.AddressEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer", schema = "sakila")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    @Column(name = "active")
    private int active;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
