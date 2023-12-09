package ru.dima.aston_intensiv_4.first_task.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dima.aston_intensiv_4.databinding.FragmentDBinding

class FragmentD : Fragment() {

    private var _binding: FragmentDBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            backBtn.setOnClickListener {
                parentFragmentManager.popBackStack("fragment_b", -1)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentD().apply {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}