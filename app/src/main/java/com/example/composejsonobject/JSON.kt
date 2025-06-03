package com.example.composejsonobject

import android.content.Context
import org.json.JSONArray

data class Music(
    val id: Int,
    val name: String,
    val metadata: MetaData,
)

data class MetaData(
    val description: String,
    val tags: List<String>,
    val author: String,
)

fun parseMusic(context: Context): List<Music> {
    val jsonText = context.assets.open("data.json").reader().readText()
    val jsonObject = JSONArray(jsonText)

    return List(jsonObject.length()) { index ->
        val it = jsonObject.getJSONObject(index)
        val metaDataJson = it.getJSONObject("metadata")

        return@List Music(
            id = it.getInt("id"),
            name = it.getString("name"),
            metadata = MetaData(
                description = metaDataJson.getString("description"),
                tags = metaDataJson.getJSONArray("tags").let { tags ->
                    List(tags.length()) { tags.getString(it) }
                },
                author = metaDataJson.getString("author")
            )
        )
    }
}