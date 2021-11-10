package cl.felipecastillof.qrreader.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelperServicios {

    companion object {

        fun getRetrofitServicios(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl( Const.URL_SERVER )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}