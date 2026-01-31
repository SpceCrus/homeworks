import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorJUnitTest {
    Calculator calc = new Calculator();

    @Test
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    void testArea() {
        assertEquals(10.0, calc.triangleArea(4, 5));
    }

    @Test
    void testMath() {
        assertEquals(10, calc.add(7, 3));
    }

    @Test
    void testCompare() {
        assertTrue(calc.isGreater(10, 5));
    }
}