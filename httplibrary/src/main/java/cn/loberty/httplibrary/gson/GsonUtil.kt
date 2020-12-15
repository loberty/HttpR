package cn.loberty.httplibrary.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

object GsonUtil {

    private var gson: Gson? = null

    fun gson() : Gson{
        return gson ?: synchronized(this){
            GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Int::class.java, GsonIntTypeAdapter())
                .registerTypeAdapter(Int::class.javaPrimitiveType, GsonIntTypeAdapter())
                .registerTypeAdapter(Double::class.java, GsonDoubleTypeAdapter())
                .registerTypeAdapter(Double::class.javaPrimitiveType, GsonDoubleTypeAdapter())
                .registerTypeAdapter(Long::class.java, GsonLongTypeAdapter())
                .registerTypeAdapter(Long::class.javaPrimitiveType, GsonLongTypeAdapter())
                .create()
        }
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    fun toJson(`object`: Any?): String? {
        var gsonString: String? = null
        gsonString = gson!!.toJson(`object`)
        return gsonString
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    fun toJson(`object`: Any?, type: Type?): String? {
        var gsonString: String? = null
        gsonString = gson!!.toJson(`object`, type)
        return gsonString
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    fun <T> fromJson(gsonString: String?, cls: Class<T>?): T? {
        var t: T? = null
        t = gson!!.fromJson(gsonString, cls)
        return t
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param type
     * @return
     */
    fun <T> fromJson(gsonString: String?, type: Type?): T? {
        var t: T? = null
        t = gson!!.fromJson(gsonString, type)
        return t
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    fun <T> gsonToList(gsonString: String?, cls: Class<T>): List<T> {
        var listGson = gson
        var type = object : TypeToken<List<T>?>() {}.type
        if (cls == Long::class.java) {
            listGson =
                GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create()
            type = object : TypeToken<List<Long?>?>() {}.type
        }
        var list: List<T>? = null
        if (listGson != null) {
            list = listGson.fromJson(gsonString, type)
        }
        return list ?: ArrayList()
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    fun <T> gsonToListMap(gsonString: String?): List<Map<String, T>>? {
        var list: List<Map<String, T>>? = null
        if (gson != null) {
            list = gson!!.fromJson(
                gsonString,
                object : TypeToken<List<Map<String?, T>?>?>() {}.type
            )
        }
        return list
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    fun <T> gsonToMap(gsonString: String?): Map<String, T>? {
        var map: Map<String, T>? = null
        if (gson != null) {
            map = gson!!.fromJson(gsonString, object : TypeToken<Map<String?, T>?>() {}.type)
        }
        return map
    }
}