package Task_2;

public class Rectangle implements Figure {
    private double width, height;
    private String fill, border;

    public Rectangle(double width, double height, String fill, String border) {
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.border = border;
    }

    @Override
    public double getArea() { return width * height; }

    @Override
    public double getPerimeter() { return 2 * (width + height); }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}