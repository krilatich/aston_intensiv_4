package ru.dima.aston_intensiv_4.second_task.presentation.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResultListener
import ru.dima.aston_intensiv_4.R
import ru.dima.aston_intensiv_4.databinding.FragmentUsersBinding
import ru.dima.aston_intensiv_4.second_task.data.User
import ru.dima.aston_intensiv_4.second_task.data.UsersDB
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.adapter.UsersAdapter
import ru.dima.aston_intensiv_4.second_task.presentation.edit_contact.EditUserFragment


const val REQUEST_KEY = "edited_user"
const val ARG_ID = "user_id"
const val ARG_FIRSTNAME = "first_name"
const val ARG_LASTNAME = "last_name"
const val ARG_PHONE_NUMBER = "photo_number"
const val ARG_PHOTO_SRC = "photo_src"

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private var usersAdapter: UsersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            val editedContact = User(
                id = bundle.getString(ARG_ID) ?: "NULL",
                firstName = bundle.getString(ARG_FIRSTNAME) ?: "NULL",
                lastName = bundle.getString(ARG_LASTNAME) ?: "NULL",
                phoneNumber = bundle.getString(ARG_PHONE_NUMBER) ?: "NULL",
                photoSrc = bundle.getString(ARG_PHOTO_SRC) ?: "NULL"
            )

            UsersDB.users = UsersDB.users.map {
                if (it.id == editedContact.id) editedContact else it
            }.toMutableList()
            usersAdapter?.submitList(UsersDB.users.toList())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        binding.usersRecycler.adapter =
            UsersAdapter(
                loadingAnimation = R.drawable.loading_animation,
                brokenImage = R.drawable.ic_broken_image
            ) {
                val editContactBundle =
                    bundleOf(
                        ARG_ID to it.id,
                        ARG_FIRSTNAME to it.firstName,
                        ARG_LASTNAME to it.lastName,
                        ARG_PHONE_NUMBER to it.phoneNumber,
                        ARG_PHOTO_SRC to it.photoSrc
                    )
                parentFragmentManager.commit {
                    replace<EditUserFragment>(
                        R.id.main_fragment_container,
                        args = editContactBundle
                    )
                    setReorderingAllowed(true)
                    addToBackStack("fragment_contacts")
                }
            }
        usersAdapter = binding.usersRecycler.adapter as UsersAdapter
        usersAdapter?.submitList(UsersDB.users.toList())
    }
}