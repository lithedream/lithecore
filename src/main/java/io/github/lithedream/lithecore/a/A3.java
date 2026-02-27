package io.github.lithedream.lithecore.a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import io.github.lithedream.lithecore.A;
import io.github.lithedream.lithecore.AnyCompare;

public final class A3<V0, V1, V2> extends A implements Serializable, Comparable<A3<V0, V1, V2>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private V0 v0;

	private V1 v1;

	private V2 v2;

	public A3() {}

	public A3(V0 v0, V1 v1, V2 v2) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
	}

	public V0 getV0() {
		return v0;
	}

	public void setV0(V0 v0) {
		this.v0 = v0;
	}

	public V1 getV1() {
		return v1;
	}

	public void setV1(V1 v1) {
		this.v1 = v1;
	}

	public V2 getV2() {
		return v2;
	}

	public void setV2(V2 v2) {
		this.v2 = v2;
	}

	public V0 g0() {
		return v0;
	}

	public void s0(V0 v0) {
		this.v0 = v0;
	}

	public V1 g1() {
		return v1;
	}

	public void s1(V1 v1) {
		this.v1 = v1;
	}

	public V2 g2() {
		return v2;
	}

	public void s2(V2 v2) {
		this.v2 = v2;
	}

	public int compareTo(A3<V0, V1, V2> o) {
		if (this == o) {
			return 0;
		}
		if (o == null) {
			return 1;
		}
		int c = AnyCompare.compare(this.v0, o.v0);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v1, o.v1);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v2, o.v2);
		return c;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj instanceof A3 == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		A3 rhs = (A3) obj;
		return Objects.equals(v0, rhs.v0) && Objects.equals(v1, rhs.v1) && Objects.equals(v2, rhs.v2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v0, v1, v2);
	}

	@Override
	public int size() {
		return 3;
	}

	@Override
	public Object g(int index) {
		switch (index) {
			case 0:
				return g0();
			case 1:
				return g1();
			case 2:
				return g2();
			default:
				throw new IllegalArgumentException("A3.g(" + index + ")");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void s(int index, Object o) {
		switch (index) {
			case 0:
				s0((V0) o);
				break;
			case 1:
				s1((V1) o);
				break;
			case 2:
				s2((V2) o);
				break;
			default:
				throw new IllegalArgumentException("A3.s(" + index + ",...)");
		}
	}

	public static <K0, K1, K2> List<K0> toList0(Collection<A3<K0, K1, K2>> li) {
		List<K0> list = new ArrayList<K0>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g0());
		}
		return list;
	}

	public static <K0, K1, K2> List<K1> toList1(Collection<A3<K0, K1, K2>> li) {
		List<K1> list = new ArrayList<K1>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g1());
		}
		return list;
	}

	public static <K0, K1, K2> List<K2> toList2(Collection<A3<K0, K1, K2>> li) {
		List<K2> list = new ArrayList<K2>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g2());
		}
		return list;
	}

	public static <K0, K1, K2> Set<K0> toSet0(Collection<A3<K0, K1, K2>> li) {
		Set<K0> list = new LinkedHashSet<K0>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g0());
		}
		return list;
	}

	public static <K0, K1, K2> Set<K1> toSet1(Collection<A3<K0, K1, K2>> li) {
		Set<K1> list = new LinkedHashSet<K1>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g1());
		}
		return list;
	}

	public static <K0, K1, K2> Set<K2> toSet2(Collection<A3<K0, K1, K2>> li) {
		Set<K2> list = new LinkedHashSet<K2>();
		for (A3<K0, K1, K2> a3 : li) {
			list.add(a3.g2());
		}
		return list;
	}

}
