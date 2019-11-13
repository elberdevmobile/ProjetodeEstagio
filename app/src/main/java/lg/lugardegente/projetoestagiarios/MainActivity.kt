package lg.lugardegente.projetoestagiarios

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elber.tilixelber.Adapter_Artigos
import com.example.elber.tilixelber.InfosLista
import com.example.elber.tilixelber.json
import com.f4pl0.farla.FarlaGetRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder

import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val GET = "https://bit.ly/2W6DCuG"
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requisicao)

        exemploGET()


    }

    fun exemploGET() {

        FarlaGetRequest(this)              // For DELETE Request change to FarlaDeleteRequest
            .setURL(GET)
            .setListener(object : FarlaGetRequest.onGetRequestListener {
                override fun onSuccess(response: String) {

                    val gsonBuilder = GsonBuilder()
                    val gson = gsonBuilder.create()


                    var infos: InfosLista =  gson.fromJson<InfosLista>(response, InfosLista::class.java)
                    var json: json = json()

                    json.dados = response


                    viewManager = LinearLayoutManager(applicationContext)
                    viewAdapter = Adapter_Artigos(infos.informacoes)
                    recyclerView = findViewById<RecyclerView>(R.id.rc_artigos).apply {

                        setHasFixedSize(true)

                        layoutManager = viewManager
                        adapter = viewAdapter


                    }


                }

                override fun onFailure(error: Int) {
                    //Handle the failure
                }
            }).execute()


    }

    private fun tratarRetorno(jsonObj: JSONObject): MutableList<Noticia>{
        var dados = jsonObj["data"]
        var tamanho = (dados as JSONArray).length()
        var noticias = Noticia()
        var ListaNoticias:MutableList<Noticia> = ArrayList()



        for (i in 0 until tamanho) {
            var jsnobjectnovo = JSONObject(dados.get(i).toString())
            noticias.id = i
            noticias.titulo = jsnobjectnovo.getString("title")
            noticias.website =jsnobjectnovo.getString("website")
            noticias.autor =jsnobjectnovo.getString("authors")
            noticias.data =jsnobjectnovo.getString("date")
            noticias.conteudo =jsnobjectnovo.getString("content")
            noticias.imagem =jsnobjectnovo.getString("image_url")
            ListaNoticias.add(noticias)
            println(ListaNoticias)

        }
        return ListaNoticias
    }


}
