package com.erill.roger.commons.mvi

import io.reactivex.FlowableTransformer

interface MviProcessor<A, R> {

    fun processAction(): FlowableTransformer<A, R>
}