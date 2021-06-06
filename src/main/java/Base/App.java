/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solutions
 *  Copyright 2021 Kevin Schiffli
 */
package Base;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main( String[] args )
    {
        String convChoice = getChoice();
        double temp = getTemp(convChoice); //temp variable is Temperature, not Temporary
        double convTemp = calcTemp(convChoice, temp);
        printOutput(convChoice, convTemp);
    }
    public static String getChoice()
    {
        String output = "";
        boolean validFlag = false;
        System.out.println("""
                Press C to convert from Fahrenheit to Celsius.
                Press F to convert from Celsius to Fahrenheit.
                Your choice:\s""");
        String input = in.nextLine();
        while (!validFlag)
        {
            if ("F".equals(input.toUpperCase(Locale.ROOT)))
            {
                output = "Celsius"; //Selector uses entered scale, not desired scale
                validFlag = true;
            }
            else if ("C".equals(input.toUpperCase(Locale.ROOT)))
            {
                output = "Fahrenheit";
                validFlag = true;
            }
            else
            {
                System.out.print("Please enter either C or F: ");
                input = in.nextLine();
            }
        }
        return output;
    }
    public static double getTemp(String convChoice)
    {
        System.out.printf( "Please enter the temperature in %s: ", convChoice);
        String input = in.nextLine();

        boolean validFlag = false;
        while(!validFlag)
            try
            {
                Double.parseDouble(input);
                validFlag = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number: ");
                input = in.nextLine();
            }
        return Double.parseDouble(input);
    }
    public static double calcTemp(String convChoice, double temp)
    {
        double output;
        if (convChoice.equals("Fahrenheit"))
        {
            output = (temp - 32)  * 5 / 9;
        }
        else if (convChoice.equals("Celsius"))
        {
            output = (temp * 9 / 5) + 32;
        }
        else output = -1;

        return output;
    }

    public static void printOutput(String convChoice, double convTemp)
    {
        DecimalFormat decFormat = new DecimalFormat("#,###.##");
        String tempFormatted = decFormat.format(convTemp);

        //Invert the user's choice, i.e. "Celsius" to "Fahrenheit" for use in the output string
        String choiceInv;
        if (convChoice.equals("Fahrenheit")) choiceInv = "Celsius";
        else if (convChoice.equals("Celsius")) choiceInv = "Fahrenheit";
        else choiceInv = "SELECTION FAILURE";

        System.out.printf("The temperature in %s is %s", choiceInv, tempFormatted);
    }
}
