package network
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import models.Pokemon
import org.json.JSONObject

object PokemonApiService {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val client = AsyncHttpClient()

    fun getRandomPokemon(callback: (Pokemon?) -> Unit) {
        // Pokémon IDs range from 1 to 898
        val randomId = (1..898).random()
        val url = "${BASE_URL}pokemon/$randomId/"

        client.get(url, null, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                response?.let {
                    val pokemon = Gson().fromJson(it.toString(), Pokemon::class.java)
                    callback(pokemon)
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                callback(null)
            }
        })
    }


}