package lithe.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Set implementation for really small sets and minimal memory footprint.
 * Values are kept in a Object[], and contains(...) scans the array sequentially, starting from the index of the last value found or added.
 * contains is O(n), but repeated contains of the same key costs 0.
 * Implementation is not thread safe.
 * Iterator doesn't check for map modification.
 *
 * @param <K> object type
 */
public final class FlashSet<K> implements Set<K>, Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Object[] keys = null;


    private int size = 0;


    private int lastidx = 0;


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean contains(Object key) {
        if (size > 0) {
            if (key == null) {
                for (int i = lastidx; i < size; i++)
                    if (keys[i] == null) {
                        lastidx = i;
                        return true;
                    }
                for (int i = 0; i < lastidx; i++)
                    if (keys[i] == null) {
                        lastidx = i;
                        return true;
                    }
            } else {
                for (int i = lastidx; i < size; i++)
                    if (key.equals(keys[i])) {
                        lastidx = i;
                        return true;
                    }
                for (int i = 0; i < lastidx; i++)
                    if (key.equals(keys[i])) {
                        lastidx = i;
                        return true;
                    }
            }
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    public K get() {
        return (K) keys[lastidx];
    }


    @SuppressWarnings("unchecked")
    public K set(K newVal) {
        Object oldKey = keys[lastidx];
        keys[lastidx] = newVal;
        return (K) oldKey;
    }


    @Override
    public boolean add(K e) {
        if (size == 0) {
            keys = new Object[]{e};
            size = 1;
            lastidx = 0;
            return true;
        } else {
            if (contains(e)) {
                return false;
            }
            if (size == keys.length) {
                int newLength = keys.length << 1;
                {
                    Object[] copy = new Object[newLength];
                    System.arraycopy(keys, 0, copy, 0, size);
                    keys = copy;
                }
            }
            keys[size] = e;
            lastidx = size;
            size++;
            return true;
        }
    }


    @Override
    public boolean remove(Object key) {
        if (contains(key)) {
            if (size == 1) {
                keys = null;
                size = 0;
            } else {
                int next = lastidx + 1;
                if (next < size) {
                    if (size > keys.length >> 2) {
                        int howManyAfter = size - next;
                        System.arraycopy(keys, next, keys, lastidx, howManyAfter);
                    } else {
                        int newlength = keys.length >> 1;
                        int howManyAfter = size - next;
                        {
                            Object[] newarray = new Object[newlength];
                            System.arraycopy(keys, 0, newarray, 0, lastidx);
                            System.arraycopy(keys, next, newarray, lastidx, howManyAfter);
                            keys = newarray;
                        }
                    }
                } else {
                    lastidx--;
                }
                size--;
                keys[size] = null;
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends K> c) {
        LinkedList<K> kk = null;
        for (K e : c) {
            if (!contains(e)) {
                if (kk == null) {
                    kk = new LinkedList<K>();
                }
                kk.add(e);
            }
        }
        if (kk != null) {
            int minNeed = size + kk.size();
            if (minNeed > keys.length) {
                int newLength = keys.length << 1;
                while (newLength < minNeed) {
                    newLength = newLength << 1;
                }
                {
                    Object[] copy = new Object[newLength];
                    System.arraycopy(keys, 0, copy, 0, size);
                    keys = copy;
                }
            }
            for (int i = size; i < minNeed; i++) {
                keys[i] = kk.poll();
            }
            lastidx = minNeed - 1;
            size = minNeed;
            return true;
        }
        return false;
    }


    @Override
    public void clear() {
        keys = null;
        size = 0;
        lastidx = 0;
    }


    public final class FlashIterator implements Iterator<K> {


        private int index;


        FlashIterator() {
            index = 0;
        }


        public final boolean hasNext() {
            return index < size;
        }


        public void remove() {
            if (size == 0 || index > size) throw new IllegalStateException();
            index--;
            FlashSet.this.remove(keys[index]);
        }


        @SuppressWarnings("unchecked")
        public K next() {
            if (index >= size) throw new NoSuchElementException();
            K e = (K) keys[index];
            index++;
            return e;
        }
    }


    public String toString() {
        Iterator<K> it = iterator();
        if (!it.hasNext()) return "[]";


        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            K e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }


    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (o == this) return true;


        if (!(o instanceof Set)) return false;
        Collection c = (Collection) o;
        if (c.size() != size()) return false;
        try {
            return containsAll(c);
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }


    public int hashCode() {
        int h = 0;
        Iterator<K> i = iterator();
        while (i.hasNext()) {
            K obj = i.next();
            if (obj != null) h += obj.hashCode();
        }
        return h;
    }


    @Override
    public Iterator<K> iterator() {
        return new FlashIterator();
    }


    @Override
    public Object[] toArray() {
        Object[] dest = new Object[size];


        if (size > 0) {
            System.arraycopy(keys, 0, dest, 0, size);
        }
        return dest;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        T[] dest = a.length >= size ? a :
                (T[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        if (size > 0) {
            System.arraycopy(keys, 0, dest, 0, size);
        }
        return dest;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


}
