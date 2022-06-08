package com.example.rvdraganddrop.Interface

interface ItemTouchHelperAdapter{

    fun onItemMove(fromPosition:Int,toPosition:Int)

    fun onItemDismiss(position: Int)

}