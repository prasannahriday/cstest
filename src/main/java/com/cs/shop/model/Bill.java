/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

/**
 *
 * @author pv
 */
import java.util.List;

/**
 * Bill contains total, subtotal and array of discounts
 */
public class Bill {

    private final Double subtotal;
    private final Double total;
    private final List<Discount> discounts;

    public Bill(Double subtotal, Double total, List<Discount> discounts) {
        this.subtotal = subtotal;
        this.total = total;
        this.discounts = discounts;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

}
