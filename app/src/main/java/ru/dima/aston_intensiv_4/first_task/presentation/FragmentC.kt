package ru.dima.aston_intensiv_4.first_task.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.dima.aston_intensiv_4.R
import ru.dima.aston_intensiv_4.databinding.FragmentCBinding


class FragmentC : Fragment() {

    private var message: String? = null
    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(ARG_MESSAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            val recMessage = "I received this:\n$message"
            receivedMessage.text = recMessage

            forwardBtn.setOnClickListener {
                parentFragmentManager.commit {
                    replace<FragmentD>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack("fragment_c")
                }
            }

            backBtn.setOnClickListener {
                parentFragmentManager.popBackStack("fragment_a",-1)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(message: String) =
            FragmentC().apply {
                arguments = Bundle().apply {
                    putString(ARG_MESSAGE, message  )
                }
            }

        const val ARG_MESSAGE = "message"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}