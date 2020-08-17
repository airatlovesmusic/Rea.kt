package com.airatlovesmusic.reakt.core

import com.airatlovesmusic.reakt.core.algebra.types.Kind

interface Actor<F, in E, out M> : Dependencies<F> {
    fun handle(e: E): Kind<F, M>
}

fun <F, E, M> actor(deps: DependenciesBag<F>, f: (E) -> Kind<F, M>): Actor<F, E, M> =
    object : Actor<F, E, M>, Dependencies<F> by deps {
        override fun handle(e: E): Kind<F, M> = f(e)
    }