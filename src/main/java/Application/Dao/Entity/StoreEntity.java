package Application.Dao.Entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "store", schema = "sakila")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private StaffEntity managerStaffEntity;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
