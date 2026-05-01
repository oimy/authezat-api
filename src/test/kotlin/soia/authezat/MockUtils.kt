package soia.authezat

import org.mockito.AdditionalAnswers.returnsArgAt
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing


fun <T> whenever(block: () -> T): OngoingStubbing<T> = `when`(block())

infix fun <T> OngoingStubbing<T>.answer(callAnswer: (InvocationOnMock) -> T): OngoingStubbing<T> {
    val answer: Answer<T> = Answer { callAnswer(it) }
    return this.thenAnswer(answer)
}

fun <T> capture(position: Int): Answer<T> = returnsArgAt(position)

fun <T> captureFirst(): Answer<T> = capture(position = 0)

fun <T> captureSecond(): Answer<T> = capture(position = 1)

fun <T> captureLast(): Answer<T> = capture(position = -1)

fun <T> any(): T = ArgumentMatchers.any()

fun <T> anyCollection(): Collection<T> = ArgumentMatchers.anyCollection()