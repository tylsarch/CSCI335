package com.example.tyler.morsecode

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mtextView.movementMethod = ScrollingMovementMethod()

        testButton.setOnClickListener{view -> appendTextAndScroll(inputText.text.toString());
        hideKeyboard();
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun appendTextAndScroll(text: String)
    {
        if(mtextView != null)
        {
            mtextView.append(text + "\n")
            val layout = mtextView.layout
            if(layout != null)
            {
                val scrollDelta = (layout!!.getLineBottom( mtextView.lineCount - 1)
                        - mtextView.scrollY - mtextView.height)
                if(scrollDelta > 0)
                {
                    mtextView.scrollBy(0, scrollDelta)
                }
            }
        }
    }

    fun Activity.hideKeyboard()
    {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View)
    {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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
}
