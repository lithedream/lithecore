package lithe.core;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Map implementation for really small sets and minimal memory footprint.
 * Keys and values are kept in two Object[], and get(...) scans the array sequentially, starting from the index of the last key found or added.
 * get is O(n), but repeated get (or containsKey) of the same key costs 0.
 * Implementation is not thread safe.
 * Iterator doesn't check for map modification.
 *
 * @param <K> key type
 * @param <V> value type
 */
public final class FlashMap<K, V> implements Map<K, V>, Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Object[] keys = null;


    private Object[] values = null;


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
    public boolean containsKey(Object key) {
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


    @Override
    public boolean containsValue(Object value) {
        if (size > 0) {
            if (value == null) {
                for (int i = lastidx; i < size; i++)
                    if (values[i] == null) {
                        lastidx = i;
                        return true;
                    }
                for (int i = 0; i < lastidx; i++)
                    if (values[i] == null) {
                        lastidx = i;
                        return true;
                    }
            } else {
                for (int i = lastidx; i < size; i++)
                    if (value.equals(values[i])) {
                        lastidx = i;
                        return true;
                    }
                for (int i = 0; i < lastidx; i++)
                    if (value.equals(values[i])) {
                        lastidx = i;
                        return true;
                    }
            }
        }
        return false;
    }


    @Override
    public V get(Object key) {
        if (containsKey(key)) {
            return get();
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public V get() {
        return (V) values[lastidx];
    }


    @SuppressWarnings("unchecked")
    public V set(V newVal) {
        Object oldVal = values[lastidx];
        values[lastidx] = newVal;
        return (V) oldVal;
    }


    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        if (size == 0) {
            keys = new Object[]{key};
            values = new Object[]{value};
            size = 1;
            lastidx = 0;
            return null;
        } else {
            if (containsKey(key)) {
                return set(value);
            }
            if (size == keys.length) {
                int newLength = keys.length << 1;
                {
                    Object[] copy = new Object[newLength];
                    System.arraycopy(keys, 0, copy, 0, size);
                    keys = copy;
                }
                {
                    Object[] copy = new Object[newLength];
                    System.arraycopy(values, 0, copy, 0, size);
                    values = copy;
                }
            }
            keys[size] = key;
            values[size] = value;
            lastidx = size;
            size++;
            return null;
        }
    }


    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            V removed = get();
            if (size == 1) {
                keys = null;
                values = null;
                size = 0;
            } else {
                int next = lastidx + 1;
                if (next < size) {
                    if (size > keys.length >> 2) {
                        int howManyAfter = size - next;
                        System.arraycopy(keys, next, keys, lastidx, howManyAfter);
                        System.arraycopy(values, next, values, lastidx, howManyAfter);
                    } else {
                        int newlength = keys.length >> 1;
                        int howManyAfter = size - next;
                        {
                            Object[] newarray = new Object[newlength];
                            System.arraycopy(keys, 0, newarray, 0, lastidx);
                            System.arraycopy(keys, next, newarray, lastidx, howManyAfter);
                            keys = newarray;
                        }
                        {
                            Object[] newarray = new Object[newlength];
                            System.arraycopy(values, 0, newarray, 0, lastidx);
                            System.arraycopy(values, next, newarray, lastidx, howManyAfter);
                            values = newarray;
                        }
                    }
                } else {
                    lastidx--;
                }
                size--;
                keys[size] = null;
                values[size] = null;
            }
            return removed;
        }
        return null;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        LinkedList<K> kk = null;
        LinkedList<V> vv = null;
        for (Entry<? extends K, ? extends V> e : m.entrySet()) {
            if (containsKey(e.getKey())) {
                values[lastidx] = e.getValue();
            } else {
                if (kk == null) {
                    kk = new LinkedList<K>();
                    vv = new LinkedList<V>();
                }
                kk.add(e.getKey());
                vv.add(e.getValue());
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
                {
                    Object[] copy = new Object[newLength];
                    System.arraycopy(values, 0, copy, 0, size);
                    values = copy;
                }
            }
            for (int i = size; i < minNeed; i++) {
                keys[i] = kk.poll();
                values[i] = vv.poll();
            }
            lastidx = minNeed - 1;
            size = minNeed;
        }
    }


    @Override
    public void clear() {
        keys = null;
        values = null;
        size = 0;
        lastidx = 0;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keySet() {
        if (size == 0) {
            return Collections.emptySet();
        }
        return new LinkedHashSet<K>((Collection<? extends K>) Arrays.asList(keys));
    }


    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        if (size == 0) {
            return Collections.emptyList();
        }
        return (Collection<V>) Collections.unmodifiableList(Arrays.asList(values));
    }


    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return new FlashEntrySet();
    }


    private final class FlashEntrySet extends AbstractSet<Map.Entry<K, V>> {


        public Iterator<Map.Entry<K, V>> iterator() {
            return new FlashEntryIterator();
        }


        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) return false;
            Map.Entry<K, V> e = (Map.Entry<K, V>) o;
            if (FlashMap.this.containsKey(e.getKey())) {
                if (e.getValue() != null && e.getValue().equals(get()) || e.getValue() == null && get() == null) {
                    return true;
                }
            }
            return false;
        }


        @SuppressWarnings("unchecked")
        public boolean remove(Object o) {
            if (size == 0 || !(o instanceof Map.Entry)) return false;
            Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
            if (FlashMap.this.containsKey(entry.getKey()) && //
                    (entry.getValue() != null && entry.getValue().equals(get()) || //
                            entry.getValue() == null && get() == null)) {
                FlashMap.this.remove(entry.getKey());
                return true;
            }
            return false;
        }


        public int size() {
            return size;
        }


        public void clear() {
            FlashMap.this.clear();
        }
    }


    public final class FlashEntryIterator implements Iterator<Entry<K, V>> {


        private int index;


        FlashEntryIterator() {
            index = 0;
        }


        public final boolean hasNext() {
            return index < size;
        }


        public void remove() {
            if (size == 0 || index > size) throw new IllegalStateException();
            index--;
            FlashMap.this.remove(keys[index]);
        }


        public Map.Entry<K, V> next() {
            if (index >= size) throw new NoSuchElementException();
            Entry<K, V> e = new FlashEntry(index);
            index++;
            return e;
        }
    }


    private final class FlashEntry implements Entry<K, V> {


        private int index;


        FlashEntry(int index) {
            this.index = index;
        }


        @SuppressWarnings("unchecked")
        @Override
        public K getKey() {
            return (K) keys[index];
        }


        @SuppressWarnings("unchecked")
        @Override
        public V getValue() {
            return (V) values[index];
        }


        @SuppressWarnings("unchecked")
        @Override
        public V setValue(V value) {
            V object = (V) values[index];
            values[index] = value;
            return object;
        }


    }


    public String toString() {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (!i.hasNext()) return "{}";


        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (; ; ) {
            Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (!i.hasNext()) return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }


    public boolean equals(Object o) {
        if (o == this) return true;


        if (!(o instanceof Map)) return false;
        Map<K, V> m = (Map<K, V>) o;
        if (m.size() != size()) return false;


        try {
            Iterator<Entry<K, V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (!(m.get(key) == null && m.containsKey(key))) return false;
                } else {
                    if (!value.equals(m.get(key))) return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }


        return true;
    }


    public int hashCode() {
        int h = 0;
        Iterator<Entry<K, V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }


}
