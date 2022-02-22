package lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Power extends Node {
    double p;
    Node arg;

    public Power(Node n, double p) {
        this.arg = n;
        this.p = p;
    }

    @Override
    public double evaluate() {
        double argVal = arg.evaluate();
        return Math.pow(argVal, p);
    }

    int getArgumentsCount() {
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (this.sign < 0) b.append("-");
        int argSign = this.arg.getSign();
        int cnt = this.arg.getArgumentsCount();
        boolean useBracket = argSign < 0 || cnt > 1;
        String argString = arg.toString();
        if (useBracket) b.append("(");
        b.append(argString);
        if (useBracket) b.append(")");

        b.append("^");

        DecimalFormat format = new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.US));
        b.append(format.format(this.p));
        return b.toString();
    }

    public Node diff(Variable var) {
        Prod r = new Prod(sign * p, new Power(arg, p - 1));
        r.mul(arg.diff(var));
        return r;
    }

    @Override
    public boolean isZero() {
        return this.arg.isZero();
    }
}
