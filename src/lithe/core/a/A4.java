package lithe.core.a;

import lithe.core.A;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class A4<T0, T1, T2, T3> extends A {

    public A4(T0 t0, T1 t1, T2 t2, T3 t3) {
        super();
        s0(t0);
        s1(t1);
        s2(t2);
        s3(t3);
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

    public T3 g3() {
        return (T3) get(3);
    }

    public void s3(T3 val) {
        set(3, val);
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

    public T2 get2() {
        return (T2) get(2);
    }

    public void set2(T2 val) {
        set(2, val);
    }

    public T3 get3() {
        return (T3) get(3);
    }

    public void set3(T3 val) {
        set(3, val);
    }

    public static <K0, K1, K2, K3> List<K0> toList0(Collection<A4<K0, K1, K2, K3>> col) {
        return (List<K0>) A.toList(col, 0);
    }

    public static <K0, K1, K2, K3> List<K1> toList1(Collection<A4<K0, K1, K2, K3>> col) {
        return (List<K1>) A.toList(col, 1);
    }

    public static <K0, K1, K2, K3> List<K2> toList2(Collection<A4<K0, K1, K2, K3>> col) {
        return (List<K2>) A.toList(col, 2);
    }

    public static <K0, K1, K2, K3> List<K3> toList3(Collection<A4<K0, K1, K2, K3>> col) {
        return (List<K3>) A.toList(col, 3);
    }

    public static <K0, K1, K2, K3> Set<K0> toSet0(Collection<A4<K0, K1, K2, K3>> col) {
        return (Set<K0>) A.toSet(col, 0);
    }

    public static <K0, K1, K2, K3> Set<K1> toSet1(Collection<A4<K0, K1, K2, K3>> col) {
        return (Set<K1>) A.toSet(col, 1);
    }

    public static <K0, K1, K2, K3> Set<K2> toSet2(Collection<A4<K0, K1, K2, K3>> col) {
        return (Set<K2>) A.toSet(col, 2);
    }

    public static <K0, K1, K2, K3> Set<K3> toSet3(Collection<A4<K0, K1, K2, K3>> col) {
        return (Set<K3>) A.toSet(col, 3);
    }

    @Override
    public int size() {
        return 4;
    }
}
