import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Converts list of size 2 to pair.
 */
fun <E> List<E>.toPair(): Pair<E, E> = when (size) {
    2 -> this[0] to this[1]
    else -> error("Size $size is incompatible to create a pair")
}
