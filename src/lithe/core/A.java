package lithe.core;

import lithe.core.a.A2;
import lithe.core.a.A3;
import lithe.core.a.A4;
import lithe.core.a.A5;

import java.io.Serializable;
import java.util.*;

public abstract class A implements Serializable, Comparable<A> {
    private Object[] objs;

    public static <T0, T1> A2<T0, T1> $(T0 t0, T1 t1) {
        return new A2<T0, T1>(t0, t1);
    }

    public static <T0, T1, T2> A3<T0, T1, T2> $(T0 t0, T1 t1, T2 t2) {
        return new A3<T0, T1, T2>(t0, t1, t2);
    }

    public static <T0, T1, T2, T3> A4<T0, T1, T2, T3> $(T0 t0, T1 t1, T2 t2, T3 t3) {
        return new A4<T0, T1, T2, T3>(t0, t1, t2, t3);
    }

    public static <T0, T1, T2, T3, T4> A5<T0, T1, T2, T3, T4> $(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
        return new A5<T0, T1, T2, T3, T4>(t0, t1, t2, t3, t4);
    }

    protected Object get(int i) {
        return objs[i];
    }

    protected void set(int i, Object val) {
        objs[i] = val;
    }

    protected static List<Object> toList(Collection<? extends A> col, int i) {
        List<Object> list = new ArrayList<Object>();
        for (A a : col) {
            list.add(a.get(i));
        }
        return list;
    }

    protected static Set<Object> toSet(Collection<? extends A> col, int i) {
        Set<Object> list = new LinkedHashSet<Object>();
        for (A a : col) {
            list.add(a.get(i));
        }
        return list;
    }

    public abstract int size();

