import java.util.Arrays;

public class HomeWorkApp {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(checkSumRange(15, 7));
        isPositiveOrNegative(0);
        System.out.println("Число отрицательное? - " + IsNegative(-3));
        printStringMutipleTimes("Java - круто! 980322", 3);
        System.out.println("2025 високосный? - " + isleapYear(2025));
        int[] arr10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertArray(arr10);
        System.out.println("Инверсия массива: " + Arrays.toString(arr10));
        int[] arr11 = fillArrayWithHundred();
        System.out.println("Массив от 1 до 100: " + Arrays.toString(arr11));
        int[] arr12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplySmallNumbers(arr12);
        System.out.println("После умножения < 6: " + Arrays.toString(arr12));
        int[][] matrix = createDiagonalMatrix(5);
        System.out.println("Квадратный диагональ)):");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        int[] arr14 = createCustomArray(8, 7);
        System.out.println("Кастомный массив: " + Arrays.toString(arr14));

    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSign() {
        int a = 15;
        int b = -5;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 150;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 20;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean checkSumRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void isPositiveOrNegative(int x) {
        if (x >= 0) {
            System.out.println("Число " + x + " положжительное");
        } else {
            System.out.println("Число " + x + " отрицательное");
        }
    }

    public static boolean IsNegative(int x) {
        return x < 0;
    }

    public static void printStringMutipleTimes(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(str);
        }
    }

    public static boolean isleapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
    }

    public static int[] fillArrayWithHundred() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static void multiplySmallNumbers(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    public static int[][] createDiagonalMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        return matrix;
    }

    public static int[] createCustomArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}