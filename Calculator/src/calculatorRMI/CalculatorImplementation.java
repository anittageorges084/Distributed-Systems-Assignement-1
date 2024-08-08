package calculatorRMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private Stack<Integer> stack;

    protected CalculatorImplementation() throws RemoteException {
        stack = new Stack<>();
    }

    @Override
    public void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) return;

        List<Integer> values = new ArrayList<>();
        while (!stack.isEmpty()) {
            values.add(stack.pop());
        }

        int result = 0;
        switch (operator) {
            case "min":
                result = values.stream().min(Integer::compareTo).orElse(0);
                break;
            case "max":
                result = values.stream().max(Integer::compareTo).orElse(0);
                break;
            case "lcm":
                result = lcm(values);
                break;
            case "gcd":
                result = gcd(values);
                break;
        }

        stack.push(result);
    }

    @Override
    public int pop() throws RemoteException {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stack.pop();
    }

    @Override
    public List<Integer> getStackValues() throws RemoteException {
        return new ArrayList<>(stack);
    }

    private int gcd(List<Integer> values) {
        return values.stream().reduce(0, (a, b) -> gcd(a, b));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(List<Integer> values) {
        return values.stream().reduce(1, (a, b) -> lcm(a, b));
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}