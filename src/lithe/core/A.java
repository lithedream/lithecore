package lithe.core;

import lithe.core.a.A2;
import lithe.core.a.A3;

import java.io.Serializable;

public abstract class A implements Serializable, Comparable<A> {
    private Object[] objs;

    public static <T0, T1> A2<T0, T1> $(T0 t0, T1 t1) {
        return new A2<T0, T1>(t0, t1);
    }

    public static <T0, T1, T2> A3<T0, T1, T2> $(T0 t0, T1 t1, T2 t2) {
        return new A3<T0, T1, T2>(t0, t1, t2);
    }

    protected Object get(int i) {
        return objs[i];
    }

    protected void set(int i, Object val) {
        objs[i] = val;
    }

    public abstract int size();

    public A(){
        objs=new Object[size()];
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
}
