package ru.dima.aston_intensiv_4.second_task.presentation.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.dima.aston_intensiv_4.second_task.data.User

object UsersDiffUtil : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}