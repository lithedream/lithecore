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

public final class A4<V0, V1, V2, V3> extends A implements Serializable, Comparable<A4<V0, V1, V2, V3>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private V0 v0;

	private V1 v1;

	private V2 v2;

	private V3 v3;

	public A4() {
	}

	public A4(V0 v0, V1 v1, V2 v2, V3 v3) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
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

	public V3 getV3() {
		return v3;
	}

	public void setV3(V3 v3) {
		this.v3 = v3;
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

	public V3 g3() {
		return v3;
	}

	public void s3(V3 v3) {
		this.v3 = v3;
	}

	public int compareTo(A4<V0, V1, V2, V3> o) {
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
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v3, o.v3);
		return c;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj instanceof A4 == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		A4 rhs = (A4) obj;
		return Objects.equals(v0, rhs.v0) && Objects.equals(v1, rhs.v1) && Objects.equals(v2, rhs.v2)
				&& Objects.equals(v3, rhs.v3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v0, v1, v2, v3);

	}

	@Override
	public int size() {
		return 4;
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
			case 3:
				return g3();
			default:
				throw new IllegalArgumentException("A4.g(" + index + ")");
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
			case 3:
				s3((V3) o);
				break;
			default:
				throw new IllegalArgumentException("A4.s(" + index + ",...)");
		}
	}

	public static <K0, K1, K2, K3> List<K0> toList0(Collection<A4<K0, K1, K2, K3>> li) {
		List<K0> list = new ArrayList<K0>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g0());
		}
		return list;
	}

	public static <K0, K1, K2, K3> List<K1> toList1(Collection<A4<K0, K1, K2, K3>> li) {
		List<K1> list = new ArrayList<K1>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g1());
		}
		return list;
	}

	public static <K0, K1, K2, K3> List<K2> toList2(Collection<A4<K0, K1, K2, K3>> li) {
		List<K2> list = new ArrayList<K2>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g2());
		}
		return list;
	}

	public static <K0, K1, K2, K3> List<K3> toList3(Collection<A4<K0, K1, K2, K3>> li) {
		List<K3> list = new ArrayList<K3>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g3());
		}
		return list;
	}

	public static <K0, K1, K2, K3> Set<K0> toSet0(Collection<A4<K0, K1, K2, K3>> li) {
		Set<K0> list = new LinkedHashSet<K0>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g0());
		}
		return list;
	}

	public static <K0, K1, K2, K3> Set<K1> toSet1(Collection<A4<K0, K1, K2, K3>> li) {
		Set<K1> list = new LinkedHashSet<K1>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g1());
		}
		return list;
	}

	public static <K0, K1, K2, K3> Set<K2> toSet2(Collection<A4<K0, K1, K2, K3>> li) {
		Set<K2> list = new LinkedHashSet<K2>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g2());
		}
		return list;
	}

	public static <K0, K1, K2, K3> Set<K3> toSet3(Collection<A4<K0, K1, K2, K3>> li) {
		Set<K3> list = new LinkedHashSet<K3>();
		for (A4<K0, K1, K2, K3> a4 : li) {
			list.add(a4.g3());
		}
		return list;
	}

}
