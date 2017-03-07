package lithe.core.a;

import lithe.core.A;

public class A2<T0, T1> extends A {

    public A2(T0 t0, T1 t1) {
        super();
        s0(t0);
        s1(t1);
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

    @Override
    public int size() {
        return 2;
    }
}
