package com.example.searchapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSearchButton()
        setupBackButton()
    }

    private fun setupSearchButton() {
        search_header_search_button?.setOnClickListener {
            openSearchBar()
        }
    }

    private fun setupBackButton() {
        search_header_back_arrow?.setOnClickListener {
            search_header_edit_text?.text?.clear()
            closeSearchBar()
        }
    }

    private fun openSearchBar() {
        search_header_motion_layout?.apply {
            setTransition(R.id.search_header_open_search)
            transitionToEnd()
            onTransition(onTransitionCompleted = { _, _ -> search_header_edit_text?.showKeyboard() })
        }
        search_header_search_button?.apply {
            isClickable = false
            isFocusable = false
        }
    }

    private fun closeSearchBar() {
        search_header_motion_layout?.apply {
            setTransition(R.id.search_header_open_search)
            transitionToStart()
        }
        search_header_search_button?.apply {
            isClickable = true
            isFocusable = true
        }
        search_header_edit_text?.hideKeyboard()
        search_header_edit_text?.clearFocus()
    }
}
