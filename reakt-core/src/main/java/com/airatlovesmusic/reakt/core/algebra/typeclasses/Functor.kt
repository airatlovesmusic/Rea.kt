package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.Kind

/**
 * Functor typeclass abstracts the ability to map over the computational context of a type constructor
 */
interface Functor<F> {

    fun <A, B> lift(f: (A) -> B): (Kind<F, A>) -> Kind<F, B>

    fun <A, B> Kind<F, A>.fmap(f: (A) -> B): Kind<F, B> =
        lift(f)(this)

}