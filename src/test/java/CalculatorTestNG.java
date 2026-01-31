import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTestNG {
    Calculator calc = new Calculator();

    @Test
    public void testFactorial() {
        Assert.assertEquals(calc.factorial(3), 6, "Факториал 3 должен быть 6");
    }

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(calc.triangleArea(10, 5), 25.0);
    }

    @Test
    public void testArithmetic() {
        Assert.assertEquals(calc.add(10, 20), 30);
        Assert.assertEquals(calc.subtract(50, 10), 40);
    }

    @Test
    public void testComparison() {
        Assert.assertTrue(calc.isGreater(5, 1));
    }
}