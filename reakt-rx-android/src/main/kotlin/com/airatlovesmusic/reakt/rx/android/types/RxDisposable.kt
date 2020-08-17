package com.airatlovesmusic.reakt.rx.android.types

import com.airatlovesmusic.reakt.core.algebra.types.KDisposable
import io.reactivex.rxjava3.disposables.Disposable

class RxDisposable(val disposable: Disposable) : KDisposable<RxType> {
    override fun dispose() {
        disposable.dispose()
    }
}

fun KDisposable<RxType>.toDisposable() = (this as RxDisposable).disposable

