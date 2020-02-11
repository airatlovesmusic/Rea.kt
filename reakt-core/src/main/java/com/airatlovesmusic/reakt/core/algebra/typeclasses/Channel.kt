package com.airatlovesmusic.reakt.core.algebra.typeclasses

import com.airatlovesmusic.reakt.core.algebra.types.KChannel
import com.airatlovesmusic.reakt.core.algebra.types.Kind

interface Channel<F> {

    fun <A> through(): KChannel<F, A>

    fun <A> conflated(): KChannel<F, A>

    fun <A> conflated(default: A): KChannel<F, A>

    fun <A> KChannel<F, A>.write(value: A): Kind<F, Unit>

    fun <A> KChannel<F, A>.suspendRead(): Kind<F, A>

}