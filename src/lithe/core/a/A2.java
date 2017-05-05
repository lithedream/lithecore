package lithe.core.a;

import lithe.core.A;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class A2<T0, T1> extends A {

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


    public T0 get0() {
        return (T0) get(0);
    }

    public void set0(T0 val) {
        set(0, val);
    }

    public T1 get1() {
        return (T1) get(1);
    }

    public void set1(T1 val) {
        set(1, val);
    }

    public static <K0, K1> List<K0> toList0(Collection<A2<K0, K1>> col) {
        return (List<K0>) A.toList(col, 0);
    }

    public static <K0, K1> List<K1> toList1(Collection<A2<K0, K1>> col) {
        return (List<K1>) A.toList(col, 1);
    }

    public static <K0, K1> Set<K0> toSet0(Collection<A2<K0, K1>> col) {
        return (Set<K0>) A.toSet(col, 0);
    }

    public static <K0, K1> Set<K1> toSet1(Collection<A2<K0, K1>> col) {
        return (Set<K1>) A.toSet(col, 1);
    }


    @Override
    public int size() {
        return 2;
    }
}
