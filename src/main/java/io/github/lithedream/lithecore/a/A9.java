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

public final class A9<V0, V1, V2, V3, V4, V5, V6, V7, V8> extends A
		implements Serializable, Comparable<A9<V0, V1, V2, V3, V4, V5, V6, V7, V8>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private V0 v0;

	private V1 v1;

	private V2 v2;

	private V3 v3;

	private V4 v4;

	private V5 v5;

	private V6 v6;

	private V7 v7;

	private V8 v8;

	public A9() {
	}

	public A9(V0 v0, V1 v1, V2 v2, V3 v3, V4 v4, V5 v5, V6 v6, V7 v7, V8 v8) {
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.v5 = v5;
		this.v6 = v6;
		this.v7 = v7;
		this.v8 = v8;
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

	public V4 getV4() {
		return v4;
	}

	public void setV4(V4 v4) {
		this.v4 = v4;
	}

	public V5 getV5() {
		return v5;
	}

	public void setV5(V5 v5) {
		this.v5 = v5;
	}

	public V6 getV6() {
		return v6;
	}

	public void setV6(V6 v6) {
		this.v6 = v6;
	}

	public V7 getV7() {
		return v7;
	}

	public void setV7(V7 v7) {
		this.v7 = v7;
	}

	public V8 getV8() {
		return v8;
	}

	public void setV8(V8 v8) {
		this.v8 = v8;
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

	public V4 g4() {
		return v4;
	}

	public void s4(V4 v4) {
		this.v4 = v4;
	}

	public V5 g5() {
		return v5;
	}

	public void s5(V5 v5) {
		this.v5 = v5;
	}

	public V6 g6() {
		return v6;
	}

	public void s6(V6 v6) {
		this.v6 = v6;
	}

	public V7 g7() {
		return v7;
	}

	public void s7(V7 v7) {
		this.v7 = v7;
	}

	public V8 g8() {
		return v8;
	}

	public void s8(V8 v8) {
		this.v8 = v8;
	}

	public int compareTo(A9<V0, V1, V2, V3, V4, V5, V6, V7, V8> o) {
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
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v4, o.v4);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v5, o.v5);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v6, o.v6);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v7, o.v7);
		if (c != 0) {
			return c;
		}
		c = AnyCompare.compare(this.v8, o.v8);
		return c;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj instanceof A9 == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		A9 rhs = (A9) obj;
		return Objects.equals(v0, rhs.v0) && Objects.equals(v1, rhs.v1) && Objects.equals(v2, rhs.v2)
				&& Objects.equals(v3, rhs.v3) && Objects.equals(v4, rhs.v4) && Objects.equals(v5, rhs.v5)
				&& Objects.equals(v6, rhs.v6) && Objects.equals(v7, rhs.v7) && Objects.equals(v8, rhs.v8);
	}

	@Override
	public int hashCode() {
		return Objects.hash(v0, v1, v2, v3, v4, v5, v6, v7, v8);
	}

	@Override
	public int size() {
		return 9;
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
			case 4:
				return g4();
			case 5:
				return g5();
			case 6:
				return g6();
			case 7:
				return g7();
			case 8:
				return g8();
			default:
				throw new IllegalArgumentException("A9.g(" + index + ")");
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
			case 4:
				s4((V4) o);
				break;
			case 5:
				s5((V5) o);
				break;
			case 6:
				s6((V6) o);
			case 7:
				s7((V7) o);
				break;
			case 8:
				s8((V8) o);
				break;
			default:
				throw new IllegalArgumentException("A9.s(" + index + ",...)");
		}
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K0> toList0(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K0> list = new ArrayList<K0>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g0());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K1> toList1(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K1> list = new ArrayList<K1>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g1());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K2> toList2(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K2> list = new ArrayList<K2>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g2());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K3> toList3(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K3> list = new ArrayList<K3>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g3());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K4> toList4(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K4> list = new ArrayList<K4>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g4());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K5> toList5(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K5> list = new ArrayList<K5>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g5());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K6> toList6(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K6> list = new ArrayList<K6>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g6());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K7> toList7(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K7> list = new ArrayList<K7>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g7());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> List<K8> toList8(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		List<K8> list = new ArrayList<K8>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g8());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K0> toSet0(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K0> list = new LinkedHashSet<K0>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g0());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K1> toSet1(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K1> list = new LinkedHashSet<K1>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g1());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K2> toSet2(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K2> list = new LinkedHashSet<K2>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g2());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K3> toSet3(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K3> list = new LinkedHashSet<K3>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g3());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K4> toSet4(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K4> list = new LinkedHashSet<K4>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g4());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K5> toSet5(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K5> list = new LinkedHashSet<K5>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g5());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K6> toSet6(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K6> list = new LinkedHashSet<K6>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g6());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K7> toSet7(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K7> list = new LinkedHashSet<K7>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g7());
		}
		return list;
	}

	public static <K0, K1, K2, K3, K4, K5, K6, K7, K8> Set<K8> toSet8(
			Collection<A9<K0, K1, K2, K3, K4, K5, K6, K7, K8>> li) {
		Set<K8> list = new LinkedHashSet<K8>();
		for (A9<K0, K1, K2, K3, K4, K5, K6, K7, K8> a5 : li) {
			list.add(a5.g8());
		}
		return list;
	}

}
