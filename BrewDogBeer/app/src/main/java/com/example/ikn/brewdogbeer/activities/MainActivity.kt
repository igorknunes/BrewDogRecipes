package com.example.ikn.brewdogbeer.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ikn.brewdogbeer.R
import com.example.ikn.brewdogbeer.Util.FavoritesManager.FAVORITE_LIST_KEY
import kotlinx.android.synthetic.main.activity_main.cv_list as cvList
import kotlinx.android.synthetic.main.activity_main.cv_favorites as cvFav

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvList.setOnClickListener {
            val intent = Intent(this, BeerListActivity::class.java)
            startActivity(intent)
        }
        cvFav.setOnClickListener {
            var intent = Intent(this, BeerListActivity::class.java)
            intent.putExtra(FAVORITE_LIST_KEY, true)
            startActivity(intent)
        }
    }
}
