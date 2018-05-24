/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    @Test
    public void shouldGetName() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getName()).isEqualTo("name");
    }

    @Test
    public void shouldGetDiscountPercent() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getDiscountPercent()).isEqualTo(10.0);
    }

    @Test
    public void shouldGetDiscountValue() throws Exception {
        Discount discount = new Discount("name", 10.0, 1.0);
        assertThat(discount.getDiscountValue()).isEqualTo(1.0);
    }


}