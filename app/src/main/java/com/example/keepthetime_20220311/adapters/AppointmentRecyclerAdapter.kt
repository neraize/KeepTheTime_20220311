package com.example.keepthetime_20220311.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_20220311.R
import com.example.keepthetime_20220311.ViewMapActivity
import com.example.keepthetime_20220311.datas.AppointmentData
import com.example.keepthetime_20220311.datas.UserData
import java.text.SimpleDateFormat

class AppointmentRecyclerAdapter(
    val mContext:Context,
    val mList: List<AppointmentData>
): RecyclerView.Adapter<AppointmentRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDateTime = view.findViewById<TextView>(R.id.txtDateTime)
        val txtPlaceName = view.findViewById<TextView>(R.id.txtPlaceName)
        val ImgViewMap = view.findViewById<ImageView>(R.id.ImgViewMap)

        fun bind(data:AppointmentData){

            txtTitle.text = data.title
            txtPlaceName.text =data.place

            // 서버가 주는 dateTime (Date형태로 내려옴)

            // 출력하고 싶은 dateTime (String - 22년 3월 15일 오후 2시 30분 양식)  - format활용
            val sdf =SimpleDateFormat("yy년 M월 d일 a h시 m분")
            txtDateTime.text = sdf.format(data.datetime)

            ImgViewMap.setOnClickListener {

                val myIntent = Intent(mContext, ViewMapActivity::class.java)
                myIntent.putExtra("appointment", data)
                mContext.startActivity(myIntent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data =mList[position]
        holder.bind(data)
    }

    override fun getItemCount()= mList.size
}