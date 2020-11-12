package com.anushka.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //7)instanciamos la consulta a la API
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        getRequestWithQueryParameters()
        //getRequestWithPathParameters()
        //uploadAlbum()

    }


    //7)esto muestra lo que a simple vista se ve en la interfaz
    private fun getRequestWithQueryParameters() {
        //7) obtenemos la información de la API
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        //7) Acomoda la respuesta del api
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    //7) imprime la información acomodada en la interfaz
                    text_view.append(result)
                }
            }
        })
    }


    //8) obtenemos todos los albumes del usuario 3
    private fun getRequestWithPathParameters() {
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)

        }

        //9) obtenemos el album 3 y pasamos los parametros
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

    //12) creamos la función que postea en la api
    private fun uploadAlbum() {
        //12) aquí se crea la vriable con los datos a postear
        val album = AlbumsItem(0, "My title", 3)
        //12)creo la variable que guarda la información previa al posteo
        val postResponse: LiveData<Response<AlbumsItem>> = liveData {
            //12) mando la información a la función de la interfaz
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        //12)obtengo la información que devuelve la api después del post
        postResponse.observe(this, Observer {
            //12) hago la variable que contiene toda la información que devolvió el API
            val receivedAlbumsItem = it.body()
            //12) voy ordenando la información en una variable
            val result = " " + "Album Title : ${receivedAlbumsItem?.title}" + "\n" +
                    " " + "Album id : ${receivedAlbumsItem?.id}" + "\n" +
                    " " + "User id : ${receivedAlbumsItem?.userId}" + "\n\n\n"
            //paso la información organizada dentro de la variable a la interfaz visible
            text_view.text = result
        })

    }

}
