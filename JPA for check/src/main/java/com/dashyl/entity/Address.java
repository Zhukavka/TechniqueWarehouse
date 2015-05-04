package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 25.03.2015.
 */
@Entity
@Table(name = "address")
@NamedQuery(name = "Address.getAll", query = "select a from Address a")
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "address_id")
    private Long id;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "phoneNumber", nullable = false)
    private String phone;

    public Address() {}
    public Address(String city, String phone){
        this.city = city;
        this.phone = phone;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCity() {
        return city;
    }
    public String getPhone() {
        return phone;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        if (id == 0) return "NOT INFORMATION ABOUT ADDRESS";
        return "Address [id=" + id +", city="+ city +", phone="+ phone +"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return city.equals(address.city) && !(phone != null ? !phone.equals(address.phone) : address.phone != null);

    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
