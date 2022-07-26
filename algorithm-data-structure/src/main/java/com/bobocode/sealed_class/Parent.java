package com.bobocode.sealed_class;

/**
 * sealed classes tell as which classes can implement or if sealed is interface, implements it.
 *
 * In this case {@link OnlyChild} can extends {@link Parent} class
 */
public sealed class Parent permits OnlyChild{
}

final class OnlyChild extends Parent{

}
