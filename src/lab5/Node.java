package lab5;

abstract public class Node {
    int sign = 1;

    Node minus() {
        this.sign = -1;
        return this;
    }

    Node plus() {
        this.sign = 1;
        return this;
    }

    int getSign() {
        return this.sign;
    }

    /**
     * Oblicza wartość wyrażenia dla danych wartości zmiennych
     * występujących w wyrażeniu
     */
    public abstract double evaluate();

    /**
     * zwraca tekstową reprezentację wyrażenia
     */
    public String toString() {
        return "";
    }

    /**
     * Zwraca liczbę argumentów węzła
     */
    int getArgumentsCount() {
        return 0;
    }

    public abstract Node diff(Variable var);

    public abstract boolean isZero();

    public Node simplify() {
        return this;
    }
}
