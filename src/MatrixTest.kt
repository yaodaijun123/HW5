import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixTest {

    @Test
    fun testNormalMultiply() {
        val a = Matrix(2)
        val b = Matrix(2)

        a.set(0, 0, 1)
        a.set(0, 1, 2)
        a.set(1, 0, 3)
        a.set(1, 1, 4)

        b.set(0, 0, 5)
        b.set(0, 1, 6)
        b.set(1, 0, 7)
        b.set(1, 1, 8)

        val result = a.multiply(b)

        assertEquals(19, result.get(0, 0))
        assertEquals(22, result.get(0, 1))
        assertEquals(43, result.get(1, 0))
        assertEquals(50, result.get(1, 1))
    }

    @Test
    fun testStrassenMultiply() {
        val a = Matrix(2)
        val b = Matrix(2)

        a.set(0, 0, 1)
        a.set(0, 1, 2)
        a.set(1, 0, 3)
        a.set(1, 1, 4)

        b.set(0, 0, 5)
        b.set(0, 1, 6)
        b.set(1, 0, 7)
        b.set(1, 1, 8)

        val result = a.strassenMultiply(b)

        assertEquals(19, result.get(0, 0))
        assertEquals(22, result.get(0, 1))
        assertEquals(43, result.get(1, 0))
        assertEquals(50, result.get(1, 1))
    }

    @Test
    fun testLargerMatrix() {
        val a = Matrix(4)
        val b = Matrix(4)

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                a.set(i, j, i + j + 1)
                b.set(i, j, i * 2 - j)
            }
        }

        val normal = a.multiply(b)
        val strassen = a.strassenMultiply(b)

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                assertEquals(
                    normal.get(i, j),
                    strassen.get(i, j)
                )
            }
        }
    }

    @Test
    fun testSingleElement() {
        val a = Matrix(1)
        val b = Matrix(1)

        a.set(0, 0, 6)
        b.set(0, 0, 7)

        assertEquals(42, a.multiply(b).get(0, 0))
        assertEquals(42, a.strassenMultiply(b).get(0, 0))
    }
}