/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.offers;

import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;
import com.cs.shop.utils.DiscountCalculator;

/**
 *
 * @author pv
 */


import java.util.List;

public class SimpleOffer implements Offer {

    private final PriceList priceList;
    private final OfferItem offerItem;
    private Offer next;

    public SimpleOffer(OfferItem offerItem, PriceList priceList) {
        this.offerItem = offerItem;
        this.priceList = priceList;
    }

   
   
    public void applyOffer(ShoppingCart cart, List<Discount> discounts) {
        long itemCount = cart.getItemCount(offerItem.getName());

        if (itemCount >= offerItem.getQuantity()) {
            DiscountCalculator discountCalculator = new DiscountCalculator(cart, priceList);
            Discount discount = getDiscount(discountCalculator);
            discounts.add(discount);
        }

        if (hasNext()) {
            next.applyOffer(cart, discounts);
        }
    }

    @Override
    public void setNext(Offer next) {
        this.next = next;
    }

    public Discount getDiscount(DiscountCalculator discountCalculator) {
        return discountCalculator.calculateDiscount(offerItem);
    }

    public boolean hasNext() {
        return this.next != null;
    }

}