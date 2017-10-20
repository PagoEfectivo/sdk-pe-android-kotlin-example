package pe.elcomercio.pagoefectivosdkkotlinsample.list_cip

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_result_search.view.*
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.inflate

/**
 * Created by tohure on 20/10/17.
 */
class ResultSearchAdapter(private val cipSearches: List<CipSearch>) : RecyclerView.Adapter<ResultSearchAdapter.ResultSearchItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultSearchItemViewHolder =
            ResultSearchItemViewHolder(parent.inflate(R.layout.item_result_search))


    override fun onBindViewHolder(holder: ResultSearchItemViewHolder, position: Int) = with(holder.itemView) {
        lblCipSearch.text = cipSearches[position].cip.toString()
        lblTransactionCode.text = cipSearches[position].transactionCode
        lblAmount.text = cipSearches[position].amount.toString()
        lblCurrency.text = cipSearches[position].currency
        lblStatus.text = cipSearches[position].status.toString()
        lblStatusName.text = cipSearches[position].statusName
        lblDateCreation.text = cipSearches[position].dateCreation
        lblDateExpire.text = cipSearches[position].dateExpiry
        lblDatePayment.text = cipSearches[position].datePayment
        lblDateRemoval.text = cipSearches[position].dateRemoval
    }

    override fun getItemCount(): Int = cipSearches.size

    class ResultSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}