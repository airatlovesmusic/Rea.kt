package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Applicative
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Functor
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.fromFlow
import com.airatlovesmusic.reakt.rx.android.types.toFlow
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction

private val RxApplicativeInstance: Applicative<RxType> =
    object : Applicative<RxType>, Functor<RxType> by Rx.Functor  {
        override fun <A, B, C> lift(f: (A, B) -> C): (Kind<RxType, A>, Kind<RxType, B>) -> Kind<RxType, C> = { k1, k2 ->
            Observable.zip(k1.toFlow(), k2.toFlow(), BiFunction(f)).fromFlow()
        }
    }

val Rx.Companion.Applicative
    get() = RxApplicativeInstance