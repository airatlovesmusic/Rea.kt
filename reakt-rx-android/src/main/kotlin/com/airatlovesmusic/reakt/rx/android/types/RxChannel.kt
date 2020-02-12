package com.airatlovesmusic.reakt.rx.android.types

import com.airatlovesmusic.reakt.core.algebra.types.KChannel
import io.reactivex.rxjava3.subjects.Subject

class RxChannel<A>(val subject: Subject<A>) : KChannel<RxType, A>

fun <A> KChannel<RxType, A>.toChannel(): RxChannel<A> = this as RxChannel