package com.example.shopify_clothes.model;


import jakarta.persistence.*;


@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long basketId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Catlog.class)
    @JoinColumn(name = "catlog_id", referencedColumnName = "catlogID", nullable = false)
    private Catlog catlogs;

    private int qty;


    // Constructor, getters and setters, etc.

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public Catlog getCatlogs() {
        return catlogs;
    }

    public void setCatlogs(Catlog catlogs) {
        this.catlogs = catlogs;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
