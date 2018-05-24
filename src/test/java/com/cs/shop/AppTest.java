/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop;

import com.cs.shop.model.*;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class AppTest {

    @Test
    public void shouldGetShoppingCart() throws Exception {
        ShoppingCart shoppingCart = ShoppingApp.getShoppingCart(new String[]{"apple", "apple", "melon"});
        assertThat(shoppingCart.getItemCount("apple")).isEqualTo(2);
        assertThat(shoppingCart.getItemCount("melon")).isEqualTo(1);
    }


    @Test
    public void shouldPrintBill() throws Exception {
        Bill bill = Mockito.spy(new Bill(10.10, 1.0, new ArrayList<>()));
        ShoppingApp.print(bill);
        Mockito.verify(bill, times(2)).getDiscounts();
        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();
    }

    @Test
    public void shouldPrintBillWithDiscounts() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("name", 10.10, 2.20));
        Bill bill = Mockito.spy(new Bill(10.10, 1.0, discounts));
        ShoppingApp.print(bill);
        Mockito.verify(bill, times(2)).getDiscounts();
        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();
    }

    

}