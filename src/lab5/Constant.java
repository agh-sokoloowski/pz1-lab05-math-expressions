package lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node {
    double value;

    public Constant(double value) {
        this.sign = value < 0 ? -1 : 1;
        this.value = value < 0 ? -value : value;
    }

    @Override
    public double evaluate() {
        return sign * value;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (this.sign < 0) b.append("(-");

        DecimalFormat format = new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.US));
        b.append(format.format(this.value));

        if (this.sign < 0) b.append(")");
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        return new Constant(0);
    }

    @Override
    public boolean isZero() {
        return this.value == 0;
    }
}
