package ru.dima.aston_intensiv_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.dima.aston_intensiv_4.first_task.presentation.FragmentA
import ru.dima.aston_intensiv_4.second_task.presentation.contacts.UsersFragment


class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstTaskBtn = view.findViewById<Button>(R.id.first_task_btn)
        val secondTaskBtn = view.findViewById<Button>(R.id.second_task_btn)

        firstTaskBtn.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<FragmentA>(R.id.main_fragment_container)
                addToBackStack("start_fragment")
            }
        }
        secondTaskBtn.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<UsersFragment>(R.id.main_fragment_container)
                addToBackStack("start_fragment")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StartFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}