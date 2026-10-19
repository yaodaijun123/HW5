
fun align(a: String, b: String): Pair<String, String> {

    val match = 1
    val mismatch = -1
    val gap = -1

    val dp = Array(a.length + 1) { IntArray(b.length + 1) }

    // Initialize first row and column
    for (i in 0..a.length) {
        dp[i][0] = i * gap
    }

    for (j in 0..b.length) {
        dp[0][j] = j * gap
    }

    // Fill DP table
    for (i in 1..a.length) {
        for (j in 1..b.length) {

            val diagonal = dp[i - 1][j - 1] +
                    if (a[i - 1] == b[j - 1]) match else mismatch

            val up = dp[i - 1][j] + gap
            val left = dp[i][j - 1] + gap

            dp[i][j] = maxOf(diagonal, up, left)
        }
    }

    // Backtracking
    var i = a.length
    var j = b.length

    val resultA = StringBuilder()
    val resultB = StringBuilder()

    while (i > 0 || j > 0) {

        if (i > 0 && j > 0 &&
            dp[i][j] == dp[i - 1][j - 1] +
            if (a[i - 1] == b[j - 1]) match else mismatch
        ) {
            resultA.append(a[i - 1])
            resultB.append(b[j - 1])
            i--
            j--

        } else if (i > 0 &&
            dp[i][j] == dp[i - 1][j] + gap
        ) {
            resultA.append(a[i - 1])
            resultB.append('-')
            i--

        } else {
            resultA.append('-')
            resultB.append(b[j - 1])
            j--
        }
    }

    return Pair(
        resultA.reverse().toString(),
        resultB.reverse().toString()
    )
}

fun main() {
    val result = align("AGT", "AT")

    println(result.first)
    println(result.second)
}
