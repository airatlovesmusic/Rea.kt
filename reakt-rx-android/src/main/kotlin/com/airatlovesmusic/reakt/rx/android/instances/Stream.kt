package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Monad
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Stream
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.fromFlow
import com.airatlovesmusic.reakt.rx.android.types.toFlow
import io.reactivex.rxjava3.core.Observable

private val RxStreamInstance: Stream<RxType> =
    object : Stream<RxType>, Monad<RxType> by Rx.Monad {
        override fun <A, B> Kind<RxType, A>.scan(acc: B, f: (B, A) -> B): Kind<RxType, B> =
            toFlow().scan(acc, f).fromFlow()

        override fun <A> merge(ss: List<Kind<RxType, A>>): Kind<RxType, A> =
            Observable.merge(ss.map { it.toFlow() }).fromFlow()

        override fun <A> Kind<RxType, A>.take(number: Int): Kind<RxType, A> =
            toFlow().take(number.toLong()).fromFlow()

    }

val Rx.Companion.Stream
    get() = RxStreamInstance