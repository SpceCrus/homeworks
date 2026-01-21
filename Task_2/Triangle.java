package Task_2;

public class Triangle implements Figure {
    private double a, b, c;
    private String fill, border;

    public Triangle(double a, double b, double c, String fill, String border) {
        this.a = a; this.b = b; this.c = c;
        this.fill = fill; this.border = border;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double getPerimeter() { return a + b + c; }

    @Override
    public String getFillColor() { return fill; }

    @Override
    public String getBorderColor() { return border; }
}