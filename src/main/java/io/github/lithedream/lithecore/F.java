package io.github.lithedream.lithecore;

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

import io.github.lithedream.lithecore.f.FUtils;

/**
 * Class for that allows getter objects creation and use, to collect simple and complex properties into Lists and Maps. It is also possible
 * to represent method invocations for later use
 * <p>
 *
 * <pre>
 *  {@code
 *
 *  List<T> list = ... ;
 *  Gate<T,PROPERTY2TYPE> gate = F.$(list).gate(F.y? null : F.__(list).getProperty1().getProperty2(),"getProperty1().getProperty2()");
 *
 *  List<PROPERTY2TYPE> listValues = gate.onList(list); //getProperty1().getProperty2() is called on every T in list
 *
 *  ...
 *  SOMEOBJECT obj = ...;
 *  Erg<SOMEOBJECT> erg = F.$(obj);
 *  Call<SOMEOBJECT> call = erg.call("someMethod");
 *  if (F.never) {
 *      call.__.someMethod();
 *  }
 *
 *  call.on(obj); // calls obj.someMethod();
 *
 *
 *  ...
 *  SOMEOBJECT obj = ...;
 *  Demon<T,RETURNTYPE> demon = F.$(obj).demon(F.y? null : obj.methodWithParams(F.__(Parameter1.class),F.__(Parameter2.class)),"methodWithParams()");
 *
 *  Parameter1 parameter1=...;
 *  Parameter2 parameter2=...;
 *  RETURNTYPE ret=demon.on(obj,parameter1,parameter2); // calls obj.methodWithParams(parameter1,parameter2)
 *
 *  }
 * </pre>
 */
public class F {

    public static final boolean y = true;

    public static final boolean never = false;

    @SuppressWarnings("unchecked")
    public static <T> Erg<T> $(T obj) {
        return (Erg<T>) Erg.getInstance();
    }

    @SuppressWarnings("unchecked")
    public static <T> Erg<T> $(Class<T> obj) {
        return (Erg<T>) Erg.getInstance();
    }

	@SuppressWarnings("unchecked")
	public static <T> Erg<T> $(Gate<T,?> obj) {
		return (Erg<T>) Erg.getInstance();
	}

    @SuppressWarnings("unchecked")
    public static <T> Erg<T> $(Collection<T> obj) {
        return (Erg<T>) Erg.getInstance();
    }

	public static <T> T __(Collection<T> obj) {
        return null;
    }

	public static <T> T __(Class<T> obj) {
        return null;
    }

    public static FUtils utils(){
        return FUtils.getInstance();
    }

	public static final class Gate<T, RET> implements Serializable, LeftGate<T>, RightGate<RET> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private Demon<T, RET> get = null;

        private Demon<T, RET> set = null;

        private Gate(String method) {
            get = new Demon<T, RET>(method);
        }

        public RET on(T t) throws ThrownException {
			return get.on0(t);
        }

        public void put(T t, RET value) throws ThrownException {
            if (set == null) {
                String toRename = get.getLastMethod();
                if (toRename.startsWith("get")) {
                    toRename = "set" + toRename.substring(3, toRename.length());
				} else if (toRename.startsWith("is")) {
                    toRename = "set" + toRename.substring(2, toRename.length());
				} else
                    throw new IllegalArgumentException("Method " + toRename + " doesn't start with get or is");
                set = get.copy().renameLastMethod(toRename);
            }
			set.on1(t, value);
		}

