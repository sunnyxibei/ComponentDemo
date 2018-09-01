package cn.com.timeriver.common.data.bean

import com.google.gson.annotations.SerializedName

data class Girl(@SerializedName("_id") val id: String,
                val createdAt: String,
                val desc: String,
                val publishedAt: String,
                val source: String,
                val type: String,
                val url: String,
                val used: Boolean,
                val who: String)

data class GirlsWrapper(val error: Boolean, var results: ArrayList<Girl>)