import java.util.Scanner;

public class RPNCalculator {

    private int[] Arr = new int[10];
    private int size = 0;

    public void push(int n) {
        Arr[size] = n;
        size++;

        if (size >= Arr.length) {
            int[] newEntries = new int[Arr.length * 2];
            System.arraycopy(Arr, 0, newEntries, 0, Arr.length);
            Arr = newEntries;
        }
    }

    public int pop() {
        if (size == 0)
            return -1;
        else {
            size--;
            return Arr[size];
        }
    }

    public void clear() {
        Arr = new int[10];
    }

    //    Runtime exception
    static class RPNException extends RuntimeException {

    }

    int evaluate(String expr) {
        String[] Array2 = expr.split(" ");

        for (int i = 0; i < Array2.length; i++) {
            try {
                int x = Integer.parseInt(Array2[i]);
                push(x);

            } catch (NumberFormatException nfe) {
                if (size > 1) {
                    int y, z;
                    y = pop();
                    z = pop();
                    int num;
                    switch (Array2[i]) {
                        case "+":
                            num = z + y;
                            push(num);
                            break;

                        case "-":
                            num = z - y;
                            push(num);
                            break;

                        case "*":
                            num = z * y;
                            push(num);
                            break;

                        case "/":
                            num = z / y;
                            push(num);
                            break;
                    }
                } else {
                    throw new RPNException();
                }
            }
        }

        if (size == 1) {
            return pop();
        } else {
            throw new RPNException();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the numbers");
        String sc = scanner.nextLine();
        RPNCalculator calculator = new RPNCalculator();
        System.out.println(calculator.evaluate(sc));
    }


}


