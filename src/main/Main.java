package main;

import models.GetConversionRate;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Variables
        String userOriginalCurrency = "";
        String userTargetCurrency;
        double userQuantity;
        double conversionRate;
        double finalQuantity;
        boolean input;

        //Scanners
        Scanner getString = new Scanner(System.in);
        Scanner getInt = new Scanner(System.in);

        //Welcome the user
        printWelcomeMessage();
        printMenu();

        //Ask the user for what action they want to do in a loop to force a valid answer
        do {
            System.out.println("\nSeleccione una moneda (Número o Código): ");
            userOriginalCurrency = getString.nextLine().toUpperCase();
            userOriginalCurrency = checkType(userOriginalCurrency);
            input = userOriginalCurrency.equals("ARS") || userOriginalCurrency.equals("BOB") || userOriginalCurrency.equals("BRL")
                    || userOriginalCurrency.equals("CLP") || userOriginalCurrency.equals("COP") || userOriginalCurrency.equals("USD")
                    || userOriginalCurrency.equals("7");
            if(!input) System.out.println("El dato ingresado: '" + userOriginalCurrency + "' no es una opción valida. Intentelo de nuevo.");
        } while(!input);

        // Main loop for the conversions
        while (!userOriginalCurrency.equals("7")){
            //Ask the user for the quantity they want to convert
                System.out.println("\nIngrese la cantidad a convertir: ");
                //Loop to force a valid answer
                while (!getInt.hasNextInt()) // loop until next token is integer
                {
                    System.out.println("\nEl dato ingresado: '" + getInt.next() + "' no es una cantidad valida.");
                    System.out.println("Ingrese la cantidad a convertir:");
                }
                userQuantity = getInt.nextInt();

            //Ask the user to what currency they want to convert
                //Loop to force a valid answer
                do {
                    System.out.println("\nSeleccione a que moneda gusta convertir los " + userQuantity + " " + userOriginalCurrency + ":");
                    userTargetCurrency = getString.nextLine().toUpperCase();
                    userTargetCurrency = checkType(userTargetCurrency);
                    input = userTargetCurrency.equals("ARS") || userTargetCurrency.equals("BOB") || userTargetCurrency.equals("BRL")
                            || userTargetCurrency.equals("CLP") || userTargetCurrency.equals("COP") || userTargetCurrency.equals("USD");
                    if(!input) System.out.println("No se seleccionó una opción valida. Intentelo de nuevo.");
                } while(!input);


            //Call the method to get the conversion rates
            GetConversionRate conversion = new GetConversionRate(userOriginalCurrency,userTargetCurrency);
            conversionRate = conversion.obtainRate();
            finalQuantity = conversionRate*userQuantity;
            System.out.println("\n" + userQuantity + " " + userOriginalCurrency + " es igual a " + String.format("%.02f", finalQuantity) + " " + userTargetCurrency);

            //Ask the user for what action they want to do
                //Loop to force a valid answer
                do {
                    System.out.println("\nSeleccione una opcion: ");
                    userOriginalCurrency = getString.nextLine().toUpperCase();
                    userOriginalCurrency = checkType(userOriginalCurrency);
                    input = userOriginalCurrency.equals("ARS") || userOriginalCurrency.equals("BOB") || userOriginalCurrency.equals("BRL")
                            || userOriginalCurrency.equals("CLP") || userOriginalCurrency.equals("COP") || userOriginalCurrency.equals("USD")
                            || userOriginalCurrency.equals("7");
                    if(!input) System.out.println("No se seleccionó una opción valida. Intentelo de nuevo.");
                } while(!input);
        }

        //Goodbye
        System.out.println("\nGracias por usar el servicio.");

    }

    public static void printWelcomeMessage(){

        System.out.println("   _____                                               _                                       _           ");
        System.out.println("  / ____|                                             | |                                     | |          ");
        System.out.println(" | |     ___  _ ____   _____ _ __ ___  ___  _ __    __| | ___   _ __ ___   ___  _ __   ___  __| | __ _ ___ ");
        System.out.println(" | |    / _ \\| '_ \\ \\ / / _ \\ '__/ __|/ _ \\| '__|  / _` |/ _ \\ | '_ ` _ \\ / _ \\| '_ \\ / _ \\/ _` |/ _` / __|");
        System.out.println(" | |___| (_) | | | \\ V /  __/ |  \\__ \\ (_) | |    | (_| |  __/ | | | | | | (_) | | | |  __/ (_| | (_| \\__ \\");
        System.out.println("  \\_____\\___/|_| |_|\\_/ \\___|_|  |___/\\___/|_|     \\__,_|\\___| |_| |_| |_|\\___/|_| |_|\\___|\\__,_|\\__,_|___/");


    }

    public static void printMenu(){
        System.out.println("\n\t┌───────────────────────────────┐");
        System.out.println("\t│ 1. ARS - Peso argentino       │");
        System.out.println("\t├───────────────────────────────┤");
        System.out.println("\t│ 2. BOB - Boliviano boliviano  │");
        System.out.println("\t├───────────────────────────────┤");
        System.out.println("\t│ 3. BRL - Real brasileño       │");
        System.out.println("\t├───────────────────────────────┤");
        System.out.println("\t│ 4. CLP - Peso chileno         │");
        System.out.println("\t├───────────────────────────────┤");
        System.out.println("\t│ 5. COP - Peso colombiano      │");
        System.out.println("\t├───────────────────────────────┤");
        System.out.println("\t│ 6. USD - Dólar estadounidense │");
        System.out.println("\t└───────────────────────────────┘");
        System.out.println("\n\t» Ingrese 7 para salir del programa «");

    }

    public static String checkType(String userInput){
        return switch (userInput) {
            case "1" -> "ARS";
            case "2" -> "BOB";
            case "3" -> "BRL";
            case "4" -> "CLP";
            case "5" -> "COP";
            case "6" -> "USD";
            default -> userInput;
        };

    }
}
