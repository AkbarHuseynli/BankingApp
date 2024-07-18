package org.example.customer.model;

import jakarta.persistence.*;
import org.jilt.Builder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;

    @ElementCollection
    Set<Long> accounts;

    public Customer() {
    }

    public Customer(Long id, String name, String surname, Set<Long> accounts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accounts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Long> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Long> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
