/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferItemTest {

    @Test
    public void shouldGetName() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 1, discountItem);
        assertThat(offerItem.getName()).isEqualTo("name");
    }

    @Test
    public void shouldGetQuantity() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 1, discountItem);
        assertThat(offerItem.getQuantity()).isEqualTo(1);
    }

    @Test
    public void shouldGetDiscountItem() throws Exception {
        DiscountItem discountItem = new DiscountItem("discountItemName", 1.0);
        OfferItem offerItem = new OfferItem("name", 0, discountItem);
        assertThat(offerItem.getDiscountItem().getName()).isEqualTo("discountItemName");
        assertThat(offerItem.getDiscountItem().getDiscountPercent()).isEqualTo(1.0);
    }

}