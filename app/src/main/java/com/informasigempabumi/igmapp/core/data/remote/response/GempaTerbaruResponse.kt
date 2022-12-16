package com.informasigempabumi.igmapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GempaTerbaruResponse(
	@field:SerializedName("Infogempa")
	val infogempa: TerbaruInfogempa? = null
)
