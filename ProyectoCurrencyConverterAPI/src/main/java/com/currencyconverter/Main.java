package com.currencyconverter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyFilter currencyFilter = new CurrencyFilter();
        String continueConversion;

        do {
            System.out.println("Bienvenido al Conversor de Monedas");

            String sourceCurrency, targetCurrency;
            do {
                System.out.print("Ingrese la moneda de origen (3 letras): ");
                sourceCurrency = scanner.nextLine().toUpperCase();
            } while (!currencyFilter.isValidCurrency(sourceCurrency));

            do {
                System.out.print("Ingrese la moneda de destino (3 letras): ");
                targetCurrency = scanner.nextLine().toUpperCase();
            } while (!currencyFilter.isValidCurrency(targetCurrency));

            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            CurrencyAPI currencyAPI = new CurrencyAPI();
            double conversionRate = currencyAPI.getConversionRate(sourceCurrency, targetCurrency);

            if (conversionRate == 0) {
                System.out.println("Error: No se encontró una tasa de conversión válida.");
            } else {
                double convertedAmount = amount * conversionRate;
                System.out.printf("La cantidad convertida es: %.2f %s\n", convertedAmount, targetCurrency);
            }

            scanner.nextLine(); // Consumir el salto de línea pendiente
            System.out.print("¿Deseas realizar otra conversión? (y/n): ");
            continueConversion = scanner.nextLine().toLowerCase();

        } while (continueConversion.equals("y"));

        System.out.println("Gracias por usar el conversor de monedas. ¡Adiós!");
        scanner.close();
    }
}
