package com.carbontower.resources

import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Encrypt {
    private val algorithm = "AES/CBC/NoPadding"

    private val keyValue = byteArrayOf(
        '0'.toByte(),
        '1'.toByte(),
        '2'.toByte(),
        '3'.toByte(),
        '4'.toByte(),
        '5'.toByte(),
        '6'.toByte(),
        '7'.toByte(),
        '8'.toByte(),
        '9'.toByte(),
        'a'.toByte(),
        'b'.toByte(),
        'c'.toByte(),
        'd'.toByte(),
        'e'.toByte(),
        'f'.toByte()
    )

    private val ivValue = byteArrayOf(
        'f'.toByte(),
        'e'.toByte(),
        'd'.toByte(),
        'c'.toByte(),
        'b'.toByte(),
        'a'.toByte(),
        '9'.toByte(),
        '8'.toByte(),
        '7'.toByte(),
        '6'.toByte(),
        '5'.toByte(),
        '4'.toByte(),
        '3'.toByte(),
        '2'.toByte(),
        '1'.toByte(),
        '0'.toByte()
    )

    private val ivspec = IvParameterSpec(ivValue)
    private val keyspec = SecretKeySpec(keyValue, "AES")

    fun encrypt(Data: String): String {

        val data: String = padString(Data)

        val c = Cipher.getInstance(algorithm)
        c.init(
            Cipher.ENCRYPT_MODE,
            keyspec,
            ivspec
        )
        val encVal = c.doFinal(data.toByteArray())
        return BASE64Encoder().encode(encVal)
    }

    fun decrypt(encryptedData: String): String {
        val c = Cipher.getInstance(algorithm)
        c.init(
            Cipher.DECRYPT_MODE,
            keyspec,
            ivspec
        )
        val decordedValue = BASE64Decoder().decodeBuffer(encryptedData)
        val decValue = c.doFinal(decordedValue)
        return String(decValue)
    }

    private fun padString(source: String): String {
        var newSource = source
        val paddingChar = ' '
        val size = 16
        val x = newSource.length % size
        val padLength = size - x

        for (i in 0 until padLength) {
            newSource += paddingChar
        }
        return newSource
    }

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

    fun hash512(input: String): String {
        return hashString("SHA-512", input)
    }
}