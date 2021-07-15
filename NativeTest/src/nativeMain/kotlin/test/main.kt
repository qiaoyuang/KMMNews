package test

import kotlinx.coroutines.*
import platform.CoreFoundation.CFRunLoopRun
import kotlin.native.concurrent.DetachedObjectGraph
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.attach


fun main() {
    val testData = TestData(8)
    val graph = DetachedObjectGraph(TransferMode.UNSAFE) { testData }
    CoroutineScope(Dispatchers.Default).launch {
        println("打印：${graph.attach().index++}")
    }
    CFRunLoopRun()
    //testFunctionCall()
}

data class TestData(var index: Int = 0)


/**
 * Demo2：虚函数调用的静态分派
 */

fun testFunctionCall() {
    val data = Data<A>(B())
    val a = data.getSomething()
    a.print()
}

abstract class Base {
    abstract fun print()
}
class A : Base() {
    override fun print() = println("class A print")
}
class B : Base() {
    override fun print() = println("class A print")
}

class Data<T : Base>(private val b: Base) {
    fun getSomething(): T = b as T
}