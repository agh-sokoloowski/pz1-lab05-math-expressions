package lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    public Prod() {
    }

    public Prod(Node n1) {
        args.add(n1);
    }

    public Prod(double c) {
        // TODO: wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
        new Constant(c);
    }

    public Prod(Node n1, Node n2) {
        this.args.add(n1);
        this.args.add(n2);
    }

    public Prod(double c, Node n) {
        // TODO: wywołaj konstruktor dwuargumentowy
        this.args.add(new Constant(c));
        this.args.add(n);
    }

    Prod mul(Node n) {
        args.add(n);
        return this;
    }

    Prod mul(double c) {
        // TODO: ???
        this.args.add(new Constant(c));
        return this;
    }

    @Override
    public double evaluate() {
        double result = 1;
        // TODO: oblicz iloczyn czynników wołąjąc ich metodę evaluate
        for (Node n : this.args) {
            result *= n.evaluate();
        }
        return sign * result;
    }

    int getArgumentsCount() {
        return args.size();
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        if (this.sign < 0) b.append("-");
        // TODO: zaimplementuj
        for (Node n : this.args) {
            b.append(n);
            if (n != this.args.get(this.args.size() - 1)) {
                b.append("*");
            }
        }
        return b.toString();
    }

    @Override
    public Node diff(Variable var) {
        Sum r = new Sum();
        for (int i = 0; i < this.args.size(); i++) {
            Prod m = new Prod();
            for (int j = 0; j < this.args.size(); j++) {
                Node f = this.args.get(j);
                if (j == i) {
                    m.mul(f.diff(var));
                } else {
                    m.mul(f);
                }
            }
            if (!m.isZero()) {
                r.add(m.simplify());
            }
        }
        return r;
    }

    @Override
    public boolean isZero() {
        for (Node n : this.args) {
            if (n.isZero()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Node simplify() {
        Prod res = new Prod();
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
            res.mul(r);
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