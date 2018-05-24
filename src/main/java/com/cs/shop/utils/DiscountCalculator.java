/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;

import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;

/**
 *
 * @author pv
 */
/**
 * Logic to calculate discount
 */
public class DiscountCalculator {

    private final PriceList priceList;
    private final ShoppingCart cart;

    public DiscountCalculator(ShoppingCart cart, PriceList priceList) {
        this.priceList = priceList;
        this.cart = cart;
    }

    public Discount calculateDiscount(OfferItem offerItem) {
        DiscountItem discountItem = offerItem.getDiscountItem();
        Double discountPercent = discountItem.getDiscountPercent();
        String discountItemName = discountItem.getName();
        Long discountItemCount = cart.getItemCount(discountItemName);
        Double discountItemPrice = priceList.getPrice(discountItemName);
        Double discountValue = calculateDiscount(discountItemCount, discountItemPrice, discountPercent);
        return new Discount(discountItemName, discountPercent, discountValue);
    }

    public static Double calculateDiscount(long count, Double price, Double discountPercent) {
        return (count * price * discountPercent) / 100;
    }

}