package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.Kind

interface Monad<F> : Applicative<F> {

    fun <A> just(value: A): Kind<F, A>

    fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>

}