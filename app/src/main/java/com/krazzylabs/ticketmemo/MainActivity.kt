package com.krazzylabs.ticketmemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.krazzylabs.ticketmemo.data.DataProvider
import com.krazzylabs.ticketmemo.model.Ticket
import com.krazzylabs.ticketmemo.view.CustomAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var dataProvider : DataProvider = DataProvider()
    var tickets : MutableList<Ticket> = mutableListOf()
    lateinit var adapter : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tickets.clear()
        tickets.addAll(dataProvider.tickets)
        //tickets = dataProvider.tickets

        addlist()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_save -> saveData()
            R.id.action_reset -> resetData()
            R.id.action_share -> shareApp()

           else -> super.onOptionsItemSelected(item)
        }
    }

    fun addlist(){

        //adding a layoutmanager
        rv_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //creating our adapter
         this.adapter = CustomAdapter(tickets)

        //now adding the adapter to recyclerview
        rv_list.adapter = adapter
    }

    fun updateTotalAmount(
            itemid:Int,
            ticket: Ticket
    ){
        tickets[itemid] = ticket
        var totalAmount = 0
        for(i in tickets ){
            totalAmount+= i.ticketAmount
            if(i.ticketAmount>0)
                Log.d("Total", i.ticketAmount.toString())
        }

        tv_totalAmount.text = totalAmount.toString()
        Log.d("Total", tickets.size.toString())
    }

    fun updateTotalAmount(){

        var totalAmount = 0
        for(i in tickets ){
            totalAmount+= i.ticketAmount
            if(i.ticketAmount>0)
                Log.d("Total", i.ticketAmount.toString())
        }

        tv_totalAmount.text = totalAmount.toString()
    }

    fun saveData(): Boolean {
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        return true
    }

    fun resetData(): Boolean {

        tickets.clear()
        tickets.addAll(dataProvider.tickets)

        adapter.notifyDataSetChanged()
        updateTotalAmount()

        Toast.makeText(this, "Data Reset", Toast.LENGTH_SHORT).show()
        return true
    }

    fun shareApp(): Boolean {

        val appPackageName = packageName
        val share = Intent(android.content.Intent.ACTION_SEND)
        share.type = "text/plain"

        share.putExtra(Intent.EXTRA_SUBJECT, "Ticket Memo App")
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=$appPackageName")
        startActivity(Intent.createChooser(share, "Share link!"))
        return true
    }
}
