package pe.elcomercio.pagoefectivosdkkotlinsample.payment_method

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_payment_method.view.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.inflate
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.PaymentMethodEntity

/**
 * Created by carlosleonardocamilovargashuaman on 10/17/17.
 */
class PaymentMethodAdapter(private val paymentMethodList: List<PaymentMethodEntity>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<PaymentMethodAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(parent.inflate(R.layout.item_payment_method))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
            with(holder.itemView) {
                lblTitle.text = paymentMethodList[position].name
                setOnClickListener {
                    listener(paymentMethodList[position].id)
                }
            }

    override fun getItemCount(): Int = paymentMethodList.size

    class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}