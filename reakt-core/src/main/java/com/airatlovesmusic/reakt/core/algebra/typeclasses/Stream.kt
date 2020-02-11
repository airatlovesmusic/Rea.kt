package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.Kind

/**
 * Streams are infinite lists
 */
interface Stream<F> : Monad<F> {

    fun <A, B> Kind<F, A>.scan(acc: B, f: (B, A) -> B): Kind<F, B>

    fun <A> merge(ss: List<Kind<F, A>>): Kind<F, A>

    fun <A> Kind<F, A>.take(number: Int): Kind<F, A>

}