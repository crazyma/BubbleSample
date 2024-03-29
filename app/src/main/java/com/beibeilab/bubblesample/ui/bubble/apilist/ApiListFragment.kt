package com.beibeilab.bubblesample.ui.bubble.apilist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beibeilab.bubblesample.R
import com.beibeilab.bubblesample.ui.bubble.ApiEndpointItem
import com.beibeilab.bubblesample.ui.bubble.BubbleViewModel
import kotlinx.android.synthetic.main.fragment_api_list.*

class ApiListFragment :
    Fragment(),
    ApiListAdapter.Interaction {

    interface Interaction {
        fun onApiEndpointClicked()
    }

    companion object {
        fun newInstance() = ApiListFragment()
    }

    private lateinit var interaction: Interaction
    private lateinit var viewModel: BubbleViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        interaction = when {
            context is Interaction -> context
            parentFragment is Interaction -> parentFragment as Interaction
            else -> throw RuntimeException("parent need to implement ApiListFragment.Interaction")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(BubbleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()

        viewModel.loadApiEndpoint()
    }

    override fun apiEndpointClicked() {
        interaction.onApiEndpointClicked()
    }

    private fun setupViewModel() {
        viewModel.apply {
            apiEndpointItems.observe(viewLifecycleOwner, Observer {
                populateList(it)
            })
        }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = ApiListAdapter(this@ApiListFragment)
        }
    }

    private fun populateList(items: List<ApiEndpointItem>) {
        (recyclerView.adapter as ApiListAdapter).items = items
    }

}