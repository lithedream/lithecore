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

public final class A2<V0, V1> extends A implements Serializable, Comparable<A2<V0, V1>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private V0 v0;

	private V1 v1;

	public A2() {
	}

	public A2(V0 v0, V1 v1) {
		this.v0 = v0;
		this.v1 = v1;
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

	public int compareTo(A2<V0, V1> o) {
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
		return c;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj instanceof A2 == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		A2 rhs = (A2) obj;
		return Objects.equals(v0, rhs.v0) && Objects.equals(v1, rhs.v1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v0, v1);
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public Object g(int index) {
		switch (index) {
			case 0:
				return g0();
			case 1:
				return g1();
			default:
				throw new IllegalArgumentException("A2.g(" + index + ")");
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
			default:
				throw new IllegalArgumentException("A2.s(" + index + ",...)");
		}
	}

	public static <K0, K1> List<K0> toList0(Collection<A2<K0, K1>> li) {
		List<K0> list = new ArrayList<K0>();
		for (A2<K0, K1> a2 : li) {
			list.add(a2.g0());
		}
		return list;
	}

	public static <K0, K1> List<K1> toList1(Collection<A2<K0, K1>> li) {
		List<K1> list = new ArrayList<K1>();
		for (A2<K0, K1> a2 : li) {
			list.add(a2.g1());
		}
		return list;
	}

	public static <K0, K1> Set<K0> toSet0(Collection<A2<K0, K1>> li) {
		Set<K0> list = new LinkedHashSet<K0>();
		for (A2<K0, K1> a2 : li) {
			list.add(a2.g0());
		}
		return list;
	}

	public static <K0, K1> Set<K1> toSet1(Collection<A2<K0, K1>> li) {
		Set<K1> list = new LinkedHashSet<K1>();
		for (A2<K0, K1> a2 : li) {
			list.add(a2.g1());
		}
		return list;
	}

	public static <K0, K1> List<A2<K1, K0>> toShifted(Collection<A2<K0, K1>> li) {
		List<A2<K1, K0>> li2 = new ArrayList<A2<K1, K0>>();
		for (A2<K0, K1> a2 : li) {
			li2.add($(a2.g1(), a2.g0()));
		}
		return li2;
	}

}
