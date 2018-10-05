package com.housemate.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "expenseName", nullable = false)
    private String expenseName;

    @Column(name = "createdOn", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Expense(){}

    public Expense(User user, BigDecimal amount, String expenseName, Date createdOn) {
        this.user = user;
        this.amount = amount;
        this.expenseName = expenseName;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", expenseName='" + expenseName + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
