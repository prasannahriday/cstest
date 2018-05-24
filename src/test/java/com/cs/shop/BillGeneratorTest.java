/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop;

import com.cs.shop.model.*;

import com.cs.shop.offers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

public class BillGeneratorTest {

    private static final Map<String, Double> prices = new HashMap<>();

    @BeforeClass
    public static void setUp() throws Exception {
        prices.put("Apple", 0.35);
        prices.put("Banana", 0.25);
        prices.put("Melon", 0.50);
        prices.put("Lime", 0.15);
    }

    @Test
    public void shouldGenerateBillWithOutOffers() throws Exception {
        List<String> list = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(list);
        PriceList priceList = Mockito.mock(PriceList.class);
        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isZero();
    }

    @Test
    public void shouldGenerateBillWithOffers() throws Exception {
        List<String> list = Arrays.asList("Apple", "Lime", "Banana", "Melon");
        PriceList priceList = Mockito.mock(PriceList.class);
        Mockito.when(priceList.getPrice(any())).thenReturn(1.0);

        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        Mockito.when(offerProcessor.applyOffer(any()))
                .thenReturn(Arrays.asList(
                        new Discount("item2", 10.0, 1.0),
                        new Discount("item1", 10.0, 1.0)
                ));

        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        ShoppingCart shoppingCart = new ShoppingCart(list);

        Bill bill = billGenerator.generateBill(shoppingCart);
        assertThat(bill.getSubtotal()).isEqualTo(4.0);
        assertThat(bill.getTotal()).isEqualTo(2.0);
    }

    @Test
    public void shouldCalculateSubtotal() throws Exception {
        PriceList priceList = Mockito.mock(PriceList.class);
        Mockito.when(priceList.getPrice(any())).thenReturn(2.0);
        OfferProcessor offerProcessor = Mockito.mock(OfferProcessor.class);
        BillGenerator billGenerator = new BillGenerator(priceList, offerProcessor);
        HashMap<String, Long> itemCountMap = new HashMap<>();
        itemCountMap.put("item1", 2L);
        itemCountMap.put("item2", 3L);
        itemCountMap.put("item3", 1L);
        itemCountMap.put("item4", 4L);

        ShoppingCart cart = Mockito.mock(ShoppingCart.class);
        Mockito.when(cart.getItemCountMap())
                .thenReturn(itemCountMap);
        Double subTotal = billGenerator.calculateSubTotal(cart);
        assertThat(subTotal).isEqualTo(20);
    }

   

    @Test
    public void shouldGetTotalDiscount() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("name", 10.0, 10.0));
        discounts.add(new Discount("name", 10.0, 20.0));
        discounts.add(new Discount("name", 10.0, 30.0));
        Optional<Double> optionalDiscount = BillGenerator.totalDiscount(discounts);
        assertThat(optionalDiscount.get()).isEqualTo(60);
    }

    @Test
    public void shouldGetTotalDiscountEmptyDiscountsList() throws Exception {
        ArrayList<Discount> discounts = new ArrayList<>();
        Optional<Double> optionalDiscount = BillGenerator.totalDiscount(discounts);
        assertThat(optionalDiscount).isEmpty();
    }

}