package com.informasigempabumi.igmapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GempaDiRasakanResponse(

	@field:SerializedName("Infogempa")
	val infogempa: Infogempa? = null
)