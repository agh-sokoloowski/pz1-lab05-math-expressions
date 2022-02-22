package lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {
    List<Node> args = new ArrayList<>();

    public Sum() {
    }

    public Sum(Node n1, Node n2) {
        this.args.add(n1);
        this.args.add(n2);
    }

    public Sum add(Node n) {
        this.args.add(n);
        return this;
    }

    public Sum add(double c) {
        this.args.add(new Constant(c));
        return this;
    }

    public Sum add(double c, Node n) {
        Node mul = new Prod(c, n);
        args.add(mul);
        return this;
    }

    @Override
    public double evaluate() {
        double result = 0;
        // TODO: oblicz sumę wartości zwróconych przez wywołanie evaluate składników sumy
        for (Node n : this.args) {
            result += n.evaluate();
        }
        return this.sign * result;
    }

    int getArgumentsCount() {
        return this.args.size();
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        if (this.sign < 0) {
            b.append("-(");
        }

        // TODO: zaimplementuj
        for (Node n : this.args) {
            b.append(n);
            if (n != this.args.get(this.args.size() - 1)) {
                b.append(" + ");
            }
        }

        if (this.sign < 0) {
            b.append(")");
        }
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        Sum r = new Sum();
        for (Node n : this.args) {
            if (!n.diff(var).isZero()) {
                r.add(n.diff(var).simplify());
            }
        }
        return r;
    }

    @Override
    public boolean isZero() {
        for (Node n : this.args) {
            if (!n.isZero()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Node simplify() {
        Sum res = new Sum();
        for (Node s : this.args) {
            Node r = s.simplify();
            if (r instanceof Constant) {
                if (((Constant) r).value == 0) {
                    return new Constant(0);
                }
                if (((Constant) r).value == 1){
                    continue;
                }
            }
            res.add(r);
        }
        if (res.args.size() > 1) {
            return res;
        } else if (res.args.size() == 0) {
            return new Constant(0);
        } else {
            return res.args.get(0);
        }
    }
}
