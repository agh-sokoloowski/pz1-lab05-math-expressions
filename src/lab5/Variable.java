package lab5;

public class Variable extends Node {
    String name;
    Double value;

    public Variable(String name) {
        this.name = name;
    }

    public void setValue(double d) {
        value = d;
    }


    @Override
    public double evaluate() {
        return this.sign * this.value;
    }


    @Override
    public String toString() {
        String sgn = this.sign < 0 ? "-" : "";
        return sgn + this.name;
    }

    public Node diff(Variable var) {
        if (var.name.equals(name)) return new Constant(sign);
        else return new Constant(0);
    }

    @Override
    public boolean isZero() {
        return this.value != null && this.value == 0;
    }
}
