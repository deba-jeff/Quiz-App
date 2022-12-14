package ukponahiunsijeffery.example.quizapp.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://opentdb.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface QuizApiService {
    @GET("api.php")
    fun getProperties(
        @Query("amount") amount: Int,
        @Query("category") category: String,
        @Query("difficulty") difficulty: String):
            Call<String>
}

object QuizApi{
    val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }

}



