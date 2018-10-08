package com.krazzylabs.ticketmemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.krazzylabs.ticketmemo.data.DataProvider
import com.krazzylabs.ticketmemo.model.Ticket
import com.krazzylabs.ticketmemo.view.CustomAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var dataProvider : DataProvider = DataProvider()
    lateinit var tickets : MutableList<Ticket>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tickets =dataProvider.tickets
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addlist(){

        //adding a layoutmanager
        rv_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //creating our adapter
        val adapter = CustomAdapter(tickets)

        //now adding the adapter to recyclerview
        rv_list.adapter = adapter
        tv_totalAmount.text = "0"
    }

    fun updateTotalAmount(
            itemid:Int,
            ticket: Ticket
    ){
        tickets[itemid] = ticket
        var totalAmount = 0
        for(i in tickets ){
            totalAmount+= i.ticketAmount
        }

        tv_totalAmount.text = totalAmount.toString()
    }
}
