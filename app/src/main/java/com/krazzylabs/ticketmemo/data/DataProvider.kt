package com.krazzylabs.ticketmemo.data

import com.krazzylabs.ticketmemo.model.Ticket

class DataProvider{

    val ticketType: MutableList<Int> = mutableListOf(1,1, 2,2, 4,4, 5,5, 8,8, 10,10, 14,14, 15,15, 16,16, 18,18, 19,19, 25,25, 30,30, 40,40, 50,50, 60,60, 90,90)
    var tickets:MutableList<Ticket> = mutableListOf<Ticket>()

    constructor(){
        for (i in ticketType) {
            var ticket = Ticket(i)
            tickets.add(ticket)
        }
    }

    fun getTicketList():MutableList<Ticket>{
        tickets.clear()
        for (i in ticketType) {
            var ticket = Ticket(i)
            tickets.add(ticket)
        }
        return tickets
    }
}