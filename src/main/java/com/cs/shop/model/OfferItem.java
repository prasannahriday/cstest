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
 * This class represents an an Item that is on offer
 */
public class OfferItem {

    private final String name;
    private final int quantity;
    private final DiscountItem discountItem;

    public OfferItem(String name, int quantity, DiscountItem discountItem) {
        this.name = name;
        this.quantity = quantity;
        this.discountItem = discountItem;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountItem getDiscountItem() {
        return discountItem;
    }

}