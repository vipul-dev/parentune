package com.vipul.parentuneassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.vipul.parentuneassignment.databinding.ItemViewPagerContentBinding
import com.vipul.parentuneassignment.models.Data
import com.vipul.parentuneassignment.utils.UIUtils.setNetworkImage

class MyPagerAdapter(private val context: Context) : Adapter<MyPagerAdapter.PagerViewHolder>() {

    private var mData: List<Data> = emptyList()

    inner class PagerViewHolder(itemViewPagerContentBinding: ItemViewPagerContentBinding) :
        ViewHolder(itemViewPagerContentBinding.root) {
        val binding = itemViewPagerContentBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder(
        ItemViewPagerContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val binding = holder.binding
        val data = mData[position]
        try {
            setNetworkImage(context, data.banner!!, binding.ivBanner)


            binding.apply {
                tvClaims.text = data.claims
                tvPlanName.text = data.planName
                tvTags.text = data.taggedAs
                btnStart.text = data.cta


                bulletRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = BulletAdapter().apply {
                        setDataList(data.description!!.dataMonthly)
                    }
                    canScrollVertically(0)
                    setHasFixedSize(true)
                }

                val payPerDay = when (data.planName) {
                    "PLUS" -> "RS ${data.costPerDay}/DAY"
                    "PRO" -> "RS ${data.costPerDay}/DAY"
                    "BASIC" -> "PAY AS YOU USE"

                    else -> ""
                }

                tvPlanPrice.text = payPerDay

                Glide.with(context).load(data.buttonBackground)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(bgImage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDataList(): List<Data> {
        return mData
    }

    fun setDataList(mDataList: ArrayList<Data>) {
        this.mData = mDataList
        notifyDataSetChanged()
    }

}