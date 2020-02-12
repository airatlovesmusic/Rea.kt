package com.airatlovesmusic.reakt.rx.android.instances

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Channel
import com.airatlovesmusic.reakt.core.algebra.types.KChannel
import com.airatlovesmusic.reakt.core.algebra.types.Kind
import com.airatlovesmusic.reakt.rx.android.types.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

private val RxChannelInstance =
    object : Channel<RxType> {
        override fun <A> through(): KChannel<RxType, A> =
            RxChannel(PublishSubject.create())

        override fun <A> conflated(default: A): KChannel<RxType, A> =
            RxChannel(BehaviorSubject.createDefault(default))

        override fun <A> KChannel<RxType, A>.write(value: A): Kind<RxType, Unit> =
            Observable.fromCallable {
                toChannel().subject.onNext(value)
            }.fromFlow()

        override fun <A> KChannel<RxType, A>.suspendRead(): Kind<RxType, A> =
            toChannel().subject.fromFlow()

    }

val Rx.Companion.Channel
    get() = RxChannelInstance