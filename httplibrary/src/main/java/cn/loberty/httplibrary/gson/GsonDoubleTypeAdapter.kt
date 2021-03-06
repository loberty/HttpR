package cn.loberty.httplibrary.gson

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class GsonDoubleTypeAdapter : JsonDeserializer<Any?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Any? {
        try {
            if (json.asString == "" || json.asString == "null") {
                //定义为int类型,如果后台返回""或者null,则返回0
                return java.lang.Double.valueOf(0.0)
            }
        } catch (ignore: Exception) {
        }
        try {
            return json.asDouble
        } catch (e: NumberFormatException) {
            Log.e(TAG, "number parser error $json")
        }
        return 0
    }

    companion object {
        private val TAG = GsonDoubleTypeAdapter::class.java.simpleName
    }
}