
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun main() {

    val sizes = listOf(2, 4, 8, 16, 32, 64, 128)
    val trials = 5

    println("Size,Normal(ms),Strassen(ms)")

    for (size in sizes) {

        var normalTotal = 0.0
        var strassenTotal = 0.0

        repeat(trials) {
            val a = Matrix(size)
            val b = Matrix(size)

            for (i in 0 until size) {
                for (j in 0 until size) {
                    a.set(i, j, Random.nextInt(10))
                    b.set(i, j, Random.nextInt(10))
                }
            }

            val normalTime = measureTime {
                a.multiply(b)
            }

            val strassenTime = measureTime {
                a.strassenMultiply(b)
            }

            normalTotal += normalTime.toDouble(DurationUnit.MILLISECONDS)
            strassenTotal += strassenTime.toDouble(DurationUnit.MILLISECONDS)
        }

        println(
            "$size,${normalTotal / trials},${strassenTotal / trials}"
        )
    }
}
