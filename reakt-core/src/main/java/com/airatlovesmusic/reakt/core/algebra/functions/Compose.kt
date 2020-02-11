package com.airatlovesmusic.reakt.core.algebra.functions

fun <A, B, C> compose(f1: (B) -> C, f2: (A) -> B): (A) -> C = {
    f1(f2(it))
}
