package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.KContext
import com.airatlovesmusic.reakt.core.algebra.types.Kind

interface Switch<F> {
    fun io(): KContext<F>
    fun computation(): KContext<F>
    fun <A> Kind<F, A>.switchTo(context: KContext<F>): Kind<F, A>
}