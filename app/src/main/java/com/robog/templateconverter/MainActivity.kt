package com.robog.templateconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.robog.lib.ConvertCenter
import com.robog.lib.Converter
import com.robog.lib.Mode
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val dateTemplate = "0000-00-00 00:00:00"
    private val telTemplate = "000 0000 0000"
    private val sensTemplate = "00****00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btDate.setOnClickListener {
            val dateTime = getDateTime("yyyyMMddHHmmss")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog(btDate.text, dateTime, dateTemplate, convertDate)
        }

        btDateShort.setOnClickListener {
            val dateTime = getDateTime("yyyyMMdd")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog(btDateShort.text, dateTime, dateTemplate, convertDate)
        }

        btDateLonger.setOnClickListener {
            val dateTime = getDateTime("yyyyMMddHHmmssSSSS")
            val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            showDialog(btDateLonger.text, dateTime, dateTemplate, convertDate)
        }

        btTel.setOnClickListener {
            val tel = "13812345678"
            val convertTel = ConvertCenter(TelConverter()).apply(tel)
            showDialog(btTel.text, tel, telTemplate, convertTel)
        }

        btSensitive.setOnClickListener {
            val data = "12345678"
            val convertData = ConvertCenter(SensitiveConverter(), Mode.REPLACE).apply(data)
            showDialog(btSensitive.text, data, sensTemplate, convertData)
        }
    }

    private fun getDateTime(pattern: String): String {
        val cal = Calendar.getInstance()
        val date = cal.time
        return SimpleDateFormat(pattern, Locale.CHINA).format(date)
    }

    private fun showDialog(title: CharSequence, src: String, template: String, result: String) {
        println("原始数据: $src\n\n数据模版: $template\n\n转换数据: $result")
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage("原始数据: $src\n\n数据模版: $template\n\n转换数据: $result")
                .show()
    }
}

class DateConverter : Converter {

    // '-' ' ' ':'
    override fun symbols(): ByteArray = "- :".toByteArray()

    override fun template(): String = "0000-00-00 00:00:00"
}

class TelConverter : Converter {

    override fun symbols(): ByteArray = " ".toByteArray()

    override fun template(): String = "000 0000 0000"
}

class SensitiveConverter : Converter {

    override fun symbols(): ByteArray = "*".toByteArray()

    override fun template(): String = "00****00"
}
