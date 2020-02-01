package com.bach.dv.basemvp.util

import com.google.gson.*
import org.joda.time.DateTimeZone
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class GsonHelper {
    companion object {
        var dateFormatMYSQL = "yyyy-MM-dd HH:mm:ss"
        var dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        var dateFormat2 = "yyyy-MM-dd'T'HH:mm:ss"
        var dateFormatTimeZone = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"
        var dateFormatTimeZone2 = "yyyy-MM-dd'T'HH:mm:ssZ"
        fun getInstance(): Gson {
            val gsonb = GsonBuilder()
            gsonb.registerTypeAdapter(Date::class.java, WcfCalendarDeserializer())
            return gsonb.create()
        }

        class WcfCalendarDeserializer : JsonDeserializer<Date>, JsonSerializer<Date> {

            @Throws(JsonParseException::class)
            override fun deserialize(
                json: JsonElement, typeOfT: Type,
                context: JsonDeserializationContext
            ): Date? {
                try {
                    val asString = json.asString
                    if (asString != null && asString.length > 0) {
                        val dateTime = ISODateTimeFormat.dateTimeParser()
                            .withZone(DateTimeZone.getDefault())
                            .parseDateTime(asString)
                        return dateTime.toDate()
                    } else {
                        return null
                    }
                } catch (e: Exception) {
                    try {
                        return Common.convertStringToDate(json.asString, "yyyy-MM-dd HH:mm:ss")
                    } catch (e2: Exception) {
                        e2.printStackTrace()
                    }

                    return null
                }

            }

            override fun serialize(
                arg0: Date, arg1: Type,
                arg2: JsonSerializationContext
            ): JsonElement {
                return JsonPrimitive(convertDateToStringToSync(arg0)!!)
            }

            companion object {


                @JvmOverloads
                fun convertDateToStringToSync(date: Date?, isTimeZone: Boolean = false): String? {
                    if (date == null) {
                        return null
                    }
                    val dateFormat = SimpleDateFormat(
                        dateFormat
                    )
                    var str = dateFormat.format(date)
                    try {
                        if (isTimeZone) {
                            val timeZoneString = TimeZone.getDefault().getDisplayName(
                                false, TimeZone.SHORT
                            )
                            str =
                                str + "+" + timeZoneString.split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    return str
                }
            }
        }
    }


}