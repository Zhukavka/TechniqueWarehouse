package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 25.03.2015.
 */
@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.getAll", query = "select e from Employee e")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private  String  name;
    @Column(name = "surname", nullable = false)
    private  String  surname;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private  Address address;
    @Column(name = "salary", nullable = false)
    private float salary;

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee(String name, String surname, float salary, Address address) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.address = address;
    }
    public  Employee()  {}
    public  void  setName(String  name)  { this.name  =  name  ; }
    public  void  setSurname(String  surname)  { this. surname =  surname; }
    public  String  getName()  { return  name; }
    public  String  getSurname()  { return  surname; }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        if (id == 0) return "NOT INFORMATION ABOUT EMPLOYEE";
        return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + " salary: " + salary +  "]" + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (address.equals(employee.address)) return false;
        if (!name.equals(employee.name)) return false;
        if (!surname.equals(employee.surname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
