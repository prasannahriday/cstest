/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop;

import com.cs.shop.offers.*;
import com.cs.shop.model.*;

/**
 *
 * @author pv
 */
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This is the main class that generates a bill
 */
class BillGenerator {

    private final PriceList priceList;
    private final OfferProcessor processor;

    public BillGenerator(PriceList priceList, OfferProcessor processor) {
        this.priceList = priceList;
        this.processor = processor;
    }

    public Bill generateBill(ShoppingCart shoppingCart) {
        Double subTotal = calculateSubTotal(shoppingCart);
        List<Discount> discounts = processor.applyOffer(shoppingCart);
        Optional<Double> discount = totalDiscount(discounts);
        Double total = subTotal - discount.orElse(0.0);
        return new Bill(subTotal, total, discounts);
    }

    public static Optional<Double> totalDiscount(List<Discount> discounts) {
        return discounts.stream()
                .map(Discount::getDiscountValue)
                .reduce((d1, d2) -> d1 + d2);
    }

    public Double calculateSubTotal(ShoppingCart cart) {
        return calculateSubTotal(cart.getItemCountMap(), priceList);
    }

    public static Double calculateSubTotal(Map<String, Long> items, PriceList priceList) {
        return items.entrySet().stream()
                .mapToDouble(item -> priceList.getPrice(item.getKey()) * item.getValue())
                .sum();
    }

}