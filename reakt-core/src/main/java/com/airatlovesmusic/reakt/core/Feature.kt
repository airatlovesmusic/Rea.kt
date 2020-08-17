package com.airatlovesmusic.reakt.core

import com.airatlovesmusic.reakt.core.algebra.typeclasses.Channel
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Stream
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Subscribe
import com.airatlovesmusic.reakt.core.algebra.typeclasses.Switch
import com.airatlovesmusic.reakt.core.algebra.types.KDisposable
import com.airatlovesmusic.reakt.core.algebra.types.Kind

class DependenciesBag<Type>(
    private val channel: Channel<Type>,
    private val shift: Switch<Type>,
    private val stream: Stream<Type>,
    private val consume: Subscribe<Type>
): Dependencies<Type>, Channel<Type> by channel, Switch<Type> by shift, Stream<Type> by stream, Subscribe<Type> by consume

interface Dependencies<Type>: Channel<Type>, Switch<Type>, Stream<Type>, Subscribe<Type>

data class Update<State, Effect>(
    val changeState: (State) -> State,
    val effects: (State) -> Set<Effect>
)

class Feature<Type, State, Event, Effect>(
    state: State,
    private val update: (State, Update<State, Effect>) -> Pair<State, Set<Effect>>,
    private val dependencies: DependenciesBag<Type>,
    private val actor: Actor<Type, Event, Update<State, Effect>>
): Dependencies<Type> by dependencies {

    private val stateChannel = conflated(state)
    private val effectChannel = through<Set<Effect>>()
    private val eventChannel = through<Event>()

    private val flowHandle: KDisposable<Type> =
        eventChannel
            .suspendRead()
            .switchTo(io())
            .flatMap { event -> actor.handle(event) }
            .flatMap { pair(stateChannel.suspendRead().take(1), just(it)) }
            .switchTo(computation())
            .fmap { (state, update) -> update(state, update) }
            .flatMap { (state, effects) ->
                pair(stateChannel.write(state), just(effects))
            }.fmap { (_, effects) -> effects }
            .flatMap { effects -> effectChannel.write(effects) }
            .subscribe()

    fun update(event: Event): KDisposable<Type> =
        eventChannel.write(event)
            .subscribe()

    fun state(): Kind<Type, State> =
        stateChannel.suspendRead()

    fun effect(): Kind<Type, Set<Effect>> =
        effectChannel.suspendRead()

    fun dispose() = flowHandle.dispose()

}