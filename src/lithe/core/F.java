package lithe.core;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Class for that allows getter objects creation and use, to collect simple and complex properties into Lists and Maps
 *
 * <pre>
 *  {@code
 *  List<T> list = ... ;
 *  Get<T,PROPERTY2TYPE> get = F.$(list, F.y? null : _(list).getProperty1().getProperty2(),"getProperty1().getProperty2()");
 *  List<PROPERTY2TYPE> listValues = get.onList(list); //getProperty1().getProperty2() is called on every T in list
 *  }
 *  </pre>
 */
public class F {


    private static final Map<MethodKey, Reference<Method>> methodCache = Collections
            .synchronizedMap(new WeakHashMap<MethodKey, Reference<Method>>());


    public static final boolean y = true;


    public static <RET, T> Get<T, RET> $(Collection<T> col, RET ret, Object method) {
        return new Get<T, RET>((String) method);
    }


    public static <RET, T> Get<T, RET> $(T obj, RET ret, Object method) {
        return new Get<T, RET>((String) method);
    }


    public static <RET, T> Get<T, RET> $(Class<T> cl, RET ret, Object method) {
        return new Get<T, RET>((String) method);
    }


    public static <T> T _(Collection<T> obj) {
        return null;
    }


    public static <T> T _(Class<T> obj) {
        return null;
    }


    public static class Get<T, RET> implements Serializable{


        private String method;


        private transient volatile Reference<Method> wr = null;


        private Get<Object, RET> next = null;


        public Get(String method) {
            if (method.contains(".")) {
                String[] split = method.split("\\.");
                Get<Object, RET> a = null;
                for (int i = split.length; i-- > 1; ) {
                    a = new Get<Object, RET>(split[i], a);
                }
                this.next = a;
                method = split[0];
            }
            setMethod(method);
        }


        private Get(String method, Get<Object, RET> next) {
            setMethod(method);
            this.next = next;
        }


        private void setMethod(String method) {
            if (method.endsWith("()")) {
                this.method = method.substring(0, method.length() - 2);
            } else {
                this.method = method;
            }
        }


        public String getMethod() {
            return method;
        }


        @SuppressWarnings("unchecked")
        public RET on(T t) throws ThrownException {
            Method wrValue = wr != null ? wr.get() : null;
            if (wrValue == null || !wrValue.getDeclaringClass().isAssignableFrom(t.getClass())) {
                wrValue = gsetCache(t.getClass(), method);
                wr = new WeakReference<Method>(wrValue);
            }
            Object value;
            try {
                value = wrValue.invoke(t);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                if (cause instanceof Error) {
                    throw (Error) cause;
                }
                throw new ThrownException(cause);
            }
            if (next != null && value != null) {
                return next.on(value);
            }
            return (RET) value;
        }


        public <RET_LIST extends Collection<RET>> RET_LIST onList(Collection<T> c, RET_LIST k) throws ThrownException {
            for (T el : c) {
                k.add(on(el));
            }
            return k;
        }


        public <RET_MAP extends Map<T, RET>> RET_MAP onMap(Collection<T> c, RET_MAP k) throws ThrownException {
            for (T el : c) {
                k.put(el, on(el));
            }
            return k;
        }

        public <RET_MAP extends Map<RET, T>> RET_MAP onValueMap(Collection<T> c, RET_MAP k) throws ThrownException {
            for (T el : c) {
                k.put(on(el), el);
            }
            return k;
        }

        public <RET_MAP extends Map<RET, List<T>>> RET_MAP onValueMapList(Collection<T> c, RET_MAP k) throws ThrownException {
            for (T el : c) {
                RET val = on(el);
                List<T> list = k.get(val);
                if (list == null) {
                    list = new ArrayList<>();
                    k.put(val, list);
                }
                list.add(el);
            }
            return k;
        }


        public List<RET> onList(Collection<T> c) throws ThrownException {
            return onList(c, new ArrayList<RET>());
        }


        public Set<RET> onSet(Collection<T> c) throws ThrownException {
            return onList(c, new LinkedHashSet<RET>());
        }


        public Map<T, RET> onMap(Collection<T> c) throws ThrownException {
            return onMap(c, new LinkedHashMap<T, RET>());
        }

        public Map<RET, T> onValueMap(Collection<T> c) throws ThrownException {
            return onValueMap(c, new LinkedHashMap<RET, T>());
        }

        public Map<RET, List<T>> onValueMapList(Collection<T> c) throws ThrownException {
            return onValueMapList(c, new LinkedHashMap<RET, List<T>>());
        }


        @Override
        public String toString() {
            if (next == null) {
                return getClass().getSimpleName() + "[" + method + "]";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(getClass().getSimpleName()).append("[").append(method);
                Get<Object, RET> curs = next;
                while (curs != null) {
                    sb.append(".").append(curs.getMethod());
                    curs = curs.next;
                }
                sb.append("]");
                return sb.toString();
            }
        }


    }


    public static class ThrownException extends RuntimeException {


        /**
         *
         */
        private static final long serialVersionUID = 1L;


        private Throwable target;


        public ThrownException(Throwable t) {
            super((Throwable) null);
            this.target = t;
        }


        public Throwable getTarget() {
            return target;
        }


        @Override
        public synchronized Throwable getCause() {
            return target;
        }


    }


    private static class MethodKey {


        private Class<?> cls;


        private String methodName;


        private int hashCode;


        public MethodKey(Class<?> cls, String methodName) {
            this.cls = cls;
            this.methodName = methodName;
            this.hashCode = methodName.length();
        }


        /**
         * Checks for equality.
         *
         * @param obj object to be tested for equality
         * @return true, if the object describes the same Method.
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MethodKey)) {
                return false;
            }
            MethodKey md = (MethodKey) obj;
            return methodName.equals(md.methodName) && cls.equals(md.cls);
        }


        public int hashCode() {
            return hashCode;
        }


        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + cls.getName() + "." + methodName + "]";
        }
    }


    private static Method gsetCache(Class<?> cl, String method) {
        MethodKey k = new MethodKey(cl, method);
        Reference<Method> methodRef = methodCache.get(k);
        Method m = methodRef != null ? methodRef.get() : null;
        if (m == null) {
            try {
                m = cl.getMethod(method);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(e);
            }
            methodCache.put(k, new WeakReference<Method>(m));
        }
        return m;
    }


}