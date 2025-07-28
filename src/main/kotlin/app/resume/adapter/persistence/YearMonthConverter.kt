package app.resume.adapter.persistence

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.YearMonth

@Converter(autoApply = true)
class YearMonthConverter : AttributeConverter<YearMonth, String> {
    override fun convertToDatabaseColumn(attribute: YearMonth?): String? {
        return attribute?.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): YearMonth? {
        return dbData?.let { YearMonth.parse(it) }
    }
}