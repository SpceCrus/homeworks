public class Calculator {

    public long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Число должно быть положительным");
        long result = 1;
        for (int i = 1; i <= n; i++) result *= i;
        return result;
    }

    public double triangleArea(double base, double height) {
        return 0.5 * base * height;
    }

    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }
    public int multiply(int a, int b) {
        return a * b;
    }
    public double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("При делении на ноль схлопывается вселенная, нельзя этого допустить");
        return (double) a / b;
    }


    public boolean isGreater(int a, int b) {
        return a > b;
    }

}