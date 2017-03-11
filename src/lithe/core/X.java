package lithe.core;


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
}
