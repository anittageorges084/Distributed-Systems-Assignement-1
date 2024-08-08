package calculatorRMI;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost:1098/CalculatorService"); 

            Scanner scanner = new Scanner(System.in);
            int option;

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Push Value");
                System.out.println("2. Push Operation");
                System.out.println("3. Pop");
                System.out.println("4. Check if Stack is Empty");
                System.out.println("5. Delay Pop");
                System.out.println("6. Show Stack");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                
                try {
                    option = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    continue;
                }

                switch (option) {
                    case 1:
                        System.out.print("Enter a value to push: ");
                        int value = Integer.parseInt(scanner.nextLine());
                        calculator.pushValue(value);
                        System.out.println("Value pushed.");
                        break;
                    case 2:
                        System.out.print("Enter an operation (min, max, lcm, gcd): ");
                        String operator = scanner.nextLine();
                        if (operator.equals("min") || operator.equals("max") || operator.equals("lcm") || operator.equals("gcd")) {
                            calculator.pushOperation(operator);
                            System.out.println("Operation pushed.");
                        } else {
                            System.out.println("Invalid operation. Please enter one of: min, max, lcm, gcd.");
                        }
                        break;
                    case 3:
                        int result = calculator.pop();
                        System.out.println("Popped value: " + result);
                        break;
                    case 4:
                        boolean isEmpty = calculator.isEmpty();
                        System.out.println("Stack is empty: " + isEmpty);
                        break;
                    case 5:
                        System.out.print("Enter delay in milliseconds: ");
                        int millis = Integer.parseInt(scanner.nextLine());
                        int delayedResult = calculator.delayPop(millis);
                        System.out.println("Popped value after delay: " + delayedResult);
                        break;
                    case 6:
                        List<Integer> stackValues = calculator.getStackValues();
                        System.out.println("Current stack values: " + stackValues);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Unknown option. Please enter a number between 1 and 7.");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
