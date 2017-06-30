# lithecore

## Synopsis

Java library to write less code (enhancements for reflection, properties, collections, and other shortcuts)

## Motivation

I like to express a lot of logic by writing as little as possible. This library helps me to write really concise code.

## API Reference

* A: fixed size arrays (A2, A3, A4, A5) with generics to enforce type safety, with static methods to create and organize them in Lists, Sets and Maps
* F: class that allows getter objects creation and use, to collect simple and complex properties into Lists and Maps; it is also possible to represent method invocations for later use
* FlashMap: Map implementation optimized for memory and really small datasets
* FlashSet: Set implementation optimized for memory and really small datasets
* LitheString: Class for String compression
* X: general utility static methods

## Code Example
```java
List<T> list = ... ;
Gate<T,PROPERTY2TYPE> gate = F.$(list).gate(F.y? null : F.o(list).getProperty1().getProperty2(),"getProperty1().getProperty2()");

List<PROPERTY2TYPE> listValues = gate.onList(list); //getProperty1().getProperty2() is called on every T in list
```

```java
SOMEOBJECT obj = ...;
Erg<SOMEOBJECT> erg = F.$(obj);
Call<SOMEOBJECT> call = erg.call("someMethod");
if (F.never) {
    call.__.someMethod();
}

call.on(obj); // calls obj.someMethod();
```

```java
SOMEOBJECT obj = ...;
Demon<T,RETURNTYPE> demon = F.$(obj).demon(F.y? null : obj.methodWithParams(F.o(Parameter1.class),F.o(Parameter2.class)),"methodWithParams()");

Parameter1 parameter1=...;
Parameter2 parameter2=...;
RETURNTYPE ret=demon.on(obj,parameter1,parameter2); // calls obj.methodWithParams(parameter1,parameter2)
```

## Author

* **lithedream**

## License

LGPL-2.1
