package com.example.pizzamax

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamax.data.source.ListDatabaseCallback.Companion.id
import com.example.pizzamax.data.source.ListDatabaseCallback.Companion.imgUrl
import com.example.pizzamax.data.source.ListDatabaseCallback.Companion.price
import com.example.pizzamax.data.source.ListDatabaseCallback.Companion.size
import com.example.pizzamax.databinding.ActivityMainBinding
import com.example.pizzamax.views.util.getJsonDataFromAsset
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(binding.root)

        binding.linearViewCart.setOnClickListener {
            /*  val intent = Intent(this, CheckoutActivity::class.java)
              startActivity(intent)*/
        }
    }

}