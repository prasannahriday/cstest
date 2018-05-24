/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.offers;

import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferProcessorTest {

    static class TestOffer implements Offer {
        Offer next;

        @Override
        public void setNext(Offer offer) {
            this.next = offer;
        }

        @Override
        public void applyOffer(ShoppingCart cart, List<Discount> discounts) {
            discounts.add(new Discount("name", 10.0, 1.0));
            if (next != null) {
                next.applyOffer(cart, discounts);
            }
        }
    }

    @Test
    public void shouldAddHandlerAndApplyOffer() throws Exception {
        OfferProcessor offerProcessor = new OfferProcessor();
        offerProcessor.addHandler(new TestOffer());
        offerProcessor.addHandler(new TestOffer());
        offerProcessor.addHandler(new TestOffer());
        List<Discount> discounts = offerProcessor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(discounts).hasSize(3);
    }

    @Test
    public void shouldApplyOfferWithNoOfferInChain() throws Exception {
        OfferProcessor offerProcessor = new OfferProcessor();
        List<Discount> discounts = offerProcessor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(discounts).isEmpty();
    }

    @Test
    public void shouldCreateSimpleOfferProcessor() throws Exception {
        List<OfferItem> offerItems = new ArrayList<>();
        offerItems.add(new OfferItem("name", 1, new DiscountItem("name", 10.0)));
        PriceList priceList = Mockito.mock(PriceList.class);
        OfferProcessor offerProcessor = OfferProcessor.createSimpleOfferProcessor(offerItems, priceList);
        List<Discount> discounts = offerProcessor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(discounts).isEmpty();
    }

}