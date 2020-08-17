package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Functor
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.fromFlow
import com.airatlovesmusic.reakt.rx.android.types.toFlow

private val RxFunctorInstance: Functor<RxType> =
    object : Functor<RxType> {
        override fun <A, B> lift(f: (A) -> B): (Kind<RxType, A>) -> Kind<RxType, B> = {
            it.toFlow().map(f).fromFlow()
        }

        override fun <A, B> Kind<RxType, A>.fmap(f: (A) -> B): Kind<RxType, B> =
            toFlow().map(f).fromFlow()
    }

val Rx.Companion.Functor
    get() = RxFunctorInstance