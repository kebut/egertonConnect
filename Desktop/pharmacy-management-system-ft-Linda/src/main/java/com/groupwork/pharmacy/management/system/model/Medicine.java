package com.groupwork.pharmacy.management.system.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,length = 30)
    private String name;

    @Column(nullable = false,length = 20)
    private Long price;

    @Column(nullable = false,length = 20)
    private Integer quantity;


    @Column(nullable = false,length = 10)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateOfExpiry;

    @Column(nullable = false,length = 10)
    private Byte drugStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

  /*  @OneToMany
    @JoinColumn(name = "suppliers_id")
    private Suppliers suppliers;*/

    public Medicine(){

    }
    public Medicine(Long id,String name,Long price,Integer quantity,Date dateOfExpiry,Byte drugStatus,Category category){
        super();
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.dateOfExpiry=dateOfExpiry;
        this.drugStatus=drugStatus;
        this.category=category;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Byte getDrugStatus() {
        return drugStatus;
    }

    public void setDrugStatus(Byte drugStatus) {
        this.drugStatus = drugStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
