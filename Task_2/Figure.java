package Task_2;

public interface Figure {
        double getArea();
        double getPerimeter();
        String getFillColor();
        String getBorderColor();

        default void printInfo() {
            System.out.println("Фигура: " + this.getClass().getSimpleName());
            System.out.printf("Площадь: %.2f | Периметр: %.2f%n", getArea(), getPerimeter());
            System.out.println("Цвет фона: " + getFillColor() + ", Цвет границы: " + getBorderColor());
            System.out.println("------------------------------------------");
        }
    }
