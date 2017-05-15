package lithe.core.f;


import lithe.core.F;
import lithe.core.X;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class FUtils {

    private static FUtils instance;

    private FUtils() {

    }

    public static FUtils getInstance() {
        if (instance == null) {
            instance = new FUtils();
        }
        return instance;
    }

    public static final class ComparatorGate<T> implements Comparator<T> {

        private final F.Gate<T, ?> gate;

        private boolean straight = true;

        private LinkedList<ComparatorGate<T>> nexts = null;

        private ComparatorGate(F.Gate<T, ?> gate) {
            this.gate = gate;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public int compare(T o1, T o2) {
            Object on1 = gate.on(o1);
            Object on2 = gate.on(o2);
            int compare = straight ? X.compare((Comparable) on1, (Comparable) on2) : X.compare((Comparable) on2, (Comparable) on1);
            if (compare == 0) {
                for (ComparatorGate<T> cg : X.liSafeIter(nexts)) {
                    compare = cg.compare(o1, o2);
                    if (compare == 0) {
                        break;
                    }
                }
            }

            return compare;
        }

        public ComparatorGate<T> invert() {
            if (nexts == null) {
                straight = !straight;
            } else {
                nexts.getLast().invert();
            }
            return this;
        }

        public ComparatorGate<T> then(F.Gate<T, ?> g) {
            if (nexts == null) {
                nexts = new LinkedList<ComparatorGate<T>>();
            }
            nexts.addLast(new ComparatorGate<T>(gate));
            return this;
        }
    }

    public final <T> ComparatorGate<T> comparator(F.Gate<T, ?> gate) {
        return new ComparatorGate<T>(gate);
    }


    @SafeVarargs
    public final <T> List<T> enchant(Class<T> cl, List<Object[]> objs, F.Gate<T, ?>... gates) {
        try {
            List<T> li = new ArrayList<>();
            for (Object[] obj : objs) {
                T newModel = cl.newInstance();
                for (int i = 0; i < obj.length; i++) {
                    ((F.Gate<T, Object>) gates[i]).put(newModel, obj[i]);
                }
                li.add(newModel);
            }
            return li;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
