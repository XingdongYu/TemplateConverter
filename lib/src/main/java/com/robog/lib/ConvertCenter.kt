package com.robog.lib

/**
 * Created by yuxingdong on 2018/11/23.
 */
class ConvertCenter @JvmOverloads constructor(var converter: Converter, var mode: Mode = Mode.ADD) {

    var symbols = converter.symbols()
    var template = converter.template()
    private val charset = Charsets.UTF_8

    fun apply(src: String?): String {

        if (src == null) return ""

        val dest = ByteArray(template.length)
        val dataBytes = src.toByteArray(charset)
        val templateBytes = template.toByteArray(charset)
        var dataIndex = 0

        for (i in templateBytes.indices) {

            if (i - dataIndex > dataBytes.size - 1) {
                dest[i] = ' '.toByte()
                continue
            }

            // 是否命中特征符
            var hit = false
            for (b in symbols) {
                if (templateBytes[i] == b) {
                    dest[i] = b
                    dataIndex++
                    hit = true
                    break
                }
            }

            if (!hit) {
                if (mode == Mode.REPLACE && i < dataBytes.size) {
                    dest[i] = dataBytes[i]
                } else {
                    dest[i] = dataBytes[i - dataIndex]
                }
            }
        }
        return dest.toString(charset)
    }
}

enum class Mode {
    ADD, REPLACE
}