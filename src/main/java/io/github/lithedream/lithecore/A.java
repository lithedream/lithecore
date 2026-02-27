package io.github.lithedream.lithecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.lithedream.lithecore.a.A2;
import io.github.lithedream.lithecore.a.A3;
import io.github.lithedream.lithecore.a.A4;
import io.github.lithedream.lithecore.a.A5;
import io.github.lithedream.lithecore.a.A6;
import io.github.lithedream.lithecore.a.A7;
import io.github.lithedream.lithecore.a.A8;
import io.github.lithedream.lithecore.a.A9;

public abstract class A {

	public abstract int size();

	public static <K0, K1> A2<K0, K1> $(K0 v0, K1 v1) {
		return new A2<K0, K1>(v0, v1);
	}

	public static <K0, K1, K2> A3<K0, K1, K2> $(K0 v0, K1 v1, K2 v2) {
		return new A3<K0, K1, K2>(v0, v1, v2);
	}
	
	public static <K0, K1, K2, K3> A4<K0, K1, K2, K3> $(K0 v0, K1 v1, K2 v2, K3 v3) {
		return new A4<K0, K1, K2, K3>(v0, v1, v2, v3);
	}
	
	public static <K0, K1, K2, K3, K4> A5<K0, K1, K2, K3, K4> $(K0 v0, K1 v1, K2 v2, K3 v3, K4 v4) {
		return new A5<K0, K1, K2, K3, K4>(v0, v1, v2, v3, v4);
	}
	
	public static <K0, K1, K2, K3, K4, K5> A6<K0, K1, K2, K3, K4, K5> $(K0 v0, K1 v1, K2 v2, K3 v3, K4 v4, K5 v5) {
		return new A6<K0, K1, K2, K3, K4, K5>(v0, v1, v2, v3, v4, v5);
	}
	
	public static <K0, K1, K2, K3, K4, K5, K6> A7<K0, K1, K2, K3, K4, K5, K6> $(K0 v0, K1 v1, K2 v2, K3 v3, K4 v4, K5 v5, K6 v6) {
		return new A7<K0, K1, K2, K3, K4, K5, K6>(v0, v1, v2, v3, v4, v5, v6);
	}
	
	public static <K0, K1, K2, K3, K4, K5, K6, K7> A8<K0, K1, K2, K3, K4, K5, K6, K7> $(K0 v0, K1 v1, K2 v2, K3 v3, K4 v4, K5 v5, K6 v6, K7 v7) {
		return new A8<K0, K1, K2, K3, K4, K5, K6, K7>(v0, v1, v2, v3, v4, v5, v6, v7);
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> $(K0 v0, K1 v1, K2 v2, K3 v3, K4 v4, K5 v5, K6 v6, K7 v7, K8 v8) {
		return new A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>(v0, v1, v2, v3, v4, v5, v6, v7, v8);
	}

	public abstract Object g(int index);

	public abstract void s(int index, Object o);

	public Object[] toArray() {
		Object[] ret = new Object[size()];
		for (int i = 0; i < size(); i++) {
			ret[i] = g(i);
		}
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('A');
		sb.append(size());
		sb.append('[');
		for (int i = 0; i < size(); i++) {
			if (i > 0) {
				sb.append(',').append(' ');
			}
			Object g = g(i);
			sb.append(g == this ? "(this A)" : g);
		}
		sb.append(']');
		return sb.toString();
	}

	public static <K0, K1> Map<K0, K1> toMap2(Collection<A2<K0, K1>> li) {
		Map<K0, K1> map = new LinkedHashMap<K0, K1>();
		for (A2<K0, K1> a2 : li) {
			map.put(a2.g0(), a2.g1());
		}
		return map;
	}

	public static <K0, K1> Map<K0, List<K1>> toMapList2(Collection<A2<K0, K1>> li) {
		Map<K0, List<K1>> map = new LinkedHashMap<K0, List<K1>>();
		for (A2<K0, K1> a2 : li) {
			List<K1> v = map.get(a2.g0());
			if (v == null) {
				v = new ArrayList<K1>();
				map.put(a2.g0(), v);
			}
			v.add(a2.g1());
		}
		return map;
	}
	
	public static <K0, K1> Map<K0, Set<K1>> toMapSet2(Collection<A2<K0, K1>> li) {
		Map<K0, Set<K1>> map = new LinkedHashMap<K0, Set<K1>>();
		for (A2<K0, K1> a2 : li) {
			Set<K1> v = map.get(a2.g0());
			if (v == null) {
				v = new LinkedHashSet<K1>();
				map.put(a2.g0(), v);
			}
			v.add(a2.g1());
		}
		return map;
	}


	public static <K0, K1, K2> Map<K0, A2<K1, K2>> toMap3(Collection<A3<K0, K1, K2>> li) {
		Map<K0, A2<K1, K2>> map = new LinkedHashMap<K0, A2<K1, K2>>();
		for (A3<K0, K1, K2> a3 : li) {
			map.put(a3.g0(), A.$(a3.g1(), a3.g2()));
		}
		return map;
	}

	public static <K0, K1, K2, K3> Map<K0, A3<K1, K2, K3>> toMap4(Collection<A4<K0, K1, K2, K3>> li) {
		Map<K0, A3<K1, K2, K3>> map = new LinkedHashMap<K0, A3<K1, K2, K3>>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			map.put(a4.g0(), A.$(a4.g1(), a4.g2(), a4.g3()));
		}
		return map;
	}

