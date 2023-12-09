package ru.dima.aston_intensiv_4.first_task.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.dima.aston_intensiv_4.R
import ru.dima.aston_intensiv_4.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            forwardBtn.setOnClickListener {

                val cFragmentBundle = bundleOf(FragmentC.ARG_MESSAGE to "Hello Fragment C!")
                parentFragmentManager.commit {
                    replace<FragmentC>(R.id.main_fragment_container, args = cFragmentBundle)
                    setReorderingAllowed(true)
                    addToBackStack("fragment_b")
                }
            }

            backBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentB().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}