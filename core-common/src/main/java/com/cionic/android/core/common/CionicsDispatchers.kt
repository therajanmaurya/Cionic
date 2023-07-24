package com.cionic.android.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val niaDispatcher: CionicsDispatchers)

enum class CionicsDispatchers {
    Default,
    IO,
}