	public static <K0, K1, K2, K3> Map<A3<K0, K1, K2>, K3> toMap4byA3(Collection<A4<K0, K1, K2, K3>> li) {
		Map<A3<K0, K1, K2>, K3> map = new LinkedHashMap<A3<K0, K1, K2>, K3>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			map.put(A.$(a4.g0(), a4.g1(), a4.g2()), a4.g3());
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4> Map<A3<K0, K1, K2>, A2<K3, K4>> toMap5byA3(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<A3<K0, K1, K2>, A2<K3, K4>> map = new LinkedHashMap<A3<K0, K1, K2>, A2<K3, K4>>();
		for (A5<K0, K1, K2, K3, K4> a5 : li) {
			map.put(A.$(a5.g0(), a5.g1(), a5.g2()), A.$(a5.g3(), a5.g4()));
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4> Map<A4<K0, K1, K2, K3>, K4> toMap5byA4(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<A4<K0, K1, K2, K3>, K4> map = new LinkedHashMap<A4<K0, K1, K2, K3>, K4>();
		for (A5<K0, K1, K2, K3, K4> a5 : li) {
			map.put(A.$(a5.g0(), a5.g1(), a5.g2(), a5.g3()), a5.g4());
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4> Map<K0, A4<K1, K2, K3, K4>> toMap5(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<K0, A4<K1, K2, K3, K4>> map = new LinkedHashMap<K0, A4<K1, K2, K3, K4>>();
		for (A5<K0, K1, K2, K3, K4> a5 : li) {
			map.put(a5.g0(), A.$(a5.g1(), a5.g2(), a5.g3(), a5.g4()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4, K5> Map<K0, A5<K1, K2, K3, K4, K5>> toMap6(Collection<A6<K0, K1, K2, K3, K4, K5>> li) {
		Map<K0, A5<K1, K2, K3, K4, K5>> map = new LinkedHashMap<K0, A5<K1, K2, K3, K4, K5>>();
		for (A6<K0, K1, K2, K3, K4, K5> a6 : li) {
			map.put(a6.g0(), A.$(a6.g1(), a6.g2(), a6.g3(), a6.g4(), a6.g5()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4, K5, K6> Map<K0, A6<K1, K2, K3, K4, K5, K6>> toMap7(Collection<A7<K0, K1, K2, K3, K4, K5, K6>> li) {
		Map<K0, A6<K1, K2, K3, K4, K5, K6>> map = new LinkedHashMap<K0, A6<K1, K2, K3, K4, K5, K6>>();
		for (A7<K0, K1, K2, K3, K4, K5, K6> a7 : li) {
			map.put(a7.g0(), A.$(a7.g1(), a7.g2(), a7.g3(), a7.g4(), a7.g5(), a7.g6()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4, K5, K6, K7> Map<K0, A7<K1, K2, K3, K4, K5, K6, K7>> toMap8(Collection<A8<K0, K1, K2, K3, K4, K5, K6, K7>> li) {
		Map<K0, A7<K1, K2, K3, K4, K5, K6, K7>> map = new LinkedHashMap<K0, A7<K1, K2, K3, K4, K5, K6, K7>>();
		for (A8<K0, K1, K2, K3, K4, K5, K6, K7> a7 : li) {
			map.put(a7.g0(), A.$(a7.g1(), a7.g2(), a7.g3(), a7.g4(), a7.g5(), a7.g6(), a7.g7()));
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Map<K0, A8<K1, K2, K3, K4, K5, K6, K7, K8>> toMap9(Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Map<K0, A8<K1, K2, K3, K4, K5, K6, K7, K8>> map = new LinkedHashMap<K0, A8<K1, K2, K3, K4, K5, K6, K7, K8>>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a7 : li) {
			map.put(a7.g0(), A.$(a7.g1(), a7.g2(), a7.g3(), a7.g4(), a7.g5(), a7.g6(), a7.g7(), a7.g8()));
		}
		return map;
	}

	
	public static <K0, K1, K2> Map<A2<K0, K1>, K2> toMap3ByA2(Collection<A3<K0, K1, K2>> li) {
		Map<A2<K0, K1>, K2> map = new LinkedHashMap<A2<K0, K1>, K2>();
		for (A3<K0, K1, K2> a3 : li) {
			map.put(A.$(a3.g0(), a3.g1()), a3.g2());
		}
		return map;
	}
	
	public static <K0, K1, K2> Map<K0, List<A2<K1,K2>>> toMapList3(Collection<A3<K0, K1, K2>> li) {
		Map<K0, List<A2<K1,K2>>> map = new LinkedHashMap<K0, List<A2<K1,K2>>>();
		for (A3<K0, K1, K2> a2 : li) {
			List<A2<K1,K2>> v = map.get(a2.g0());
			if (v == null) {
				v = new ArrayList<A2<K1,K2>>();
				map.put(a2.g0(), v);
			}
			v.add(A.$(a2.g1(),a2.g2()));
		}
		return map;
	}
	
	public static <K0, K1, K2> Map<A2<K0,K1>, List<K2>> toMapList3byA2(Collection<A3<K0, K1, K2>> li) {
		Map<A2<K0,K1>, List<K2>> map = new LinkedHashMap<A2<K0,K1>, List<K2>>();
		for (A3<K0, K1, K2> a2 : li) {
			A2<K0, K1> key = A.$(a2.g0(), a2.g1());
			List<K2> v = map.get(key);
			if (v == null) {
				v = new ArrayList<K2>();
				map.put(key, v);
			}
			v.add(a2.g2());
		}
		return map;
	}

	public static <K0, K1, K2, K3> Map<A2<K0, K1>, A2<K2, K3>> toMap4ByA2(Collection<A4<K0, K1, K2, K3>> li) {
		Map<A2<K0, K1>, A2<K2, K3>> map = new LinkedHashMap<A2<K0, K1>, A2<K2, K3>>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			map.put(A.$(a4.g0(), a4.g1()), A.$(a4.g2(), a4.g3()));
		}
		return map;
	}

	public static <K0, K1, K2, K3> Map<K0, List<A3<K1,K2,K3>>> toMapList4(Collection<A4<K0, K1, K2, K3>> li) {
		Map<K0, List<A3<K1,K2,K3>>> map = new LinkedHashMap<K0, List<A3<K1,K2,K3>>>();
		for (A4<K0, K1, K2, K3> a2 : li) {
			List<A3<K1,K2,K3>> v = map.get(a2.g0());
			if (v == null) {
				v = new ArrayList<A3<K1,K2,K3>>();
				map.put(a2.g0(), v);
			}
			v.add(A.$(a2.g1(),a2.g2(),a2.g3()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4> Map<K0, List<A4<K1, K2, K3, K4>>> toMapList5(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<K0, List<A4<K1, K2, K3, K4>>> map = new LinkedHashMap<K0, List<A4<K1, K2, K3, K4>>>();
		for (A5<K0, K1, K2, K3, K4> a2 : li) {
			List<A4<K1, K2, K3, K4>> v = map.get(a2.g0());
			if (v == null) {
				v = new ArrayList<A4<K1, K2, K3, K4>>();
				map.put(a2.g0(), v);
			}
			v.add(A.$(a2.g1(), a2.g2(), a2.g3(), a2.g4()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3> Map<A3<K0, K1, K2>, List<K3>> toMapList4byA3(Collection<A4<K0, K1, K2, K3>> li) {
		Map<A3<K0, K1, K2>, List<K3>> map = new LinkedHashMap<A3<K0, K1, K2>, List<K3>>();
		for (A4<K0, K1, K2, K3> a2 : li) {
			A3<K0, K1, K2> key = A.$(a2.g0(), a2.g1(), a2.g2());
			List<K3> v = map.get(key);
			if (v == null) {
				v = new ArrayList<K3>();
				map.put(key, v);
			}
			v.add(a2.g3());
		}
		return map;
	}

	public static <K0, K1, K2, K3> Map<A2<K0, K1>, List<A2<K2, K3>>> toMapList4byA2(Collection<A4<K0, K1, K2, K3>> li) {
		Map<A2<K0, K1>, List<A2<K2, K3>>> map = new LinkedHashMap<A2<K0, K1>, List<A2<K2, K3>>>();
		for (A4<K0, K1, K2, K3> a2 : li) {
			A2<K0, K1> key = A.$(a2.g0(), a2.g1());
			List<A2<K2, K3>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A2<K2, K3>>();
				map.put(key, v);
			}
			v.add(A.$(a2.g2(), a2.g3()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4> Map<A2<K0, K1>, List<A3<K2, K3, K4>>> toMapList5byA2(
			Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<A2<K0, K1>, List<A3<K2, K3, K4>>> map = new LinkedHashMap<A2<K0, K1>, List<A3<K2, K3, K4>>>();
		for (A5<K0, K1, K2, K3, K4> a2 : li) {
			A2<K0, K1> key = A.$(a2.g0(), a2.g1());
			List<A3<K2, K3, K4>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A3<K2, K3, K4>>();
				map.put(key, v);
			}
			v.add(A.$(a2.g2(), a2.g3(), a2.g4()));
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4> Map<A3<K0, K1, K2>, List<A2<K3, K4>>> toMapList5byA3(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<A3<K0, K1, K2>, List<A2<K3, K4>>> map = new LinkedHashMap<A3<K0, K1, K2>, List<A2<K3, K4>>>();
		for (A5<K0, K1, K2, K3, K4> a5 : li) {
			A3<K0, K1, K2> key = A.$(a5.g0(), a5.g1(), a5.g2());
			List<A2<K3, K4>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A2<K3, K4>>();
				map.put(key, v);
			}
			v.add(A.$(a5.g3(), a5.g4()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4> Map<A4<K0, K1, K2, K3>, List<K4>> toMapList5byA4(Collection<A5<K0, K1, K2, K3, K4>> li) {
		Map<A4<K0, K1, K2, K3>, List<K4>> map = new LinkedHashMap<A4<K0, K1, K2, K3>, List<K4>>();
		for (A5<K0, K1, K2, K3, K4> a5 : li) {
			A4<K0, K1, K2, K3> key = A.$(a5.g0(), a5.g1(), a5.g2(), a5.g3());
			List<K4> v = map.get(key);
			if (v == null) {
				v = new ArrayList<K4>();
				map.put(key, v);
			}
			v.add(a5.g4());
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4, K5> Map<K0, List<A5<K1, K2, K3, K4, K5>>> toMapList6(Collection<A6<K0, K1, K2, K3, K4, K5>> li) {
		Map<K0, List<A5<K1, K2, K3, K4, K5>>> map = new LinkedHashMap<K0, List<A5<K1, K2, K3, K4, K5>>>();
		for (A6<K0, K1, K2, K3, K4, K5> a2 : li) {
			List<A5<K1, K2, K3, K4, K5>> v = map.get(a2.g0());
			if (v == null) {
				v = new ArrayList<A5<K1, K2, K3, K4, K5>>();
				map.put(a2.g0(), v);
			}
			v.add(A.$(a2.g1(), a2.g2(), a2.g3(), a2.g4(), a2.g5()));
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4, K5> Map<A2<K0, K1>, List<A4<K2, K3, K4, K5>>> toMapList6byA2(
			Collection<A6<K0, K1, K2, K3, K4, K5>> li) {
		Map<A2<K0, K1>, List<A4<K2, K3, K4, K5>>> map = new LinkedHashMap<A2<K0, K1>, List<A4<K2, K3, K4, K5>>>();
		for (A6<K0, K1, K2, K3, K4, K5> a2 : li) {
			A2<K0, K1> key = A.$(a2.g0(), a2.g1());
			List<A4<K2, K3, K4, K5>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A4<K2, K3, K4, K5>>();
				map.put(key, v);
			}
			v.add(A.$(a2.g2(), a2.g3(), a2.g4(), a2.g5()));
		}
		return map;
	}

	public static <K0, K1, K2, K3, K4, K5, K6> Map<A2<K0, K1>, List<A5<K2, K3, K4, K5, K6>>> toMapList7byA2(
			Collection<A7<K0, K1, K2, K3, K4, K5, K6>> li) {
		Map<A2<K0, K1>, List<A5<K2, K3, K4, K5, K6>>> map = new LinkedHashMap<A2<K0, K1>, List<A5<K2, K3, K4, K5, K6>>>();
		for (A7<K0, K1, K2, K3, K4, K5, K6> a2 : li) {
			A2<K0, K1> key = A.$(a2.g0(), a2.g1());
			List<A5<K2, K3, K4, K5, K6>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A5<K2, K3, K4, K5, K6>>();
				map.put(key, v);
			}
			v.add(A.$(a2.g2(), a2.g3(), a2.g4(), a2.g5(), a2.g6()));
		}
		return map;
	}
	
	public static <K0, K1, K2, K3, K4, K5, K6> Map<A3<K0, K1, K2>, List<A4<K3, K4, K5, K6>>> toMapList7byA3(
			Collection<A7<K0, K1, K2, K3, K4, K5, K6>> li) {
		Map<A3<K0, K1, K2>, List<A4<K3, K4, K5, K6>>> map = new LinkedHashMap<A3<K0, K1, K2>, List<A4<K3, K4, K5, K6>>>();
		for (A7<K0, K1, K2, K3, K4, K5, K6> a2 : li) {
			A3<K0, K1, K2> key = A.$(a2.g0(), a2.g1(), a2.g2());
			List<A4<K3, K4, K5, K6>> v = map.get(key);
			if (v == null) {
				v = new ArrayList<A4<K3, K4, K5, K6>>();
				map.put(key, v);
			}
			v.add(A.$(a2.g3(), a2.g4(), a2.g5(), a2.g6()));
		}
		return map;
	}

	public static <T extends A> void sortBy(List<T> li, final int pos) {
		Collections.sort(li, new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {

				return AnyCompare.compare(o1.g(pos), o2.g(pos));
			}
		});

	}

	public static <T extends A> T min(List<T> li, final int pos) {
		T min = null;
		Object val = null;
		for (T t : li) {
			if (min == null || AnyCompare.compare(t.g(pos), val) < 0) {
				min = t;
				val = t.g(pos);
			}
		}
		return min;
	}
	
	
	public static <T extends A> T max(List<T> li, final int pos) {
		T max = null;
		Object val = null;
		for (T t : li) {
			if (max == null || AnyCompare.compare(t.g(pos), val) > 0) {
				max = t;
				val = t.g(pos);
			}
		}
		return max;
	}

}
