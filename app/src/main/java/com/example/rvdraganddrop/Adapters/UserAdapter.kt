package com.example.rvdraganddrop.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rvdraganddrop.CLASS.User
import com.example.rvdraganddrop.Interface.ItemTouchHelperAdapter
import com.example.rvdraganddrop.R
import java.util.*

class UserAdapter(var context: Context, var list: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.Vh>(), ItemTouchHelperAdapter {

    inner class Vh(var itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(user: User) {

            itemView.findViewById<TextView>(R.id.textId).text = user.name
            user.imageID?.let {
                itemView.findViewById<ImageView>(R.id.imageId).setImageResource(it)
            }

            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.my_anim)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)

    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}