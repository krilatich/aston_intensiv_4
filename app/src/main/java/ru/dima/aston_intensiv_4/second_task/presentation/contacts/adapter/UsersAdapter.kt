package ru.dima.aston_intensiv_4.second_task.presentation.contacts.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ru.dima.aston_intensiv_4.databinding.ItemUserBinding
import ru.dima.aston_intensiv_4.second_task.data.User

@SuppressLint("SupportAnnotationUsage")
class UsersAdapter(
    @DrawableRes
    val loadingAnimation: Int? = null,
    @DrawableRes
    val brokenImage: Int? = null,
    private val onClickAction: (user: User) -> Unit,
) : ListAdapter<User, UsersAdapter.UserViewHolder>(UsersDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater)
        val viewHolder = UserViewHolder(binding)


        viewHolder.itemView.setOnClickListener {
            val item = getItem(viewHolder.adapterPosition)
            onClickAction(item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {

        fun bind(model: User) {
            with(binding) {
                "${model.firstName} ${model.lastName}".also { tvName.text = it }
                tvPhoneNumber.text = model.phoneNumber
                val imgUri = model.photoSrc.toUri().buildUpon().scheme("https").build()
                imgUser.load(imgUri) {
                    if (loadingAnimation != null) {
                        placeholder(loadingAnimation)
                    }
                    if (brokenImage != null) {
                        error(brokenImage)
                    }
                }
            }
        }
    }
}

