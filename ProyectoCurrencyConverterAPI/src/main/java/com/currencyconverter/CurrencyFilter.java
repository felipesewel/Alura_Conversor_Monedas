package com.currencyconverter;

public class CurrencyFilter {

    public boolean isValidCurrency(String currency) {
        // Verifica si la moneda tiene un formato válido (3 letras en mayúsculas)
        return currency.matches("[A-Z]{3}");
    }
}
