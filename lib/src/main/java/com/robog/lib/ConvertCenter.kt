package com.robog.lib

/**
 * Created by yuxingdong on 2018/11/23.
 */
class ConvertCenter @JvmOverloads constructor(var converter: Converter, var mode: Mode = Mode.NORMAL) {

    var symbols = converter.symbols()
    var template = converter.template()
    private val charset = Charsets.UTF_8

    fun apply(src: String?): String {

        if (src == null) return ""

        val dest = ByteArray(
                if (mode == Mode.SOURCE && src.length >= template.length)
                    src.length
                else
                    template.length
        )
        val dataBytes = src.toByteArray(charset)
        val templateBytes = template.toByteArray(charset)
        var dataIndex = 0

        for (i in templateBytes.indices) {

            // 若超过原始数据长度
            if (i - dataIndex > dataBytes.size - 1) {
                dest[i] = if (mode == Mode.TEMPLATE) templateBytes[i] else ' '.toByte()
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
                if ((mode == Mode.REPLACE || mode == Mode.SOURCE) && i < dataBytes.size) {
                    dest[i] = dataBytes[i]
                } else {
                    dest[i] = dataBytes[i - dataIndex]
                }
            }
        }
        if (mode == Mode.SOURCE && src.length > template.length) {
            val srcBytes = src.toByteArray(charset)
            for (i in (template.length - 1)until src.length) {
                dest[i] = srcBytes[i]
            }
        }
        return dest.toString(charset)
    }
}

enum class Mode {
    NORMAL,
    // 替换模式，以模版为准
    REPLACE,
    // 替换模式，以原数据为准
    SOURCE,
    // 不足以模版数据补充
    TEMPLATE
}