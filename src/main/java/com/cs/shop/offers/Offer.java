/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.offers;

/**
 *
 * @author pv
 */
import com.cs.shop.ShoppingCart;
import com.cs.shop.model.*;

import java.util.List;

interface Offer {

    void setNext(Offer offer);

    void applyOffer(ShoppingCart cart, List<Discount> discounts);

}