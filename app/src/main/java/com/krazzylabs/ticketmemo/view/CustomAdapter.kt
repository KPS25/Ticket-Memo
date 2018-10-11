package com.krazzylabs.ticketmemo.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krazzylabs.ticketmemo.MainActivity
import com.krazzylabs.ticketmemo.R
import com.krazzylabs.ticketmemo.model.Ticket
import kotlinx.android.synthetic.main.list_layout.view.*

class CustomAdapter( val tickets: MutableList<Ticket>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(tickets[position], position)
        holder.setIsRecyclable(false)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return tickets.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mContext: Context = itemView.context

        fun bindItems(ticket: Ticket, position : Int) {
            itemView.et_ticketValue.text = ticket.ticketValue.toString()
            itemView.tv_ticketSell.text = ticket.ticketSell.toString()
            itemView.tv_ticketAmount.text = ticket.ticketAmount.toString()

            itemView.et_ticketStartNo.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(!itemView.et_ticketStartNo.text.toString().equals("")) {
                        ticket.ticketStartNo = itemView.et_ticketStartNo.text.toString().toInt()
                    }else
                        ticket.ticketStartNo = 0

                    if(!itemView.et_ticketEndNo.text.toString().equals("")) {
                        ticket.ticketEndNo = itemView.et_ticketEndNo.text.toString().toInt()
                    }else
                        ticket.ticketEndNo = 0


                    ticket.compute()
                    itemView.tv_ticketSell.text = ticket.ticketSell.toString()
                    itemView.tv_ticketAmount.text = ticket.ticketAmount.toString()

                    (mContext as MainActivity).updateTotalAmount(position, ticket)

                    }
            })

            itemView.et_ticketEndNo.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(!itemView.et_ticketStartNo.text.toString().equals("")) {
                        ticket.ticketStartNo = itemView.et_ticketStartNo.text.toString().toInt()
                    }else
                        ticket.ticketStartNo = 0

                    if(!itemView.et_ticketEndNo.text.toString().equals("")) {
                        ticket.ticketEndNo = itemView.et_ticketEndNo.text.toString().toInt()
                    }else
                        ticket.ticketEndNo = 0

                    ticket.compute()
                    itemView.tv_ticketSell.text = ticket.ticketSell.toString()
                    itemView.tv_ticketAmount.text = ticket.ticketAmount.toString()

                    Log.d("Kiran", position.toString())
                    (mContext as MainActivity).updateTotalAmount(position, ticket)

                }
            })


        }
    }
}