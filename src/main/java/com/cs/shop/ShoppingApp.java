/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop;

import com.cs.shop.utils.*;
import com.cs.shop.offers.*;
import com.cs.shop.model.*;
import java.util.List;
import java.util.Arrays;
import java.util.Locale;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


/**
 *
 * @author pv
 */
/**
 * Main class
 */
public class ShoppingApp {

    private static final CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);

    public static void main(String[] args) {
        List<String> items = Arrays.asList(args);
        //List<String> items = Arrays.asList("Apple", "Banana", "Melon", "Melon", "Melon", "Lime");
        Config config = ConfigFactory.load();

        ShoppingCart shoppingCart = new ShoppingCart(items);

        BillGenerator billGenerator = getBillGenerator(config);
        Bill bill = billGenerator.generateBill(shoppingCart);
        print(bill);
    }

    public static BillGenerator getBillGenerator(Config config) {
        ConfigParser configParser = new ConfigParser(config);
        List<OfferItem> offers = configParser.getOffers();
        PriceList priceList = configParser.getPriceList();

        OfferProcessor offerProcessor = OfferProcessor.createSimpleOfferProcessor(offers, priceList);

        return new BillGenerator(priceList, offerProcessor);
    }

    public static ShoppingCart getShoppingCart(String[] args) {
        return new ShoppingCart(Arrays.asList(args));
    }

    public static void print(Bill bill) {
        System.out.println("Subtotal: " + currencyFormat.format(bill.getSubtotal()));

        bill.getDiscounts().forEach(ShoppingApp::print);

        if (bill.getDiscounts().isEmpty()) {
            System.out.println("(No offers available)");
        }

        System.out.println("Total: " + currencyFormat.format(bill.getTotal()));
    }

    public static void print(Discount discount) {
        System.out.println(discount.getName() + " " + discount.getDiscountPercent() + "% off: -" + currencyFormat.format(discount.getDiscountValue()));
    }

}