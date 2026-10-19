class Matrix(val size: Int) {

    val data = Array(size) { IntArray(size) }

    fun get(row: Int, col: Int): Int {
        return data[row][col]
    }

    fun set(row: Int, col: Int, value: Int) {
        data[row][col] = value
    }


    fun multiply(other: Matrix): Matrix {
        require(size == other.size)

        val result = Matrix(size)

        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    result.data[i][j] +=
                        data[i][k] * other.data[k][j]
                }
            }
        }

        return result
    }

    fun add(other: Matrix): Matrix {
        val result = Matrix(size)

        for (i in 0 until size) {
            for (j in 0 until size) {
                result.data[i][j] = data[i][j] + other.data[i][j]
            }
        }

        return result
    }

    fun subtract(other: Matrix): Matrix {
        val result = Matrix(size)

        for (i in 0 until size) {
            for (j in 0 until size) {
                result.data[i][j] = data[i][j] - other.data[i][j]
            }
        }

        return result
    }


    fun strassenMultiply(other: Matrix): Matrix {
        require(size == other.size)
        require(size > 0 && (size and (size - 1)) == 0)

        if (size == 1) {
            val result = Matrix(1)
            result.data[0][0] = data[0][0] * other.data[0][0]
            return result
        }

        val half = size / 2

        fun split(m: Matrix, row: Int, col: Int): Matrix {
            val part = Matrix(half)

            for (i in 0 until half) {
                for (j in 0 until half) {
                    part.data[i][j] = m.data[i + row][j + col]
                }
            }

            return part
        }

        val a = split(this, 0, 0)
        val b = split(this, 0, half)
        val c = split(this, half, 0)
        val d = split(this, half, half)

        val e = split(other, 0, 0)
        val f = split(other, 0, half)
        val g = split(other, half, 0)
        val h = split(other, half, half)

        // Seven recursive multiplications
        val p1 = a.add(d).strassenMultiply(e.add(h))
        val p2 = c.add(d).strassenMultiply(e)
        val p3 = a.strassenMultiply(f.subtract(h))
        val p4 = d.strassenMultiply(g.subtract(e))
        val p5 = a.add(b).strassenMultiply(h)
        val p6 = c.subtract(a).strassenMultiply(e.add(f))
        val p7 = b.subtract(d).strassenMultiply(g.add(h))

        val r11 = p1.add(p4).subtract(p5).add(p7)
        val r12 = p3.add(p5)
        val r21 = p2.add(p4)
        val r22 = p1.subtract(p2).add(p3).add(p6)

        // Combine the four result blocks
        val result = Matrix(size)

        for (i in 0 until half) {
            for (j in 0 until half) {
                result.data[i][j] = r11.data[i][j]
                result.data[i][j + half] = r12.data[i][j]
                result.data[i + half][j] = r21.data[i][j]
                result.data[i + half][j + half] = r22.data[i][j]
            }
        }

        return result
    }

}
