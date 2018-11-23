## TemplateConverter
简单地通过设置特征符号以及模版格式转换数据

输出结果
----
日期(mode = ADD):
原始数据: 20181028162522
数据模版: 0000-00-00 00:00:00
转换数据: 2018-10-28 16:25:22

电话(mode = ADD):
原始数据: 13812345678
数据模版: 000 0000 0000
转换数据: 138 1234 5678

敏感信息(mode = REPLACE):
原始数据: 12345678
数据模版: 00\*\*\*\*00
转换数据: 12\*\*\*\*78

使用
---
以时间转换为例，需要实现symbols()以及template():
```kotlin
class DateConverter : Converter {
    
    // '-' ' ' ':'
    override fun symbols(): ByteArray = "- :".toByteArray()

    override fun template(): String = "0000-00-00 00:00:00"
}
// 通过ConvertCenter获取转换后数据
val convertDate = ConvertCenter(DateConverter()).apply(dateTime)
```
