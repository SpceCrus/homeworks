package Task_2;

public class Circle implements Figure {
    private double radius;
    private String fill, border;

    public Circle(double radius, String fill, String border) {
        this.radius = radius;
        this.fill = fill;
        this.border = border;
    }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * radius; }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}