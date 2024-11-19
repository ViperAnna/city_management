package com.example.city_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PASSPORTS")
public class Passport {
    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name="person_id", nullable = false)
    private Long id;

    @Column(name = "number_of_passport", nullable = false, unique = true, length = 6)
    @NonNull
    private Long numberOfPassport;

//    @OneToOne( optional = false, cascade = CascadeType.REMOVE)
//    @JoinColumn(name="owner_id", referencedColumnName = "id", nullable = false)
//    @NonNull
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Person owner;


    public void setOwner(@NonNull Person owner) {
        this.owner = owner;
        this.id = owner.getId();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Passport passport = (Passport) o;
        return getId() != null && Objects.equals(getId(), passport.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
