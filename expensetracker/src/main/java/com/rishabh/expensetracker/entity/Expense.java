package com.rishabh.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="tbl_expenses")
@NoArgsConstructor
public class Expense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "name must not be null")
    @Size(min = 3,message = "name must be 3 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "desc must not be null")
    private String desc;

    @Column(name = "expense_amount")
    @NotNull(message = "amount must not be null")
    private Double amount;

    @Column(name = "category")
    @NotBlank(message = "category must not be null")
    private String category;

    @Column(name = "date")
    @NotNull(message = "date must not be null")
    private Date date;

    @Column(name = "created_on" , nullable = false ,updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = "updated_on" )
    @UpdateTimestamp
    private Timestamp updatedOn;

    public Expense(String name, String desc, Double amount, String category, Date date) {
        this.name = name;
        this.desc = desc;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }


}

