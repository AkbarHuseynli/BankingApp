package org.example.account.model;

import jakarta.persistence.*;
import org.jilt.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal balance;
    LocalDateTime creationDate;

    @ElementCollection
    Set<String> transactions;

    public Account() {
    }

    public Account(Long id, BigDecimal balance, LocalDateTime creationDate, Set<String> transactions) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.transactions = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<String> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", creationDate=" + creationDate +
                ", transactions=" + transactions +
                '}';
    }
}
