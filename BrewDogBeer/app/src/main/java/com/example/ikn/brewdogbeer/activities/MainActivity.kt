package com.example.ikn.brewdogbeer.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ikn.brewdogbeer.R
import kotlinx.android.synthetic.main.activity_main.cv_list as cvList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvList.setOnClickListener{
            val intent = Intent(this, BeerListActivity::class.java)
            startActivity(intent)
        }
    }
}
