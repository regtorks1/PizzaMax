package com.example.pizzamax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.MenuItemCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.top_app_bar,menu)

        val item : MenuItem = menu!!.findItem(R.id.spinner)
        val spinner : Spinner = MenuItemCompat.getActionView(item) as Spinner

        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
                                                   R.array.spinner_list_item_array, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        return true
    }
}