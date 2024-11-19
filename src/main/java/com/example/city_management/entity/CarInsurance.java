package com.example.city_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "CAR_INSURANCES")
public class CarInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number", nullable = false)
    @NonNull
    private String policyNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id" , nullable = false)
    private Person owner;

    @OneToOne
    @JoinColumn(name = "number_pass_of_owner")
    private Passport numberPassOfOwner;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;


    @Column(name = "strat_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CarInsurance that = (CarInsurance) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
