package com.valu.exercise_01.base.domain.usecase


interface SuspendableUseCase<I, O> {

    suspend fun execute(input: I? = null): O
}