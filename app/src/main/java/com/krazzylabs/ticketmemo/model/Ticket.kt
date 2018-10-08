package com.krazzylabs.ticketmemo.model

data class Ticket(
    var ticketValue : Int,
    var ticketStartNo: Int = 0,
    var ticketEndNo: Int = 0,
    var ticketSell: Int = 0,
    var ticketAmount: Int = 0
){

    fun compute(){
        if(ticketEndNo-ticketStartNo >= 0){
            ticketSell = ticketEndNo-ticketStartNo
            ticketAmount = ticketSell*ticketValue
        }

    }
}