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
            var convertDate = ""
            val time = getTime {
                convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            }
            showDialog(btDate.text, dateTime, dateTemplate, convertDate, time)
        }

        btDateShort.setOnClickListener {
            val dateTime = getDateTime("yyyyMMdd")
            var convertDate = ""
            val time = getTime {
                convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            }
            showDialog(btDateShort.text, dateTime, dateTemplate, convertDate, time)
        }

        btDateShortAll.setOnClickListener {
            val dateTime = getDateTime("yyyyMMdd")
            var convertDate = ""
            val time = getTime {
                convertDate = ConvertCenter(DateConverter(), Mode.TEMPLATE).apply(dateTime)
            }
            showDialog(btDateShortAll.text, dateTime, dateTemplate, convertDate, time)
        }

        btDateLonger.setOnClickListener {
            val dateTime = getDateTime("yyyyMMddHHmmssSSSS")
            var convertDate = ""
            val time = getTime {
                convertDate = ConvertCenter(DateConverter()).apply(dateTime)
            }
            showDialog(btDateLonger.text, dateTime, dateTemplate, convertDate, time)
        }

        btTel.setOnClickListener {
            val tel = "13812345678"
            var convertTel = ""
            val time = getTime {
                convertTel = ConvertCenter(TelConverter()).apply(tel)
            }
            showDialog(btTel.text, tel, telTemplate, convertTel, time)
        }

        btSensitive.setOnClickListener {
            val data = "12345678"
            var convertData = ""
            val time = getTime {
                convertData = ConvertCenter(SensitiveConverter(), Mode.REPLACE).apply(data)
            }

            showDialog(btSensitive.text, data, sensTemplate, convertData, time)
        }
    }

    private fun getDateTime(pattern: String): String {
        val cal = Calendar.getInstance()
        val date = cal.time
        return SimpleDateFormat(pattern, Locale.CHINA).format(date)
    }

    private fun showDialog(title: CharSequence, src: String, template: String, result: String, time: Long) {
        println("原始数据: $src\n\n数据模版: $template\n\n转换数据: $result")
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage("原始数据: $src\n\n数据模版: $template\n\n转换数据: $result\n\n耗时: $time ms")
                .show()
    }

    private fun getTime(f: () -> Unit): Long {
        val startTime = System.currentTimeMillis()
        f()
        return System.currentTimeMillis() - startTime
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
