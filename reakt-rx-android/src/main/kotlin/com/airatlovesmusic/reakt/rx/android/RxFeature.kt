package com.airatlovesmusic.reakt.rx.android

import com.airatlovesmusic.reakt.core.*
import com.airatlovesmusic.reakt.rx.android.instances.Channel
import com.airatlovesmusic.reakt.rx.android.instances.Stream
import com.airatlovesmusic.reakt.rx.android.instances.Subscribe
import com.airatlovesmusic.reakt.rx.android.instances.Switch
import com.airatlovesmusic.reakt.rx.android.types.Rx
import com.airatlovesmusic.reakt.rx.android.types.RxType
import com.airatlovesmusic.reakt.rx.android.types.toDisposable
import com.airatlovesmusic.reakt.rx.android.types.toFlow
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

typealias RxFeature<State, Event, Effect> = Feature<RxType, State, Event, Effect>
typealias RxActor<Event, Update> = Actor<RxType, Event, Update>

val RxDependenciesBag =
    DependenciesBag(Rx.Channel, Rx.Switch, Rx.Stream, Rx.Subscribe)

fun <State, Event, Effect> RxFeature(
    state: State,
    update: (State, Update<State, Effect>) -> Pair<State, Set<Effect>>,
    effectsHandler: RxActor<Event, Update<State, Effect>>
): RxFeature<State, Event, Effect> =
    Feature(
        state = state,
        update = update,
        dependencies = RxDependenciesBag,
        actor = effectsHandler
    )

fun <Event, Update> RxActor(
    f: (Event) -> Observable<Update>
): RxActor<Event, Update> =
    actor(
        deps = RxDependenciesBag,
        f = compose({ Rx(it) }, f)
    )

fun <A, B, C> compose(bc: (B) -> C, ab: (A) -> B): (A) -> C =
    {
        bc(ab(it))
    }

fun <Event> RxFeature<*, Event, *>.rxUpdate(event: Event): Disposable =
    update(event).toDisposable()

fun <State> RxFeature<State, *, *>.rxState() =
    state().toFlow()