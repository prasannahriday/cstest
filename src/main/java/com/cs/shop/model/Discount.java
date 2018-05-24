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

/**
 *
 * This represents the discount which has name/percent and value
 */
public class Discount {

    private final String name;
    private final Double discountPercent;
    private final Double discountValue;

    public Discount(String name, Double discountPercent, Double discountValue) {
        this.name = name;
        this.discountPercent = discountPercent;
        this.discountValue = discountValue;
    }

    public String getName() {
        return name;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

}