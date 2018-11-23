package com.robog.templateconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.robog.lib.ConvertCenter
import com.robog.lib.Converter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val dateTemplate = "0000-00-00 00:00:00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btDate.setOnClickListener {
            val dateTime = getDateTime("yyyyMMddHHmmss")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog("原始日期: $dateTime\n模版: $dateTemplate\n转换日期: $convertDate")
        }

        btDateShort.setOnClickListener {
            val dateTime = getDateTime("yyyyMMdd")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog("原始日期: $dateTime\n模版: $dateTemplate\n转换日期: $convertDate")
        }

        btDateLonger.setOnClickListener {
            val dateTime = getDateTime("yyyyMMddHHmmssSSSS")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog("原始日期: $dateTime\n模版: $dateTemplate\n转换日期: $convertDate")
        }

        btTel.setOnClickListener {
            val tel = "13888888888"
            val convertTel = ConvertCenter(TelConverter()).apply(tel)
            showDialog("原始电话: $tel\n转换电话: $convertTel")
        }

    }

    private fun getDateTime(pattern: String): String {
        val cal = Calendar.getInstance()
        val date = cal.time
        return SimpleDateFormat(pattern, Locale.CHINA).format(date)
    }

    private fun showDialog(msg: String) {
        AlertDialog.Builder(this).setMessage(msg).show()
    }
}

class DateConverter : Converter {

    // '-'
    // ' '
    // ':'
    override fun symbols(): ByteArray = "- :".toByteArray()

    override fun template(): String = "0000-00-00 00:00:00"
}

class TelConverter : Converter {

    override fun symbols(): ByteArray = " ".toByteArray()

    override fun template(): String = "000 0000 0000"
}
