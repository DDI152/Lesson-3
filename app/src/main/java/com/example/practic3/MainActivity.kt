package com.example.practic3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.asLiveData
import com.example.practic3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDB.getDb(this)
        binding.tvlist.text = ""
        db.getDao().getAllItem().asLiveData().observe(this){
            it.forEach {

                val text = "id: ${it.id} Name: ${it.name} Price: ${it.price}\n"
                binding.tvlist.append(text)
            }
        }
        binding.button.setOnClickListener {
            val item = Item(null,binding.edName.text.toString(),
                binding.edPrice.text.toString()
            )
            Thread{
                db.getDao().insertItem(item)
            }.start()



        }
    }
}