/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;

import com.cs.shop.model.*;
import com.typesafe.config.Config;


/**
 *
 * @author pv
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    private final Config config;

    public ConfigParser(Config config) {
        this.config = config;
    }

    public List<OfferItem> getOffers() {
        ArrayList<OfferItem> offerItems = new ArrayList<>();
        List<? extends Config> offers = config.getConfigList("offers");
        for (Config offer : offers) {
            OfferItem offerItem = parseOfferItem(offer);
            offerItems.add(offerItem);
        }
        return offerItems;
    }

    public PriceList getPriceList() {
        Map<String, Double> prices = new HashMap<>();
        Config priceConfig = config.getConfig("price");
        priceConfig.entrySet().forEach(price -> {
            String product = price.getKey();
            Double value = priceConfig.getDouble(product);
            prices.put(product, value);
        });
        return new PriceList(prices);
    }

    public static OfferItem parseOfferItem(Config config) {
        String name = config.getString("name");
        int quantity = config.getInt("quantity");
        Config discount = config.getConfig("discount");
        DiscountItem discountItem = parseDiscountItem(discount);
        return new OfferItem(name, quantity, discountItem);
    }

    public static DiscountItem parseDiscountItem(Config config) {
        String discountName = config.getString("name");
        double discountPercent = config.getDouble("discountPercent");
        return new DiscountItem(discountName, discountPercent);
    }

}