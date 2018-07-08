package me.jagdeep.database.mapper

interface CacheMapper<E, D> {

    fun mapToCache(type: E): D

    fun mapFromCache(type: D): E

}
