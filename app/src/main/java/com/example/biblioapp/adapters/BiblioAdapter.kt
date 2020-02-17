package com.example.biblioapp.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.biblioapp.R
import com.example.biblioapp.models.AppModel
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.biblio_item_list.view.*

class BiblioAdapter(
    private val list: MutableList<AppModel>,
    private val canRemoveAppByClicking: Boolean = false
) :
    RecyclerView.Adapter<BiblioAdapter.BiblioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiblioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.biblio_item_list, parent, false)
        return BiblioViewHolder(view)
    }

    override fun onBindViewHolder(holder: BiblioViewHolder, position: Int) {
        holder.bind(list[position])

        val app: AppModel = list.get(position)

        holder.view.setOnClickListener(View.OnClickListener() {
            it.setOnClickListener {
                removeApp(app)
            }
        })
    }

    private fun removeApp(app: AppModel) {
        val currentPosition: Int = list.indexOf(app)
        list.removeAt(currentPosition)
        notifyItemRemoved(currentPosition)
    }

    override fun getItemCount(): Int =
        list.size


    inner class BiblioViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(app: AppModel) {
            view.biblio_app_title.text = app.nom
            view.biblio_app_genre.text = app.genre
            view.biblio_app_year.text = app.anneeSortie.toString()
            val uri: Uri = Uri.parse(app.imageUrl)
            val draweeView = view.biblio_app_image

            draweeView.setImageURI(uri)
        }
    }

}