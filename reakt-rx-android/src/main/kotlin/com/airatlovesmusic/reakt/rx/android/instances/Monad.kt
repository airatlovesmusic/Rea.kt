package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Applicative
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Monad
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.fromFlow
import com.airatlovesmusic.reakt.rx.android.types.toFlow
import io.reactivex.rxjava3.core.Observable

private val RxMonadInstance: Monad<RxType> =
    object : Monad<RxType>, Applicative<RxType> by Rx.Applicative {
        override fun <A> just(value: A): Kind<RxType, A> =
            Observable.just(value).fromFlow()

        override fun <A, B> Kind<RxType, A>.flatMap(f: (A) -> Kind<RxType, B>): Kind<RxType, B> =
            toFlow().flatMap { f(it).toFlow() }.fromFlow()

    }

val Rx.Companion.Monad
    get() = RxMonadInstance