    public A() {
        objs = new Object[size()];
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof A)) {
            return false;
        }
        A casted = (A) obj;
        if (size() != casted.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (X.notEqual(get(i), casted.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(A o) {
        int compare = X.compare(size(), o.size());
        if (compare == 0) {
            for (int i = 0; i < size(); i++) {
                compare = X.compare((Comparable) get(i), (Comparable) o.get(i));
                if (compare != 0) {
                    break;
                }
            }
        }
        return compare;
    }

    @Override
    public int hashCode() {
        return X.hash(objs);
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0; i < size(); i++) {
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('A');
        sb.append(size());
        sb.append('[');
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object get = get(i);
            sb.append(get == this ? "(this A)" : get);
        }
        sb.append(']');
        return sb.toString();
    }

    public static <T extends A> void sortBy(List<T> list, final int pos) {
        Collections.sort(list, new Comparator<T>() {
            @SuppressWarnings({"rawtypes"})
            @Override
            public int compare(T o1, T o2) {
                return X.compare((Comparable) o1.get(pos), (Comparable) o2.get(pos));
            }
        });
    }

    @SuppressWarnings("rawtypes")
    public static <T extends A> T min(Collection<T> col, final int pos) {
        T min = null;
        Comparable val = null;
        for (T t : col) {
            if (min == null || X.compare((Comparable) t.get(pos), val) < 0) {
                min = t;
                val = (Comparable) t.get(pos);
            }
        }
        return min;
    }

    @SuppressWarnings("rawtypes")
    public static <T extends A> T max(Collection<T> col, final int pos) {
        T max = null;
        Comparable val = null;
        for (T t : col) {
            if (max == null || X.compare((Comparable) t.get(pos), val) > 0) {
                max = t;
                val = (Comparable) t.get(pos);
            }
        }
        return max;
    }


    public static <K0, K1> Map<K0, K1> toMap2(Collection<A2<K0, K1>> col) {
        Map<K0, K1> map = new LinkedHashMap<K0, K1>();
        for (A2<K0, K1> a2 : col) {
            map.put(a2.g0(), a2.g1());
        }
        return map;
    }

    public static <K0, K1, K2> Map<K0, A2<K1, K2>> toMap3(Collection<A3<K0, K1, K2>> col) {
        Map<K0, A2<K1, K2>> map = new LinkedHashMap<K0, A2<K1, K2>>();
        for (A3<K0, K1, K2> a3 : col) {
            map.put(a3.g0(), A.$(a3.g1(), a3.g2()));
        }
        return map;
    }

    public static <K0, K1, K2, K3> Map<K0, A3<K1, K2, K3>> toMap4(Collection<A4<K0, K1, K2, K3>> col) {
        Map<K0, A3<K1, K2, K3>> map = new LinkedHashMap<K0, A3<K1, K2, K3>>();
        for (A4<K0, K1, K2, K3> a4 : col) {
            map.put(a4.g0(), A.$(a4.g1(), a4.g2(), a4.g3()));
        }
        return map;
    }

    public static <K0, K1, K2, K3, K4> Map<K0, A4<K1, K2, K3, K4>> toMap5(Collection<A5<K0, K1, K2, K3, K4>> col) {
        Map<K0, A4<K1, K2, K3, K4>> map = new LinkedHashMap<K0, A4<K1, K2, K3, K4>>();
        for (A5<K0, K1, K2, K3, K4> a5 : col) {
            map.put(a5.g0(), A.$(a5.g1(), a5.g2(), a5.g3(), a5.g4()));
        }
        return map;
    }


    public static <K0, K1> Map<K0, List<K1>> toMapList2(Collection<A2<K0, K1>> col) {
        Map<K0, List<K1>> map = new LinkedHashMap<K0, List<K1>>();
        for (A2<K0, K1> a2 : col) {
            List<K1> list = map.get(a2.g0());
            if (list == null) {
                list = new ArrayList<K1>();
                map.put(a2.g0(), list);
            }
            list.add(a2.g1());
        }
        return map;
    }

    public static <K0, K1, K2> Map<K0, List<A2<K1, K2>>> toMapList3(Collection<A3<K0, K1, K2>> col) {
        Map<K0, List<A2<K1, K2>>> map = new LinkedHashMap<K0, List<A2<K1, K2>>>();
        for (A3<K0, K1, K2> a3 : col) {
            List<A2<K1, K2>> v = map.get(a3.g0());
            if (v == null) {
                v = new ArrayList<A2<K1, K2>>();
                map.put(a3.g0(), v);
            }
            v.add(A.$(a3.g1(), a3.g2()));
        }
        return map;
    }

    public static <K0, K1, K2, K3> Map<K0, List<A3<K1, K2, K3>>> toMapList4(Collection<A4<K0, K1, K2, K3>> col) {
        Map<K0, List<A3<K1, K2, K3>>> map = new LinkedHashMap<K0, List<A3<K1, K2, K3>>>();
        for (A4<K0, K1, K2, K3> a4 : col) {
            List<A3<K1, K2, K3>> v = map.get(a4.g0());
            if (v == null) {
                v = new ArrayList<A3<K1, K2, K3>>();
                map.put(a4.g0(), v);
            }
            v.add(A.$(a4.g1(), a4.g2(), a4.g3()));
        }
        return map;
    }

    public static <K0, K1, K2, K3, K4> Map<K0, List<A4<K1, K2, K3, K4>>> toMapList5(Collection<A5<K0, K1, K2, K3, K4>> col) {
        Map<K0, List<A4<K1, K2, K3, K4>>> map = new LinkedHashMap<K0, List<A4<K1, K2, K3, K4>>>();
        for (A5<K0, K1, K2, K3, K4> a5 : col) {
            List<A4<K1, K2, K3, K4>> v = map.get(a5.g0());
            if (v == null) {
                v = new ArrayList<A4<K1, K2, K3, K4>>();
                map.put(a5.g0(), v);
            }
            v.add(A.$(a5.g1(), a5.g2(), a5.g3(), a5.g4()));
        }
        return map;
    }

    public static <K0, K1> Map<K0, Set<K1>> toMapSet2(Collection<A2<K0, K1>> col) {
        Map<K0, Set<K1>> map = new LinkedHashMap<K0, Set<K1>>();
        for (A2<K0, K1> a2 : col) {
            Set<K1> set = map.get(a2.g0());
            if (set == null) {
                set = new LinkedHashSet<K1>();
                map.put(a2.g0(), set);
            }
            set.add(a2.g1());
        }
        return map;
    }

    public static <K0, K1, K2> Map<K0, Set<A2<K1, K2>>> toMapSet3(Collection<A3<K0, K1, K2>> col) {
        Map<K0, Set<A2<K1, K2>>> map = new LinkedHashMap<K0, Set<A2<K1, K2>>>();
        for (A3<K0, K1, K2> a3 : col) {
            Set<A2<K1, K2>> set = map.get(a3.g0());
            if (set == null) {
                set = new LinkedHashSet<A2<K1, K2>>();
                map.put(a3.g0(), set);
            }
            set.add(A.$(a3.g1(), a3.g2()));
        }
        return map;
    }

    public static <K0, K1, K2, K3> Map<K0, Set<A3<K1, K2, K3>>> toMapSet4(Collection<A4<K0, K1, K2, K3>> col) {
        Map<K0, Set<A3<K1, K2, K3>>> map = new LinkedHashMap<K0, Set<A3<K1, K2, K3>>>();
        for (A4<K0, K1, K2, K3> a4 : col) {
            Set<A3<K1, K2, K3>> set = map.get(a4.g0());
            if (set == null) {
                set = new LinkedHashSet<A3<K1, K2, K3>>();
                map.put(a4.g0(), set);
            }
            set.add(A.$(a4.g1(), a4.g2(), a4.g3()));
        }
        return map;
    }

    public static <K0, K1, K2, K3, K4> Map<K0, Set<A4<K1, K2, K3, K4>>> toMapSet5(Collection<A5<K0, K1, K2, K3, K4>> col) {
        Map<K0, Set<A4<K1, K2, K3, K4>>> map = new LinkedHashMap<K0, Set<A4<K1, K2, K3, K4>>>();
        for (A5<K0, K1, K2, K3, K4> a5 : col) {
            Set<A4<K1, K2, K3, K4>> set = map.get(a5.g0());
            if (set == null) {
                set = new LinkedHashSet<A4<K1, K2, K3, K4>>();
                map.put(a5.g0(), set);
            }
            set.add(A.$(a5.g1(), a5.g2(), a5.g3(), a5.g4()));
        }
        return map;
    }

    public static <K0, K1, K2> Map<A2<K0, K1>, K2> toMap3byA2(Collection<A3<K0, K1, K2>> col) {
        Map<A2<K0, K1>, K2> map = new LinkedHashMap<A2<K0, K1>, K2>();
        for (A3<K0, K1, K2> a3 : col) {
            map.put(A.$(a3.g0(), a3.g1()), a3.g2());
        }
        return map;
    }

    public static <K0, K1, K2, K3> Map<A3<K0, K1, K2>, K3> toMap4byA3(Collection<A4<K0, K1, K2, K3>> col) {
        Map<A3<K0, K1, K2>, K3> map = new LinkedHashMap<A3<K0, K1, K2>, K3>();
        for (A4<K0, K1, K2, K3> a4 : col) {
            map.put(A.$(a4.g0(), a4.g1(), a4.g2()), a4.g3());
        }
        return map;
    }


    public static <K0, K1, K2, K3, K4> Map<A4<K0, K1, K2, K3>, K4> toMap5byA4(Collection<A5<K0, K1, K2, K3, K4>> col) {
        Map<A4<K0, K1, K2, K3>, K4> map = new LinkedHashMap<A4<K0, K1, K2, K3>, K4>();
        for (A5<K0, K1, K2, K3, K4> a5 : col) {
            map.put(A.$(a5.g0(), a5.g1(), a5.g2(), a5.g3()), a5.g4());
        }
        return map;
    }


    public static <K0, K1, K2> Map<A2<K0, K1>, List<K2>> toMapList3byA2(Collection<A3<K0, K1, K2>> col) {
        Map<A2<K0, K1>, List<K2>> map = new LinkedHashMap<A2<K0, K1>, List<K2>>();
        for (A3<K0, K1, K2> a2 : col) {
            A2<K0, K1> key = A.$(a2.g0(), a2.g1());
            List<K2> list = map.get(key);
            if (list == null) {
                list = new ArrayList<K2>();
                map.put(key, list);
            }
            list.add(a2.g2());
        }
        return map;
    }

    public static <K0, K1, K2, K3> Map<A3<K0, K1, K2>, List<K3>> toMapList4byA3(Collection<A4<K0, K1, K2, K3>> col) {
        Map<A3<K0, K1, K2>, List<K3>> map = new LinkedHashMap<A3<K0, K1, K2>, List<K3>>();
        for (A4<K0, K1, K2, K3> a2 : col) {
            A3<K0, K1, K2> key = A.$(a2.g0(), a2.g1(), a2.g2());
            List<K3> list = map.get(key);
            if (list == null) {
                list = new ArrayList<K3>();
                map.put(key, list);
            }
            list.add(a2.g3());
        }
        return map;
    }

    public static <K0, K1, K2, K3, K4> Map<A4<K0, K1, K2, K3>, List<K4>> toMapList5byA4(Collection<A5<K0, K1, K2, K3, K4>> col) {
        Map<A4<K0, K1, K2, K3>, List<K4>> map = new LinkedHashMap<A4<K0, K1, K2, K3>, List<K4>>();
        for (A5<K0, K1, K2, K3, K4> a5 : col) {
            A4<K0, K1, K2, K3> key = A.$(a5.g0(), a5.g1(), a5.g2(), a5.g3());
            List<K4> list = map.get(key);
            if (list == null) {
                list = new ArrayList<K4>();
                map.put(key, list);
            }
            list.add(a5.g4());
        }
        return map;
    }

}
