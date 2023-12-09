package ru.dima.aston_intensiv_4.first_task.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.dima.aston_intensiv_4.R
import ru.dima.aston_intensiv_4.databinding.FragmentABinding


class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forwardBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace<FragmentB>(R.id.main_fragment_container)
                setReorderingAllowed(true)
                addToBackStack("fragment_a")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentA().apply {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}