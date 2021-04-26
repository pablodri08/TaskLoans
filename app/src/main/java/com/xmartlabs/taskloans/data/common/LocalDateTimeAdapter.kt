package com.xmartlabs.taskloans.data.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter(private val dateTimeFormat: DateTimeFormatter) : TypeAdapter<LocalDateTime?>() {

  @RequiresApi(Build.VERSION_CODES.O)
  @Throws(IOException::class)
  override fun write(jsonWriter: JsonWriter, localDate: LocalDateTime?) {
    jsonWriter.value(localDate?.let(dateTimeFormat::format))
  }

  @RequiresApi(Build.VERSION_CODES.O)
  @Throws(IOException::class)
  override fun read(jsonReader: JsonReader): LocalDateTime? {
    if (jsonReader.peek() === JsonToken.NULL) {
      jsonReader.nextNull()
      return null
    }
    val dateTimeValue = jsonReader.nextString()
    return kotlin.runCatching {
      ZonedDateTime.parse(dateTimeValue, dateTimeFormat)
          .withZoneSameInstant(ZoneId.systemDefault())
          .toLocalDateTime()
    }
        .getOrNull()
        ?: LocalDateTime.parse(dateTimeValue, dateTimeFormat)
  }
}
