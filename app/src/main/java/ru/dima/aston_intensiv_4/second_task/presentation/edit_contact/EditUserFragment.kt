package ru.dima.aston_intensiv_4.second_task.presentation.edit_contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ru.dima.aston_intensiv_4.databinding.FragmentEditUserBinding
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.ARG_FIRSTNAME
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.ARG_ID
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.ARG_LASTNAME
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.ARG_PHONE_NUMBER
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.ARG_PHOTO_SRC
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.REQUEST_KEY

class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = _binding!!

    private var userId: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var phoneNumber: String? = null
    private var photoSrc: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_ID)
            firstName = it.getString(ARG_FIRSTNAME)
            lastName = it.getString(ARG_LASTNAME)
            phoneNumber = it.getString(ARG_PHONE_NUMBER)
            photoSrc = it.getString(ARG_PHOTO_SRC)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            etFirstName.setText(firstName)
            etLastName.setText(lastName)
            etPhoneNumber.setText(phoneNumber)
            etPhotoSrc.setText(photoSrc)

            confirmButton.setOnClickListener {
                setFragmentResult(
                    REQUEST_KEY, bundleOf(
                        ARG_ID to userId,
                        ARG_FIRSTNAME to binding.etFirstName.text.toString(),
                        ARG_LASTNAME to binding.etLastName.text.toString(),
                        ARG_PHONE_NUMBER to binding.etPhoneNumber.text.toString(),
                        ARG_PHOTO_SRC to binding.etPhotoSrc.text.toString()
                    )
                )
                parentFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            userId: String,
            firstName: String,
            lastName: String,
            phoneNumber: String,
            photoSrc: String
        ) =
            EditUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, userId)
                    putString(ARG_FIRSTNAME, firstName)
                    putString(ARG_LASTNAME, lastName)
                    putString(ARG_PHONE_NUMBER, phoneNumber)
                    putString(ARG_PHOTO_SRC, photoSrc)
                }
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}