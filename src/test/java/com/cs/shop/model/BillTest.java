
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.model;



import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BillTest {

    @Test
    public void shouldGetSubtotal() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getSubtotal()).isEqualTo(10.0);
    }

    @Test
    public void shouldGetTotal() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getTotal()).isEqualTo(10.0);
    }

    @Test
    public void shouldGetEmptyDiscounts() throws Exception {
        Bill bill = new Bill(10.0, 10.0, new ArrayList<>());
        assertThat(bill.getDiscounts()).isEmpty();
    }

    @Test
    public void shouldGetDiscounts() throws Exception {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("item1", 10.0, 1.0));
        discounts.add(new Discount("item2", 10.0, 1.0));
        discounts.add(new Discount("item3", 10.0, 1.0));
        Bill bill = new Bill(10.0, 10.0, discounts);
        assertThat(bill.getDiscounts()).isNotEmpty();
        assertThat(bill.getDiscounts()).hasSize(3);
    }

}