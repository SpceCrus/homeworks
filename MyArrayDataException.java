public class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int col) {
        super("Ошибка в ячейке [" + row + "][" + col + "]: данные не являются числом");
    }
}