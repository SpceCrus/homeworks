package Task_2;

public class MainTask2 {
    public static void main(String[] args) {
        Figure[] figures = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(4, 10, "Зеленый", "Белый"),
                new Triangle(3, 4, 5, "Синий", "Желтый")
        };

        System.out.println("----Характеристики фигур----");

        for (Figure f : figures) {
            f.printInfo();
        }
    }
}