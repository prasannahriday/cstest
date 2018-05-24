/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;


import org.junit.BeforeClass;
import org.junit.Test;


import java.util.HashMap;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountCalculatorTest {

    private static final Map<String, Double> priceList = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        priceList.put("Melon", 0.50);
        priceList.put("Melon", 0.50);
        priceList.put("Lime", 0.15);
        priceList.put("Lime", 0.15);
        priceList.put("Lime", 0.15);
    }

    @Test
    public void shouldGet50Percent() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(1, 100.0, 50.0);
        assertThat(discount).isEqualTo(50.0);
    }

    @Test
    public void shouldGet33PercentDiscount() throws Exception {
        Double discount = DiscountCalculator.calculateDiscount(3, 100.0, 33.33);
        assertThat(discount).isEqualTo(99.99);
    }

   
}