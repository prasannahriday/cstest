/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;


import com.cs.shop.model.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ConfigParserTest {

    @Test
    public void shouldGetParseDiscountItem() throws Exception {
        Config config = Mockito.mock(Config.class);
        when(config.getString("name")).thenReturn("Melon");
        when(config.getDouble("discountPercent")).thenReturn(50.0);

        DiscountItem discountItem = ConfigParser.parseDiscountItem(config);
        assertThat(discountItem.getName()).isEqualTo("Melon");
        assertThat(discountItem.getDiscountPercent()).isEqualTo(50.00);
    }

    

    @Test
    public void shouldGetOffersFromConfig() throws Exception {
        Config config = ConfigFactory.load("applicationTest.conf");
        ConfigParser configParser = new ConfigParser(config);
        List<OfferItem> offers = configParser.getOffers();
        assertThat(offers).hasSize(2);
    }

    @Test
    public void shouldGetPriceListFromConfig() throws Exception {
        Config config = ConfigFactory.load("applicationTest.conf");
        ConfigParser configParser = new ConfigParser(config);
        PriceList priceList = configParser.getPriceList();
        assertThat(priceList.getPrice("Apple")).isEqualTo(0.35);
    }

    @Test
    public void shouldGetPriceListMockConfig() throws Exception {
        HashSet<Map.Entry<String, ConfigValue>> entries = new HashSet<>();
        entries.add(new AbstractMap.SimpleEntry<>("item1", ConfigValueFactory.fromAnyRef(50.0)));
        entries.add(new AbstractMap.SimpleEntry<>("item2", ConfigValueFactory.fromAnyRef(40.0)));
        entries.add(new AbstractMap.SimpleEntry<>("item3", ConfigValueFactory.fromAnyRef(30.0)));
        Config priceConfig = Mockito.mock(Config.class);
        Mockito.when(priceConfig.entrySet()).thenReturn(entries);
        Mockito.when(priceConfig.getDouble("item1")).thenReturn(50.0);
        Mockito.when(priceConfig.getDouble("item2")).thenReturn(40.0);
        Mockito.when(priceConfig.getDouble("item3")).thenReturn(30.0);
        Config config = Mockito.mock(Config.class);
        Mockito.when(config.getConfig("price")).thenReturn(priceConfig);

        ConfigParser configParser = new ConfigParser(config);
        PriceList priceList = configParser.getPriceList();
        assertThat(priceList.getPrice("item1")).isEqualTo(50.0);
        assertThat(priceList.getPrice("item2")).isEqualTo(40.0);
        assertThat(priceList.getPrice("item3")).isEqualTo(30.0);
    }

}