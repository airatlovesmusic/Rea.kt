package com.airatlovesmusic.reakt.rx.android.types

import com.airatlovesmusic.reakt.core.algebra.types.KContext
import io.reactivex.rxjava3.core.Scheduler

class RxContext(val scheduler: Scheduler) : KContext<RxType>

fun KContext<RxType>.fromContext() = (this as RxContext).scheduler