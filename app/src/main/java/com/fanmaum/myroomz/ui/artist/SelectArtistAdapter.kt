package com.fanmaum.myroomz.ui.artist

import android.annotation.SuppressLint
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.fanmaum.myroomz.R
import com.fanmaum.myroomz.data.Artist
import kotlinx.android.synthetic.main.item_select_artist_default.view.*
import kotlinx.android.synthetic.main.item_select_artist_header.view.*


class SelectArtistAdapter(private val adapterListener: SelectArtistAdapterListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList: ArrayList<Artist> = arrayListOf()

    private val TYPE_HEADER = 0
    private val TYPE_DEFAULT = 1

    private fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

    interface SelectArtistAdapterListener{
        fun selectArtist(item: Artist)
        fun searchArtist(key: String?, token: IBinder?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_HEADER -> SelectArtistHeaderViewHolder(parent.inflate(R.layout.item_select_artist_header))
            else -> SelectArtistDefaultViewHolder(parent.inflate(R.layout.item_select_artist_default))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> TYPE_HEADER
            else -> TYPE_DEFAULT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SelectArtistDefaultViewHolder -> {
                holder.itemView.apply {
                    val item = itemList[position.minus(1)]
                    artist_name.text = item.name
                    if (item.group == null) artist_group.visibility = View.GONE else {
                        artist_group.text = item.group
                        artist_group.visibility = View.VISIBLE
                    }
                    if (item.isSelect) {
                        setBackgroundResource(R.drawable.rectangle_secondary_yellow_radius_20dp)
                        artist_heart.setImageResource(R.drawable.ic_heart_20_blue_1)
                    } else {
                        setBackgroundResource(R.drawable.rectangle_pale_grey02_radius_20dp)
                        artist_heart.setImageResource(R.drawable.ic_heart_20_blue_1_o_2)
                    }

                    setOnClickListener {
                        adapterListener.selectArtist(item)
                    }
                }
            }
            is SelectArtistHeaderViewHolder -> {
                holder.itemView.apply {
                    search_artist.setOnEditorActionListener { p0, p1, _ ->
                        when (p1) {
                            EditorInfo.IME_ACTION_SEARCH -> {
                                p0.clearFocus()
                                artist_title.text = "검색 결과"
                                adapterListener.searchArtist(p0.text.toString(), p0.windowToken)
                                true
                            }
                            else -> false
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size.plus(1)

    fun addItem(list: ArrayList<Artist>){
        itemList.addAll(list)
        notifyItemRangeInserted(1, list.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItemList(){
        itemList.clear()
        notifyDataSetChanged()
    }

    fun updateItem(item: Artist){
        for (i in itemList) {
            if (i.name == item.name){
                itemList[itemList.indexOf(i)] = item
                notifyItemChanged(itemList.indexOf(i).plus(1))
                break
            }
        }
    }

    class SelectArtistHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class SelectArtistDefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