		public void putLenient(T t, RET value) throws ThrownException {
			if (set == null) {
				String toRename = get.getLastMethod();
				if (toRename.startsWith("get")) {
					toRename = "set" + toRename.substring(3, toRename.length());
				} else if (toRename.startsWith("is")) {
					toRename = "set" + toRename.substring(2, toRename.length());
				} else
					throw new IllegalArgumentException("Method " + toRename + " doesn't start with get or is");
				set = get.copy().renameLastMethod(toRename);
			}
			set.on1Lenient(t, value);
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
                    list = new ArrayList<T>();
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

		public RET onMax(Collection<T> c) throws ThrownException {
			RET maxValue = null;
			if (c != null) {
				for (T el : c) {
					RET on = on(el);
					if (maxValue == null || (on != null && ((Comparable) on).compareTo(maxValue) >= 0)) {
						maxValue = on;
					}
				}
			}
			return maxValue;
		}

		public RET onMin(Collection<T> c) throws ThrownException {
			RET minValue = null;
			if (c != null) {
				for (T el : c) {
					RET on = on(el);
					if (minValue == null || (on != null && ((Comparable) on).compareTo(minValue) < 0)) {
						minValue = on;
					}
				}
			}
			return minValue;
		}

		public List<T> byValue(Collection<T> c, RET value) throws ThrownException {
			List<T> k = new ArrayList<T>();
			for (T el : c) {
				RET on = on(el);
				if (X.equal(on, value)) {
					k.add(el);
				}
			}
			return k;
		}

		public List<T> byValues(Collection<T> c, RET... value) throws ThrownException {
			List<T> k = new ArrayList<T>();
			for (T el : c) {
				RET on = on(el);
				for (RET v : value) {
					if (X.equal(on, v)) {
						k.add(el);
						break;
					}
				}
			}
			return k;
		}

		public List<T> byNotNull(Collection<T> c) throws ThrownException {
			List<T> k = new ArrayList<T>();
			for (T el : c) {
				RET on = on(el);
				if (on != null) {
					k.add(el);
				}
			}
			return k;
		}

		public List<T> byNull(Collection<T> c) throws ThrownException {
			List<T> k = new ArrayList<T>();
			for (T el : c) {
				RET on = on(el);
				if (on == null) {
					k.add(el);
				}
			}
			return k;
		}

        public Map<RET, List<T>> onValueMapList(Collection<T> c) throws ThrownException {
            return onValueMapList(c, new LinkedHashMap<RET, List<T>>());
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ":" + get.toString();
        }

		@SuppressWarnings("unchecked")
		public Erg<T> $() {
			return (Erg<T>) Erg.getInstance();
		}

		public T __() {
			return null;
		}

    }

    public static class ThrownException extends RuntimeException {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private Throwable target;

        private ThrownException(Throwable t) {
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

    public static final class Erg<T> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private static final Erg<Object> instance = new Erg<Object>();

        private Erg() {

        }

        public final T __ = null;

        public <RET> Gate<T, RET> gate(RET ret, String method) {
            return new Gate<T, RET>(method);
        }

        // public Call<T> call() {
        // return new Call<T>();
        // }

        public Call<T> call(String method) {
            return new Call<T>(method);
        }

        public <RET> Demon<T, RET> demon(RET ret, String method) {
            return new Demon<T, RET>(method);
        }

        public Demon<T, Void> demon(String method) {
            return new Demon<T, Void>(method);
        }

		public static final Erg<Object> getInstance() {
            return instance;
        }

    }

    public static final class Call<T> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public final T __ = null;

        private Demon<T, Void> demon = null;

        // private Call() {
        //
        // }

        private Call(String method) {
            demon = new Demon<T, Void>(method);
        }

        // public boolean __(String method) {
        // demon = new Demon<T, Void>(method);
        // return false;
        // }

        public void on(T t) throws ThrownException {
			demon.on0(t);
        }

        public void onExtra(T t, Object... params) throws ThrownException {
            demon.on(t, params);
        }

        public void onList(Collection<T> c) throws ThrownException {
            for (T el : c) {
				demon.on0(el);
            }
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ":" + demon;
        }

    }

    public static final class Demon<T, RET> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private static final Map<MethodKey, Reference<Method>> methodCache = Collections
                .synchronizedMap(new WeakHashMap<MethodKey, Reference<Method>>());

        private String method;

        private transient Reference<Method> wr = null;

        private Demon<Object, RET> next = null;

        private Demon(String method) {
            if (method.contains(".")) {
                String[] split = method.split("\\.");
                Demon<Object, RET> a = null;
                for (int i = split.length; i-- > 1; ) {
                    a = new Demon<Object, RET>(split[i], a);
                }
                this.next = a;
                method = split[0];
            }
            setMethod(method);
        }

        private Demon(String method, Demon<Object, RET> next) {
            setMethod(method);
            this.next = next;
        }

        private void setMethod(String method) {
            this.method = method.endsWith("()") ? method.substring(0, method.length() - 2) : method;
        }

        @SuppressWarnings("unchecked")
        public RET on(T t, Object... params) throws ThrownException {
            Object val;
            try {
                Method wrValue = getMethodInstance(t, (next != null || params == null) ? 0 : params.length);
				val = next == null ? wrValue.invoke(t, params) : wrValue.invoke(t);
			}
			catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
			catch (InvocationTargetException e) {
				Throwable cause = e.getCause();
				if (cause instanceof RuntimeException) {
					throw (RuntimeException) cause;
				}
				if (cause instanceof Error) {
					throw (Error) cause;
				}
				throw new ThrownException(cause);
			}

			if (next == null || val == null) {
				return (RET) val;
			}
			return next.on(val, params);
		}


