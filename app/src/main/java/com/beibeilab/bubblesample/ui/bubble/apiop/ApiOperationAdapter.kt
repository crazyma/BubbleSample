package com.beibeilab.bubblesample.ui.bubble.apiop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beibeilab.bubblesample.R
import com.beibeilab.bubblesample.ui.bubble.ApiEndpointItem
import com.beibeilab.bubblesample.ui.bubble.ApiOperationItem
import kotlinx.android.synthetic.main.list_item_api_endpoint.view.*

class ApiOperationAdapter(
    private val interaction: Interaction
): RecyclerView.Adapter<ApiOperationAdapter.ViewHolder>() {

    //  TODO by Batu: change 'RecyclerView.ViewHolder' to 'DcardRecyclerViewHolder'
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {

            fun create(parent: ViewGroup) =
                ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_api_endpoint, parent, false)
                )
        }

        private val apiEndpointTextView = itemView.apiEndpointTextView!!

        fun bind(item: ApiOperationItem) {
            apiEndpointTextView.text = item.apiOperation
        }
    }

    interface Interaction{
        fun apiEndpointClicked()
    }

    var items: List<ApiOperationItem>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =ViewHolder.create(parent)

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items!![position])
        holder.itemView.setOnClickListener {
            interaction.apiEndpointClicked()
        }
    }
}