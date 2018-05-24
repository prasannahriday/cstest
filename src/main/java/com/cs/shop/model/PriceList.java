/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

import java.util.Map;

/**
 *
 * @author pv
 */
/**
 * Full price list
 */
public class PriceList {

    private final Map<String, Double> priceList;

    public PriceList(Map<String, Double> priceList) {
        this.priceList = priceList;
    }

    public Double getPrice(String name) {
        Double price = priceList.get(name);
        if (price == null) {
           System.out.println(name + " not in the price list");
        }
        return price == null ? 0.0 : price;
    }

}