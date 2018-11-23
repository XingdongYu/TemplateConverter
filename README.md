## TemplateConverter
简单地自定义特征符号以及模版格式进行数据格式转换

输出结果
----
|类目|日期|电话|敏感信息|
|:-:|:-:|:-:|:-:|
|Mode|ADD|ADD|REPLACE|
|原始数据|20181028162522|13812345678|12345678|
|数据模版|0000-00-00 00:00:00|000 0000 0000|00\*\*\*\*00|
|转换数据|2018-10-28 16:25:22|138 1234 5678|12\*\*\*\*78|

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
