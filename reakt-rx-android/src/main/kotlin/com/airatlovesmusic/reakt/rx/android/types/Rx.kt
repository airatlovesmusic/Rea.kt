package com.airatlovesmusic.reakt.rx.android.types

import com.airatlovesmusic.reakt.core.algebra.types.Kind
import io.reactivex.rxjava3.core.Observable

class RxType private constructor()
typealias RxOf<A> = Kind<RxType, A>

class Rx<A>(val flow: Observable<A>) : RxOf<A> {
    companion object
}

fun <A> RxOf<A>.toFlow() = (this as Rx<A>).flow
fun <A> Observable<A>.fromFlow() = Rx(this)
