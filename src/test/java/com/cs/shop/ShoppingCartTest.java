/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {

    @Test
    public void shouldGetItemsReturnsItemsInBasketEmpty() throws Exception {
        List<String> items = new ArrayList<>();
        assertThat(new ShoppingCart(items).getItemCountMap()).isEmpty();
    }

    @Test
    public void shouldGetItemsReturnsItemsInBasket() throws Exception {
        List<String> items = Arrays.asList("Apple", "Melon", "Banana");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItemCountMap()).isNotEmpty();
        assertThat(shoppingCart.getItemCountMap()).hasSize(3);
    }

    @Test
    public void shouldGetItems() throws Exception {
        List<String> items = Arrays.asList("Apple", "Melon", "Banana");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItems()).isEqualTo(items);
    }

    @Test
    public void shouldGetItem() throws Exception {
        List<String> items = Arrays.asList("Apple", "Melon", "Banana");
        ShoppingCart shoppingCart = new ShoppingCart(items);
        assertThat(shoppingCart.getItemCount("Apple")).isEqualTo(1L);
    }

   

}