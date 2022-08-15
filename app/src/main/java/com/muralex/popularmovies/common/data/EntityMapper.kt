package com.muralex.popularmovies.common.data

interface EntityMapper<SRC, DST> {
    fun mapFromEntity(data: SRC): DST
}