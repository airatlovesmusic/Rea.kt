package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.KDisposable
import com.airatlovesmusic.reakt.core.algebra.types.Kind

interface Subscribe<F> {

    fun <A> Kind<F, A>.subscribe(f: (A) -> Unit = {}): KDisposable<F>

}