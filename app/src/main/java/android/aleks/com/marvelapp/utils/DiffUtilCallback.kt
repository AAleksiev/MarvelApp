package android.aleks.com.marvelapp.utils

import android.aleks.com.marvelapp.models.UniqueId
import android.support.v7.util.DiffUtil

/**
 * Created by Aleksandar on 5.1.2018 г..
 */
class DiffUtilCallback<T : UniqueId>(private val current: List<T>, private val next: List<T>) : DiffUtil.Callback() {

    //region DiffUtil.Callback implementation
    override fun getOldListSize(): Int = current.size

    override fun getNewListSize(): Int = next.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val currentItem = current[oldItemPosition]

        val nextItem = next[newItemPosition]

        return currentItem.uniqueId.equals(nextItem.uniqueId, true)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val currentItem = current[oldItemPosition]

        val nextItem = next[newItemPosition]

        return currentItem == nextItem
    }
    //endregion
}