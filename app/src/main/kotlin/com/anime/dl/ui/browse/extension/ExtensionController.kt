package com.anime.dl.ui.browse.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.dl.ui.action.DisplayExtensions
import com.anime.dl.ui.base.controller.BaseController
import com.anime.dl.databinding.ExtensionControllerBinding
import com.anime.dl.ui.main.mainStore
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class ExtensionController : BaseController<ExtensionControllerBinding>() {

    private var adapter: FlexibleAdapter<IFlexible<*>>? = null

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        binding = ExtensionControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        binding.swipeRefresh.isRefreshing = true
        binding.swipeRefresh.refreshes()
            .onEach { mainStore.dispatch(DisplayActions) 
            .launchIn(scope)

        adapter = ExtensionAdapter(this)

        binding.recycler.layoutManager = LinearLayoutManager(view.context)
        binding.recycler.adapter = adapter
        adapter?.fastScroller = binding.fastScroller
    }
}
