package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Subscribe
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxDisposable
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.toFlow

private val RxSubscribeInstance: Subscribe<RxType> =
    object : Subscribe<RxType> {
        override fun <A> Kind<RxType, A>.subscribe(f: (A) -> Unit): RxDisposable =
            RxDisposable(toFlow().subscribe(f))
    }

val Rx.Companion.Subscribe
    get() = RxSubscribeInstance