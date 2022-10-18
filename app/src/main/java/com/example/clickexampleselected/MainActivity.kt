package com.example.clickexampleselected

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val assentos = mutableMapOf(
        0 to false,
        1 to false,
        2 to false,
        3 to false,
        4 to false,
        5 to false,
        6 to false,
        7 to false,
        8 to false,
        9 to false,
        10 to false,
        11 to false,
        12 to false,
        13 to false,
        14 to false,
        15 to false,
        16 to false,
        17 to false,
        18 to false,
        19 to false,
        20 to false
    )

    var selecionado: Int? = null

    private lateinit var adapter: MainAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_main)
        rv.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(assentos) {
            if (selecionado == null && assentos[it] == false) {
                assentos[it] = true
                selecionado = it
                Toast.makeText(this, "$selecionado", Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
            } else if (selecionado != null && selecionado != it) {
                assentos[selecionado!!] = false
                selecionado = it
                assentos[it] = true
                adapter.notifyDataSetChanged()
            }
        }

        rv.adapter = adapter

    }

    class MainAdapter(
        private val assentos: Map<Int, Boolean>,
        private val onItemClickListener: (Int) -> Unit
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_button_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount() = assentos.count()

        inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(id: Int) {
                val button = itemView.findViewById<Button>(R.id.item_btn)

                if (assentos[id] == true) {
                    button.text = (id).toString()
                }

                button.setOnClickListener {
                    onItemClickListener.invoke(id)
                }


            }

        }

    }
}