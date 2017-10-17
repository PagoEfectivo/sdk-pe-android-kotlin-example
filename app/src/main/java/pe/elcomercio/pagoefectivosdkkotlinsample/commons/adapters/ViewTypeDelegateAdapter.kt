package pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, viewType: ViewType)
}