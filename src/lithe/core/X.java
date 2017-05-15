package lithe.core;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class X {

    public static boolean equal(Object o1, Object o2) {
        if (o1 != null && o2 != null) {
            return o1.equals(o2);
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return false;
    }


    public static int compare(Comparable o1, Comparable o2) {
        if (o1 != null && o2 != null) {
            return o1.compareTo(o2);
        }
        if (o1 == null && o2 != null) {
            return -1;
        }
        if (o1 != null && o2 == null) {
            return 1;
        }
        return 0;
    }

    public static boolean notEqual(Object o1, Object o2) {
        return !equal(o1, o2);
    }


    public static int hash(Object... o) {
        int hc = 17;
        for (Object obj : o) {
            if (obj == null) {
                hc = hc * 37;
            } else {
                hc = hc * 37 + obj.hashCode();
            }
        }
        return hc;
    }

    public static <T> String liChain(Collection<T> list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean started = false;
        for (T element : list) {
            if (started) {
                sb.append(separator);
            } else {
                started = true;
            }
            sb.append(element);
        }
        return sb.toString();
    }

    public static <T> boolean liEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> T liFirst(Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return (list instanceof List) ? ((List<T>) list).get(0) : list.iterator().next();
        }
    }

    public static <T> boolean liNotEmpty(Collection<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T> Collection<T> liSafeIter(Collection<T> list) {
        return list != null ? list : Collections.emptyList();
    }

    public static <T> T nvl(T o1, T o2) {
        return o1 != null ? o1 : o2;
    }


    public static List<String> stringSplit(String string, String token) {
        List<String> list = new ArrayList<String>();
        if (string != null) {
            for (String substring : string.split(token, -1)) {
                String element = substring.trim();
                if (!element.equals("")) {
                    list.add(element);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <K> K to(Class<K> cl, Object o) {
        if (o == null || cl.isAssignableFrom(o.getClass())) return (K) o;
        if (equal(cl, String.class)) return (K) toString(o);
        if (equal(cl, Long.class)) return (K) toLong(o);
        if (equal(cl, Integer.class)) return (K) toInteger(o);
        if (equal(cl, BigDecimal.class)) return (K) toBigDecimal(o);
        if (equal(cl, Double.class)) return (K) toDouble(o);
        if (equal(cl, Boolean.class)) return (K) toBoolean(o);
        if (Enum.class.isAssignableFrom(cl)) return (K) toEnum((Class<? extends Enum>) cl, o);
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + cl);
    }

    public static <K extends Enum> K toEnum(Class<K> cl, Object o) {
        if (o == null || cl.equals(o.getClass())) return (K) o;
        return (K) Enum.valueOf(cl, o.toString());
    }

    public static BigDecimal toBigDecimal(Object o) {
        if (o == null || o instanceof BigDecimal) return (BigDecimal) o;
        if (o instanceof String) return new BigDecimal((String) o);
        if (o instanceof Long) return BigDecimal.valueOf((Long) o);
        if (o instanceof Integer) return BigDecimal.valueOf((Integer) o);
        if (o instanceof Double) return BigDecimal.valueOf((Double) o);
        if (o instanceof Boolean) return (((Boolean) o) ? BigDecimal.ONE : BigDecimal.ZERO);
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + BigDecimal.class);
    }

    public static Boolean toBoolean(Object o) {
        if (o == null || o instanceof Boolean) return (Boolean) o;
        if (o instanceof String) return "".equals(o) ? null : (Boolean.valueOf((String) o) || "1".equals(o));
        if (o instanceof Integer) return Boolean.valueOf(((Integer) o).compareTo(Integer.valueOf(1)) == 0);
        if (o instanceof Long) return Boolean.valueOf(((Long) o).compareTo(Long.valueOf(1)) == 0);
        if (o instanceof BigDecimal) return Boolean.valueOf(((BigDecimal) o).compareTo(BigDecimal.ONE) == 0);
        if (o instanceof Double) return Boolean.valueOf(((Double) o).compareTo(Double.valueOf(1)) == 0);
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + Boolean.class);
    }

    public static Double toDouble(Object o) {
        if (o == null || o instanceof Double) return (Double) o;
        if (o instanceof String) return Double.valueOf((String) o);
        if (o instanceof BigDecimal) return Double.valueOf(((BigDecimal) o).doubleValue());
        if (o instanceof Integer) return Double.valueOf(((Integer) o).doubleValue());
        if (o instanceof Long) return Double.valueOf(((Long) o).doubleValue());
        if (o instanceof Boolean) return (((Boolean) o) ? Double.valueOf(1) : Double.valueOf(0));
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + Double.class);
    }


    public static Integer toInteger(Object o) {
        if (o == null || o instanceof Integer) return (Integer) o;
        if (o instanceof String) return Integer.valueOf((String) o);
        if (o instanceof Long) return Integer.valueOf(((Long) o).intValue());
        if (o instanceof BigDecimal) return Integer.valueOf(((BigDecimal) o).intValue());
        if (o instanceof Double) return Integer.valueOf(((Double) o).intValue());
        if (o instanceof Boolean) return (((Boolean) o) ? Integer.valueOf(1) : Integer.valueOf(0));
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + Integer.class);
    }

    public static Long toLong(Object o) {
        if (o == null || o instanceof Long) return (Long) o;
        if (o instanceof String) return Long.valueOf((String) o);
        if (o instanceof Integer) return Long.valueOf((Integer) o);
        if (o instanceof BigDecimal) return Long.valueOf(((BigDecimal) o).longValue());
        if (o instanceof Double) return Long.valueOf(((Double) o).longValue());
        if (o instanceof Boolean) return (((Boolean) o) ? Long.valueOf(1) : Long.valueOf(0));
        throw new IllegalArgumentException("X.to:" + o.getClass() + " -> " + Long.class);
    }

    public static String toString(Object o) {
        if (o == null || o instanceof String) return (String) o;
        return o.toString();
    }

}
