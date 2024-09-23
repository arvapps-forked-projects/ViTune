package app.vitune.core.data.utils

class RingBuffer<T>(val size: Int, init: (index: Int) -> T) : Iterable<T> {
    private val list = MutableList(size, init)

    @get:Synchronized
    @set:Synchronized
    private var index = 0

    operator fun get(index: Int) = list.getOrNull(index)
    operator fun plusAssign(element: T) { list[index++ % size] = element }

    override fun iterator() = list.iterator()
}
