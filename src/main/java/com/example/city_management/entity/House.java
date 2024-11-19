package com.example.city_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "HOUSES")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    @NonNull
    private Address address;

    //один,несколько, 0
//    @ManyToMany
//    @JoinTable(name = "owner_house",
//            joinColumns = @JoinColumn(name = "house_id",
//                    referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "owner_id",
//                    referencedColumnName = "id"))
    @ManyToMany(mappedBy = "houses")
    @ToString.Exclude
    private Set<Person> owners = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        House house = (House) o;
        return getId() != null && Objects.equals(getId(), house.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
