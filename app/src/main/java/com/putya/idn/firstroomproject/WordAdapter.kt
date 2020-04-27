package com.putya.idn.firstroomproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.putya.idn.firstroomproject.databinding.ItemRowBinding

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private lateinit var binding: ItemRowBinding

    private var word = emptyList<Word>()

    internal fun setWord(words: List<Word>) {
        this.word = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            with(binding) {
                tvWord.text = item.word
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.WordViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent,
            false
        )
        return WordViewHolder(binding)
    }

    override fun getItemCount() = word.size

    override fun onBindViewHolder(holder: WordAdapter.WordViewHolder, position: Int) =
        holder.bind(word[position])
}