package lithe.core.a;

import lithe.core.A;

public class A3<T0, T1, T2> extends A {

    public A3(T0 t0, T1 t1, T2 t2) {
        super();
        s0(t0);
        s1(t1);
        s2(t2);
    }

    public T0 g0() {
        return (T0) get(0);
    }

    public void s0(T0 val) {
        set(0, val);
    }

    public T1 g1() {
        return (T1) get(1);
    }

    public void s1(T1 val) {
        set(1, val);
    }

    public T2 g2() {
        return (T2) get(2);
    }

    public void s2(T2 val) {
        set(2, val);
    }

    @Override
    public int size() {
        return 3;
    }
}
