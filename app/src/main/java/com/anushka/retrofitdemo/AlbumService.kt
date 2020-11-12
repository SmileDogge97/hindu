package com.anushka.retrofitdemo

import retrofit2.Response
import retrofit2.http.*


interface AlbumService {
    //5) Cree las funciones para obtener información, los campos que se mandan o jalan son iguales a los del dataclass
    //AlbumItems
    //el "/albums" es parte de la URL de donde se saca la información
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    //8) aquí hago la misma consulta de información a la api pero ahora le especifico de que usuario quiero
    //información
    @GET("/albums")
    //8) después del query puse el nombre del campo al que le voy a mandar el dato
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    //9) paso los parametros
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>

    //12 la funcion que postea
    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumsItem): Response<AlbumsItem>


}