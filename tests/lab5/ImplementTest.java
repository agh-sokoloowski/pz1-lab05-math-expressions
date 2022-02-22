package lab5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

public class ImplementTest {
    @Test
    public void testBuildAndPrint() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);

        assertEquals("2.1*x^3 + x^2 + (-2)*x + 7", exp.toString());
    }

    @Test
    public void testBuildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);

        x.setValue(-1);
        assertEquals(0, exp.evaluate());
        x.setValue(1);
        assertEquals(0, exp.evaluate());
        x.setValue(2);
        assertEquals(0, exp.evaluate());

        x.setValue(-2);
        assertNotEquals(0, exp.evaluate());
    }

    @Test
    public void testDefineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);

        assertEquals("x^2 + y^2 + 8*x + 4*y + 16", circle.toString());

        x.setValue(-4);
        y.setValue(-2);
        assertTrue(circle.evaluate() < 0);

        x.setValue(4);
        y.setValue(2);
        assertTrue(circle.evaluate() >= 0);
    }
}