		@SuppressWarnings("unchecked")
		private RET on0(T t) throws ThrownException {
			Object val;
			try {
				Method wrValue = getMethodInstance(t, 0);
				val = wrValue.invoke(t);
			}
			catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
			catch (InvocationTargetException e) {
				Throwable cause = e.getCause();
				if (cause instanceof RuntimeException) {
					throw (RuntimeException) cause;
				}
				if (cause instanceof Error) {
					throw (Error) cause;
				}
				throw new ThrownException(cause);
			}

			if (next == null || val == null) {
				return (RET) val;
			}
			return next.on0(val);
		}

        private RET on1(T t, Object param) throws ThrownException {
            Object val;
            try {
                if (next != null) {
                    Method wrValue = getMethodInstance(t, 0);
                    val = wrValue.invoke(t);
                } else {
                    Method wrValue = getMethodInstance(t, 1);
                    val = wrValue.invoke(t, param);
                }
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

            if (next == null || val == null) {
                return (RET) val;
            }
            return next.on1(val, param);
        }

		private RET on1Lenient(T t, Object param) throws ThrownException {
			Object val;
			try {
				if (next != null) {
					Method wrValue = getMethodInstance(t, 0);
					val = wrValue.invoke(t);
				} else {
					Method wrValue = getMethodInstance(t, 1);
					Class classPar;
					Object converted = param == null || (classPar = wrValue.getParameterTypes()[0]).isAssignableFrom(param.getClass()) ? param
							: X.to(classPar, param);
					val = wrValue.invoke(t, converted);
				}
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

			if (next == null || val == null) {
				return (RET) val;
			}
			return next.on1Lenient(val, param);
        }

        private Method getMethodInstance(T t, int numParams) {
            Method wrValue = wr != null ? wr.get() : null;
			if (wrValue == null || wrValue.getParameterCount() != numParams
                    || !wrValue.getDeclaringClass().isAssignableFrom(t.getClass())) {
                wrValue = gsetCache(t.getClass(), method, numParams);
                wr = new WeakReference<Method>(wrValue);
            }
            return wrValue;
        }

        @Override
        public String toString() {
            if (next == null) {
                return getClass().getSimpleName() + "[" + method + "]";
			} else {
                StringBuilder sb = new StringBuilder();
                sb.append(getClass().getSimpleName()).append("[").append(method);
                Demon<Object, RET> curs = next;
                while (curs != null) {
                    sb.append(".").append(curs.method);
                    curs = curs.next;
                }
                sb.append("]");
                return sb.toString();
            }
        }

        private static Method gsetCache(Class<?> cl, String method, int numParams) {
            MethodKey k = new MethodKey(cl, method, numParams);
            Reference<Method> methodRef = methodCache.get(k);
            Method m = methodRef != null ? methodRef.get() : null;
            if (m == null) {
                if (numParams == 0) {
                    try {
                        m = cl.getMethod(method);
					} catch (NoSuchMethodException e) {
                        throw new IllegalArgumentException(e);
                    }
				} else {
                    for (Method me : cl.getMethods()) {
						if (me.getName().equals(method) && me.getParameterCount() == numParams) {
                            m = me;
                            break;
                        }
                    }
                    if (m == null) {
                        throw new IllegalArgumentException("Method " + method + " not found in class " + cl.getName());
                    }
                }
                methodCache.put(k, new WeakReference<Method>(m));
            }
            return m;
        }

        protected Demon<T, RET> copy() {
            return new Demon<T, RET>(method, next == null ? null : next.copy());
        }

        protected String getLastMethod() {
            return next == null ? method : next.getLastMethod();
        }

        protected Demon<T, RET> renameLastMethod(String toRename) {
            if (next == null) {
                method = toRename;
                wr = null;
			} else {
                next.renameLastMethod(toRename);
            }
            return this;
        }

        private static class MethodKey {

            private Class<?> cls;

            private String methodName;

            private int numParams;

            private int hashCode;

            public MethodKey(Class<?> cls, String methodName, int numParams) {
                this.cls = cls;
                this.methodName = methodName;
                this.numParams = numParams;
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
                return methodName.equals(md.methodName) && numParams == md.numParams && cls.equals(md.cls);
            }

            public int hashCode() {
                return hashCode;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(getClass().getSimpleName()).append("[").append(cls.getName()).append(".").append(methodName);
                if (numParams > 0) {
                    sb.append("(").append(numParams).append(")");
                }
                sb.append("]");
                return sb.toString();
            }
        }

    }

	public static interface LeftGate<T> {

	}

	public static interface RightGate<RET> {

	}

}