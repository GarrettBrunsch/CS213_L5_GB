// Garrett Brunsch
// Lab #5 - Recursion
// Due 7/20/25

import java.util.Scanner;

public class Main
{
    enum MenuOptions
    {
        INVALID, PRODUCT_SERIES, SUM_SERIES, QUIT
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        MenuOptions menuChoice = MenuOptions.INVALID;

        while (menuChoice != MenuOptions.QUIT)
        {
            displayMenu();
            choice = getUserChoice(scanner);

            choice = (choice >= MenuOptions.PRODUCT_SERIES.ordinal() && choice <= MenuOptions.QUIT.ordinal()) ? choice : 0;
            menuChoice = MenuOptions.values()[choice];

            switch (menuChoice)
            {
                case PRODUCT_SERIES:
                    handleProductSeries(scanner);
                    break;
                case SUM_SERIES:
                    handleSumSeries(scanner);
                    break;
                case QUIT:
                    System.out.println("Now exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid menu option");
                    break;
            }
        }
        scanner.close();
    }

    public static void displayMenu()
    {
        //FIXME remove clutter
        System.out.print("\n\n=== RECURSIVE SERIES CALCULATOR ===\n" +
                "1. Calculate Product Series:\n" +
                "2. Calculate Sum Series:\n" +
                "3. Quit\n" +
                "Choice: ");
    }

    public static int getUserChoice(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        int choice = 0;

        try
        {
            if (input != null && !input.isEmpty())
            {
                choice = Integer.parseInt(input);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.print("Incorrect format detected - ");
            choice = 0;
        }
        return choice;
    }

    public static void handleProductSeries(Scanner scanner)
    {
        System.out.println("\n=== PRODUCT SERIES CALCULATOR ===");
        int n = getPositiveInteger(scanner, "Enter the number of terms (n > 0): ");

        System.out.println("\nCalculating product series for n = " + n + ":");
        calculateProductSeries(n, 1, 1);
    }

    public static void handleSumSeries(Scanner scanner)
    {
        System.out.println("\n=== SUM SERIES CALCULATOR ===");
        int n = getPositiveInteger(scanner, "Enter the number of terms (n > 0): ");

        System.out.println("\nCalculating sum series for n = " + n + ":");
        calculateSumSeries(n, n, 0.0);
    }

    public static int getPositiveInteger(Scanner scanner, String prompt)
    {
        int number = 0;
        boolean validInput = false;

        while (!validInput)
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try
            {
                if (input != null && !input.isEmpty())
                {
                    number = Integer.parseInt(input);
                    if (number > 0)
                    {
                        validInput = true;
                    }
                    else
                    {
                        System.out.println("Error: Number must be greater than zero");
                    }
                }
                else
                {
                    System.out.println("Error: Please enter a valid number");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Error: Please enter a valid integer");
            }
        }
        return number;
    }

    public static void calculateProductSeries(int originalNum, int currentTerm, long runningProduct)
    {
        if (currentTerm == 1)
        {
            long currentProduct = 1;
            long newProduct = runningProduct * currentProduct;

            if (originalNum == 1)
            {
                System.out.println("1 = "+ newProduct);
            }
            else
            {
                System.out.print("1 * ");
                calculateProductSeries(originalNum, currentTerm + 1, newProduct);
            }
        }
        else if (currentTerm <= originalNum)
        {
            long currentProduct = (currentTerm - 1) + currentTerm;
            long newProduct = runningProduct * currentProduct;

            if (currentTerm == originalNum)
            {
                System.out.print("(" + (currentTerm - 1) + "+" + currentTerm + ") = " + newProduct);
            }
            else
            {
                System.out.print("(" + (currentTerm - 1) + "+" + currentTerm + ") * ");
                calculateProductSeries(originalNum, currentTerm + 1, newProduct);
            }
        }
    }

    public static void calculateSumSeries(int originalNum, int currentNum, double runningSum)
    {
        if (currentNum == 1)
        {
            double fraction = 1.0;
            double finalSum = runningSum + fraction;

            System.out.print("1 = "+ finalSum);
        }
        else if (currentNum > 1)
        {
            double fraction = 1.0 / (currentNum * currentNum);
            double newSum = runningSum + fraction;

            System.out.print("1/(" + currentNum + "*" + currentNum + ") + ");
            calculateSumSeries(originalNum, currentNum - 1, newSum);
        }
    }
}

/*


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: 5
Invalid choice. Please select a valid menu option


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: g
Incorrect format detected - Invalid choice. Please select a valid menu option


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: 1

=== PRODUCT SERIES CALCULATOR ===
Enter the number of terms (n > 0): 5

Calculating product series for n = 5:
1 * (1+2) * (2+3) * (3+4) * (4+5) = 945


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: 2

=== SUM SERIES CALCULATOR ===
Enter the number of terms (n > 0): 3

Calculating sum series for n = 3:
1/(3*3) + 1/(2*2) + 1 = 1.3611111111111112


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: -2
Invalid choice. Please select a valid menu option


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: 1

=== PRODUCT SERIES CALCULATOR ===
Enter the number of terms (n > 0): -3
Error: Number must be greater than zero
Enter the number of terms (n > 0): 0
Error: Number must be greater than zero
Enter the number of terms (n > 0): 1

Calculating product series for n = 1:
1 = 1


=== RECURSIVE SERIES CALCULATOR ===
1. Calculate Product Series:
2. Calculate Sum Series:
3. Quit
Choice: 3
Now exiting program...
 */
