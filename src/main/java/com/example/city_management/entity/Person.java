package com.example.city_management.entity;

import com.example.city_management.entity.enumeration.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
//@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "PERSONS")
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @NonNull
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    @NonNull
    private String surname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    //    @OneToOne( optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(optional = false, mappedBy = "owner", cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    private Passport passport;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<CarInsurance> insurance;


    @OneToOne(optional = false, mappedBy = "owner", cascade = CascadeType.ALL)
    @ToString.Exclude
    private InternationalPassport internationalPassport;

    @Column(name = "balance", precision = 19, scale = 2, nullable = false)
    @NonNull
    private BigDecimal balance = BigDecimal.ZERO;


    //один,несколько, 0

    //    @ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "owner_house",
            joinColumns = @JoinColumn(name = "owner_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "house_id",
                    referencedColumnName = "id"))
    @ToString.Exclude
    private Set<House> houses;


    //у жителя мб одна/неск/0 машин, но у машины тольео один хозяин

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Car> cars;

    public void accruesBalance(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            if (this.balance == null) {
                this.balance = BigDecimal.ZERO;
            }
            this.balance = this.balance.add(amount);
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Person person = (Person) o;
        return getId() != null && Objects.equals(getId(), person.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Person(@NonNull String name, @NonNull String surname, Gender gender, @NonNull Passport passport) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.passport = passport;
    }

    public Person(@NonNull String name, @NonNull String surname, Gender gender, @NonNull Passport passport, @NonNull BigDecimal balance) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.passport = passport;
        this.balance = balance;
    }
}



