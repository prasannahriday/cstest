/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;

import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyFormatTest {

    @Test
    public void shouldGetCurrencyWithFractionalUnit() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(10.10);
        assertThat(format).isEqualTo("£10.10");
    }

    @Test
    public void shouldGetCurrencyFormatWithOnlyFractionalUnit() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(0.10);
        assertThat(format).isEqualTo("10p");
    }

    @Test
    public void shouldGetFractionalUnitUK() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        assertThat(currencyFormat.fractionalUnit()).isEqualTo("p");
    }


}