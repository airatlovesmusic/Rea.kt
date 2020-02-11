package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.Kind

/**
 * Applicative typeclass abstracts the ability to lift values and apply functions over the computational context of a type constructor
 */
interface Applicative<F> : Functor<F> {

    fun <A, B, C> lift(f: (A, B) -> C): (Kind<F, A>, Kind<F, B>) -> Kind<F, C>

    fun <A, B, C> zip(a: Kind<F, A>, b: Kind<F, B>, f: (A, B) -> C): Kind<F, C> =
        lift(f)(a, b)

    fun <A, B> pair(a: Kind<F, A>, b: Kind<F, B>): Kind<F, Pair<A, B>> =
        zip(a, b, ::Pair)

}