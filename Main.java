public class Main {

    public static void main(String[] args) {
        String[][] correctArray = {
                {"10", "5", "5", "5"},
                {"7", "8", "5", "5"},
                {"15", "5", "2", "3",},
                {"10", "10", "4", "1"}
        };

        try {
            int result = sumArrayElements(correctArray);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        System.out.println("---Задание 4: ArrayIndexOutOfBoundsException--");
        generateAndCatchOutOfBounds();
    }

    public static int sumArrayElements(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Массив должен иметь ровно 4 строки.");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("В строке " + i + " количество столбцов не равно 4.");
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    public static void generateAndCatchOutOfBounds() {
        try {
            int[] smallArray = {1, 2, 3};
            int fail = smallArray[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("поймали выход за пределы массива: " + e.toString());
        }
    }
}