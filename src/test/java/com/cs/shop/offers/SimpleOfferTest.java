/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.offers;


import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;
import com.cs.shop.utils.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class SimpleOfferTest {

    @Test
    public void shouldGetNextFalse() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        assertThat(simpleOffer.hasNext()).isFalse();
    }

    @Test
    public void shouldGetNextReturnsTrueAfterSettingNext() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        simpleOffer.setNext(Mockito.mock(SimpleOffer.class));
        assertThat(simpleOffer.hasNext()).isTrue();
    }

    @Test
    public void shouldGetDiscount() throws Exception {
        OfferItem offerItem = Mockito.mock(OfferItem.class);
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        DiscountCalculator discountCalculator = Mockito.mock(DiscountCalculator.class);
        Mockito.when(discountCalculator.calculateDiscount(any()))
                .thenReturn(new Discount("item", 10.0, 1.0));
        Discount discount = simpleOffer.getDiscount(discountCalculator);
        assertThat(discount.getDiscountPercent()).isEqualTo(10.0);
        assertThat(discount.getDiscountValue()).isEqualTo(1.0);
        assertThat(discount.getName()).isEqualTo("item");
    }


    

    @Test
    public void shouldGetOfferWithTwoOffersInChain() throws Exception {
        OfferItem offerItem = new OfferItem("Melon", 2, new DiscountItem("Melon", 10.0));
        PriceList priceList = Mockito.mock(PriceList.class);
        SimpleOffer simpleOffer = new SimpleOffer(offerItem, priceList);
        simpleOffer.setNext(Mockito.mock(SimpleOffer.class));
        ShoppingCart shoppingCart = Mockito.mock(ShoppingCart.class);
        Mockito.when(shoppingCart.getItemCount(any()))
                .thenReturn(2L);
        ArrayList<Discount> discounts = new ArrayList<>();
        simpleOffer.applyOffer(shoppingCart, discounts);
        assertThat(discounts).hasSize(1);
        assertThat(discounts.get(0).getDiscountValue()).isEqualTo(0.0);
    }

   
}