package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.Kind

/**
 * Monad is a typeclass that abstracts over sequential execution of code
 */
interface Monad<F> : Applicative<F> {

    /**
     * Wraps single value into type container
     */
    fun <A> just(value: A): Kind<F, A>

    /**
     * Takes a continuation function from the value A to a new Kind<F, B>, and returns a Kind<F, B>
     */
    fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>

}