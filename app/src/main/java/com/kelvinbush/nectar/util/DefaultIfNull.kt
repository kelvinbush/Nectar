package com.kelvinbush.nectar.util

import com.squareup.moshi.*
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class DefaultIfNull

class DefaultIfNullFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>,
                        moshi: Moshi
    ): JsonAdapter<*>? {
        if (!Types.getRawType(type).isAnnotationPresent(
                DefaultIfNull::class.java)) {
            return null
        }

        val delegate = moshi.nextAdapter<Any>(this, type, annotations)

        return object : JsonAdapter<Any>() {
            override fun fromJson(reader: JsonReader): Any? {
                @Suppress("UNCHECKED_CAST")
                val blob = reader.readJsonValue() as Map<String, Any?>
                val noNulls = blob.filterValues { it != null }
                return delegate.fromJsonValue(noNulls)
            }

            override fun toJson(writer: JsonWriter, value: Any?) {
                return delegate.toJson(writer, value)
            }
        }
    }
}