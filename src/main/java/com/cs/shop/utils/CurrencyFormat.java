/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.shop.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author pv
 */
/**
 * Formats given Double to currency
 * This formatter represents currency less than 1
 * using FractionalUnit
 */
public class CurrencyFormat {

    private final NumberFormat numberFormat;

    public CurrencyFormat(Locale locale) {
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public String format(Double number) {
        if (number.longValue() == 0) {
            Double v = number * 100;
            return v.longValue() + fractionalUnit();
        }
        return numberFormat.format(number);
    }

  
    public String fractionalUnit() {
        String result = "";
        String currencyCode = numberFormat.getCurrency().getCurrencyCode();
        switch (currencyCode) {
            case "GBP":
                result = "p";
                break;
         
        }
        return result;
    }

}