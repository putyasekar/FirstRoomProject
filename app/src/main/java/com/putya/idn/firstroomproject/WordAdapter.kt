package com.putya.idn.firstroomproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var word = emptyList<Word>()

    internal fun setWord(words: List<Word>) {
        this.word = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordView: TextView = view.findViewById(R.id.tv_word)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_row, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = word.size

    override fun onBindViewHolder(holder: WordAdapter.WordViewHolder, position: Int) {
        val currentPosition = word[position]
        holder.wordView.text = currentPosition.word
    }
}