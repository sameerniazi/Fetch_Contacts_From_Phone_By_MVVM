package com.sameer.call.sameerproject.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sameer.call.sameerproject.ViewModel.ContactViewModel
import com.sameer.call.sameerproject.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    private var contactViewModel: ContactViewModel? = null

    private var binding: FragmentContactListBinding? = null

    var adapter: ContactAdapter? = null

    companion object {

    fun newInstance(): ContactListFragment? {
        return ContactListFragment()
    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* if (arguments != null) {
        }*/
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        contactViewModel = ContactViewModel(requireContext().applicationContext)
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        initRecyclerView()
        return view
    }


    private fun initRecyclerView() {
        binding!!.contactRecyclerView.setLayoutManager(LinearLayoutManager(binding!!.contactRecyclerView.context))
        binding!!.contactRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding!!.contactRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ContactAdapter(binding!!.contactRecyclerView.context)
        adapter!!.setContacts(contactViewModel!!.getContacts())
        binding!!.contactRecyclerView.adapter = adapter
        adapter!!.setOnItemClickListener { view, obj, position ->
            Toast.makeText(
                context,
                """
                Contact Selected
                ${obj.getName()}${obj.getPhoneNumber()}}
                """.trimIndent(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onStop() {
        super.onStop()
        requireActivity().fragmentManager.popBackStack();
    }

    override fun onResume() {
        super.onResume()
        requireActivity().fragmentManager.popBackStack();
    }
}