/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.offers;

import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;

/**
 *
 * @author pv
 */


import java.util.ArrayList;
import java.util.List;


public class OfferProcessor {

    private Offer prevHandler;
    private Offer rootHandler;

    public void addHandler(Offer handler) {
        if (prevHandler != null) {
            prevHandler.setNext(handler);
        } else {
            rootHandler = handler;
        }
        prevHandler = handler;
    }

    public List<Discount> applyOffer(ShoppingCart cart) {
        List<Discount> discounts = new ArrayList<>();
        if (rootHandler != null) {
            rootHandler.applyOffer(cart, discounts);
        }
        return discounts;
    }

    public static OfferProcessor createSimpleOfferProcessor(List<OfferItem> offerItems, PriceList priceList) {
        OfferProcessor offerProcessor = new OfferProcessor();
        for (OfferItem offerItem : offerItems) {
            offerProcessor.addHandler(new SimpleOffer(offerItem, priceList));
        }
        return offerProcessor;
    }

}