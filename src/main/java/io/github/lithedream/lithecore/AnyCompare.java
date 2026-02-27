package io.github.lithedream.lithecore;

import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class AnyCompare {
    private AnyCompare() {
    }

    // Unique per-instance ids (within JVM run), no leaks thanks to weak keys.
    private static final Object LOCK = new Object();
    private static final WeakHashMap<Object, Long> IDS = new WeakHashMap<>();
    private static final AtomicLong SEQ = new AtomicLong(1);

    public static int compare(Object a, Object b) {
        if (a == b)
            return 0;
        if (a == null)
            return -1;
        if (b == null)
            return 1;

        // Coherent with equals: only 0 when equals says so
        if (Objects.equals(a, b))
            return 0;

        // If same runtime class and Comparable, use natural order
        if (a.getClass() == b.getClass() && a instanceof Comparable) {
            @SuppressWarnings("unchecked")
            Comparable<Object> ca = (Comparable<Object>) a;
            try {
                int r = ca.compareTo(b);
                if (r != 0)
                    return r;
                // If r==0 but !equals (BigDecimal), we must break ties
            } catch (ClassCastException ignored) {
                // fall through
            }
        }

        // Deterministic fallback: identityHashCode first, then collision-proof id
        int ha = System.identityHashCode(a);
        int hb = System.identityHashCode(b);
        if (ha != hb)
            return Integer.compare(ha, hb);

        long ia = idOf(a);
        long ib = idOf(b);
        return Long.compare(ia, ib);
    }

    private static long idOf(Object o) {
        synchronized (LOCK) {
            Long id = IDS.get(o);
            if (id == null) {
                id = SEQ.getAndIncrement();
                IDS.put(o, id);
            }
            return id;
        }
    }
}