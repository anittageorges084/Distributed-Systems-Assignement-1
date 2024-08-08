package calculatorRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import calculatorRMI.Calculator;
import calculatorRMI.CalculatorImplementation;

public class CalculatorServer {
	public static void main(String[] args) {
        try {
          
            LocateRegistry.createRegistry(1098);

            
            Calculator calculator = new CalculatorImplementation();

           
            Naming.rebind("rmi://localhost:1098/CalculatorService", calculator);

           
            System.out.println("CalculatorService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}