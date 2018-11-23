## TemplateConverter
简单地通过设置特征符号以及模版格式转换数据

转换结果
---
<img src="https://github.com/XingdongYu/TemplateConverter/blob/master/art/date.png" height="384" width="216">
<img src="https://github.com/XingdongYu/TemplateConverter/blob/master/art/tel.png" height="384" width="216" align="center">
<img src="https://github.com/XingdongYu/TemplateConverter/blob/master/art/sens.png" height="384" width="216" align="right">

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
