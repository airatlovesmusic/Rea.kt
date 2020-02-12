package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Switch
import com.airatlovesmusic.reakt.core.algebra.types.KContext
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.*
import io.reactivex.rxjava3.schedulers.Schedulers

private val RxSwitchInstance =
    object : Switch<RxType> {
        override fun io(): KContext<RxType> =
            RxContext(Schedulers.io())

        override fun computation(): KContext<RxType> =
            RxContext(Schedulers.computation())

        override fun <A> Kind<RxType, A>.switchTo(context: KContext<RxType>): Kind<RxType, A> =
            toFlow().observeOn(context.fromContext()).fromFlow()

    }

val Rx.Companion.Switch
    get() = RxSwitchInstance