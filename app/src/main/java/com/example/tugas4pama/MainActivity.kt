package com.example.tugas4pama

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Radyza Glagah Sudharma NIM 225150400111022

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val dataList = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = RecyclerViewAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val addButton: Button = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            showAddItemDialog()
        }

        addSampleData()
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val etDescription = dialogView.findViewById<EditText>(R.id.etDescription)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add New Item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                if (title.isNotEmpty() && description.isNotEmpty()) {
                    addNewData(title, description)
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun addNewData(name: String, description: String) {
        val newData = DataModel(name, description)
        dataList.add(newData)
        adapter.notifyItemInserted(dataList.size - 1)
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
    }

    private fun addSampleData() {
        dataList.add(DataModel("Item 1", "Description 1"))
        dataList.add(DataModel("Item 2", "Description 2"))
        dataList.add(DataModel("Item 3", "Description 3"))
        adapter.notifyDataSetChanged()
    }
}
