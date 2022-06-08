package com.example.rvdraganddrop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.rvdraganddrop.Adapters.UserAdapter
import com.example.rvdraganddrop.CLASS.User
import com.example.rvdraganddrop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var userList: ArrayList<User>
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userList = ArrayList()
        loadData()
        userAdapter = UserAdapter(this, userList)

        binding.rvId.adapter = userAdapter


        val itemTouchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {

                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

                return makeMovementFlags(dragFlags, swipeFlags)

            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                userAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)

                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                userAdapter.onItemDismiss(viewHolder.adapterPosition)

            }
        }

        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rvId)

    }

    private fun loadData() {


        for (i in 0 until 100) {
            userList.add(User("a", R.drawable.ic_launcher_foreground))
        }


    }
}