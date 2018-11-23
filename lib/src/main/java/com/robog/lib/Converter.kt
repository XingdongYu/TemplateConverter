package com.robog.lib

/**
 * Created by yuxingdong on 2018/11/23.
 */
interface Converter {

    /**
     * 模版中的特征符号
     */
    fun symbols(): ByteArray

    /**
     * 模版字符串
     */
    fun template(): String
}