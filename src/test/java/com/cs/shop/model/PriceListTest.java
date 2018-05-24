/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceListTest {

    private static final Map<String, Double> priceList = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        priceList.put("Apple", 0.35);
        priceList.put("Banana", 0.20);
        priceList.put("Melon", 0.50);
        priceList.put("Lime", 0.15);
    }

    @Test
    public void shouldGetPriceShouldReturnPrice() throws Exception {
        PriceList priceList = new PriceList(PriceListTest.priceList);
        assertThat(priceList.getPrice("Apple")).isEqualTo(0.35);
        assertThat(priceList.getPrice("Banana")).isEqualTo(0.20);
        assertThat(priceList.getPrice("Melon")).isEqualTo(0.50);
        assertThat(priceList.getPrice("Lime")).isEqualTo(0.15);
    }

    @Test
    public void shouldIfItemNotFoundReturnZero() throws Exception {
        PriceList priceList = new PriceList(PriceListTest.priceList);
        assertThat(priceList.getPrice("Potato")).isEqualTo(0.0);
    }

}