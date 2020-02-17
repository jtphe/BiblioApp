package com.example.biblioapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.biblioapp.adapters.BiblioAdapter
import com.example.biblioapp.models.AppModel
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fresco.initialize(this)
        val listOfApps = listOf(
            AppModel(
                "Fortnite",
                "FPS",
                2018,
                "https://lh3.googleusercontent.com/Yc3-ehYHXE54cbv3uRk7sXNgWFhvvntl7gCEcLgzkaQJ_sPRxmQEiYfUurVgGtPm5BxN"
            ),
            AppModel(
                "Afk Arena",
                "Idler",
                2018,
                "https://www.thewindose.fr/wp-content/uploads/2019/10/afk-arena.png"
            ),
            AppModel(
                "PUBG",
                "FPS",
                2016,
                "https://www.apklinker.com/wp-content/plugins/APKLinker/resize_img.php?u=https://www.apklinker.com/wp-content/uploads/2018/03/PUBGMobile.png&q=9&s=240"
            ),
            AppModel(
                "Candy Crush",
                "Puzzle",
                2000,
                "https://vignette.wikia.nocookie.net/candy-crush-saga/images/c/ce/Candy_Crush_Saga_1.168_icon.png/revision/latest/scale-to-width-down/340?cb=20200123134835"
            ),
            AppModel(
                "Idle Heroes",
                "Idler",
                2015,
                "https://is3-ssl.mzstatic.com/image/thumb/Purple123/v4/38/3b/b7/383bb7ee-1d63-de6d-efea-1570d153af92/AppIcon-0-0-1x_U007emarketing-0-0-0-10-85-220.png/400x400.png"
            ),
            AppModel(
                "Call of Duty",
                "FPS",
                2020,
                "https://d2jcw5q7j4vmo4.cloudfront.net/6lEEhm2WZojAbZ1uqRJb-KEmT24xydDd5I0QjABtlNOeDr9NrNxztXe67AArHUFuqSI=w512"
            )
        )
        var newList: MutableList<AppModel> = listOfApps.toMutableList();

        val intent: Intent? = getIntent()
        val searchName: String? = intent?.getStringExtra("App")

        if (searchName !== null) {
            newList = listOfApps.filter { it ->
                it.nom.trim().toLowerCase() == searchName.toString().trim().toLowerCase()
            }.toMutableList()
        }
        recycler_view_biblio_app.apply {
            context?.also {
                layoutManager = GridLayoutManager(context, 1)
                adapter = BiblioAdapter(newList)
            }
        }

        fun selectorGenre(g: AppModel): String = g.genre
        fun selectorYear(g: AppModel): String = g.anneeSortie.toString()

        biblio_app_btn_filterGenre.setOnClickListener {
            val sortedByGenre = listOfApps.sortedBy({ selectorGenre(it) })
            Log.d("Debug 1 =>", sortedByGenre.toString());
        }

        biblio_app_btn_filterYear.setOnClickListener {
            val sortedByYear = listOfApps.sortedBy({ selectorYear(it) })
            Log.d("Debug 2 =>", sortedByYear.toString());
        }

        biblio_app_btn_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


    }


}
