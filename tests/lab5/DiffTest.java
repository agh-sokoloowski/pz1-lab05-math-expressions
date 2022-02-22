package lab5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiffTest {
    @Test
    public void testDiffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2, new Power(x, 3))
                .add(new Power(x, 2))
                .add(-2, x)
                .add(7);
        assertEquals("2*x^3 + x^2 + (-2)*x + 7", exp.toString());

        Node d = exp.diff(x);
        assertEquals("2*3*x^2 + 2*x^1 + (-2)", d.toString());
    }

    @Test
    public void testDiffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        assertEquals("x^2 + y^2 + 8*x + 4*y + 16", circle.toString());

        Node dx = circle.diff(x);
        assertEquals("2*x^1 + 8", dx.toString());

        Node dy = circle.diff(y);
        assertEquals("2*y^1 + 4", dy.toString());

    }
}
