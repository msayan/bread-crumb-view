package com.holoo.library.breadcrumbviewsample;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.holoo.library.breadcrumb.BreadCrumb
import com.holoo.library.breadcrumb.BreadCrumbView

class MainActivity : AppCompatActivity() {

    private var counter = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<BreadCrumb>()

        list.add(BreadCrumb.Builder().setTitle("Crumb 1").build())
        list.add(BreadCrumb.Builder().setTitle("Crumb 2").build())
        list.add(BreadCrumb.Builder().setTitle("Crumb 3").build())

        findViewById<BreadCrumbView>(R.id.bread_crumb_view).setBreadCrumbList(list)

        findViewById<BreadCrumbView>(R.id.bread_crumb_view).setListener(object : BreadCrumbView.Listener {
            override fun onBreadCrumbSelected(crumb: BreadCrumb) {
                Toast.makeText(this@MainActivity, "Bread Crumb Clicked ${crumb.getTitle()}", Toast.LENGTH_LONG).show()
            }

        })

        findViewById<TextView>(R.id.add_bread_crumb).setOnClickListener {
            findViewById<BreadCrumbView>(R.id.bread_crumb_view).addBreadCrumb(BreadCrumb.Builder().setTitle("Crumb $counter").build())
            counter++
        }

        findViewById<TextView>(R.id.get_current).setOnClickListener {
            Toast.makeText(this@MainActivity, "Current Bread Crumb : ${findViewById<BreadCrumbView>(R.id.bread_crumb_view).getCurrentCrumb()?.getTitle()}", Toast.LENGTH_LONG).show()

        }
    }


    override fun onBackPressed() {
        if (!findViewById<BreadCrumbView>(R.id.bread_crumb_view).goBack())
            super.onBackPressed()
    }

}
