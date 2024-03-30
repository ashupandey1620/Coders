package com.ashutosh.bingo.domain.convertors

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateConverter {

	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	@JvmStatic
	fun fromString(value: String?): LocalDate? {
		return value?.let { LocalDate.parse(it) }
	}

	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	@JvmStatic
	fun toString(value: LocalDate?): String? {
		val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
		return value?.format(formatter)
	}
}
