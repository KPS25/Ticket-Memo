package com.krazzylabs.ticketmemo.data

import com.krazzylabs.ticketmemo.model.Ticket

class DataProvider{

    val ticketType: MutableList<Int> = mutableListOf(1, 2, 4, 5, 8, 10, 14, 15, 16, 18, 19, 25, 30, 50, 60, 90)
    var tickets:MutableList<Ticket> = mutableListOf<Ticket>()

    constructor(){
        for (i in ticketType) {
            var ticket = Ticket(i)
            tickets.add(ticket)
        }
    }
}