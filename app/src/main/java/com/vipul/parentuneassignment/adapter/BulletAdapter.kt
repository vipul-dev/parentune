package com.vipul.parentuneassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vipul.parentuneassignment.R
import com.vipul.parentuneassignment.databinding.ItemBulletLayoutBinding
import com.vipul.parentuneassignment.models.DataMonthly

class BulletAdapter : Adapter<BulletAdapter.BulletItemViewHolder>() {

    private var itemList: List<DataMonthly> = emptyList()

    inner class BulletItemViewHolder(itemBulletLayoutBinding: ItemBulletLayoutBinding) :
        ViewHolder(itemBulletLayoutBinding.root) {
        val binding = itemBulletLayoutBinding


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BulletItemViewHolder(
        ItemBulletLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: BulletItemViewHolder, position: Int) {

        holder.binding.apply {
            bulletIcon.setImageResource(
                if (itemList[position].isLocked == "True") R.drawable.ic_lock else R.drawable.ic_right_check
            )
            bulletText.text = itemList[position].text
        }
    }

    fun setDataList(mDataList:ArrayList<DataMonthly>){
        this.itemList = mDataList
        notifyDataSetChanged()
    }
}