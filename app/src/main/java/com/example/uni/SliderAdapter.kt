package com.example.uni

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter


class SliderAdapter(private val context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val images = intArrayOf(
        R.drawable.first_pic,
        R.drawable.second_pic,
        R.drawable.third_pic,
        R.drawable.fourth_pic
    )

    val headingListTitle = intArrayOf(
        R.string.first_slide_title,
        R.string.second_slide_title,
        R.string.third_slide_title,
        R.string.fourth_slide_title
    )

    val headingListDescription = intArrayOf(
        R.string.first_slide_desc,
        R.string.second_slide_desc,
        R.string.third_slide_desc,
        R.string.fourth_slide_desc
    )

    override fun getCount(): Int {
        return headingListDescription.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.slides_layout, container, false)
        val slider_image = itemView.findViewById<View>(R.id.slider_image) as ImageView
        val slider_title = itemView.findViewById<View>(R.id.slider_heading) as TextView
        val slider_desc = itemView.findViewById<View>(R.id.slider_desc) as TextView

        slider_image.setImageResource(images[position])
        slider_title.setText(headingListTitle[position])
        slider_desc.setText(headingListDescription[position])

        container.addView(itemView)

        return itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       // super.destroyItem(container, position, `object`)
        container.removeView(`object` as ConstraintLayout)
    }
}