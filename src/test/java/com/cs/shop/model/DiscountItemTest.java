/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountItemTest {

    @Test
    public void shouldGetName() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", null);
        assertThat(discountItem.getName()).isEqualTo("name");
    }

    @Test
    public void shouldGetDiscountPercent() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", 10.0);
        assertThat(discountItem.getDiscountPercent()).isEqualTo(10.0);
    }

    @Test
    public void shouldGetDiscountPercentShouldBeZeroWhenNull() throws Exception {
        DiscountItem discountItem = new DiscountItem("name", null);
        assertThat(discountItem.getDiscountPercent()).isEqualTo(0.0);
    }

}