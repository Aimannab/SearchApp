package com.example.searchapp

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.motion.widget.MotionLayout

//region View
fun View?.showKeyboard() {
    if (this == null) return
    val focusRequested = requestFocus()
    if (!focusRequested) {
        Log.d("Debug", "Focus requested but not succeeded.")
        return
    }
    var imm: InputMethodManager? = null
    try {
        imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    } catch (illegalStateException: IllegalStateException) {
        Log.d("Debug", illegalStateException.message.toString())
    }

    imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun View?.hideKeyboard() {
    if (this == null) return
    requestFocus()

    var imm: InputMethodManager? = null
    try {
        imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    } catch (illegalStateException: IllegalStateException) {
        Log.d("Debug", illegalStateException.message.toString())
    }

    imm?.hideSoftInputFromWindow(windowToken, 0);
}
//endRegion

//region MotionLayout
fun MotionLayout.onTransition(
    onTransitionChange: (motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) -> Unit =
        { _, _, _, _ -> },
    onTransitionCompleted: (motionLayout: MotionLayout?, currentId: Int) -> Unit = { _, _ -> },
    onTransitionStarted: (motionLayout: MotionLayout?, startId: Int, endId: Int) -> Unit = { _, _, _ -> },
    onTransitionTrigger: (motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) -> Unit =
        { _, _, _, _ -> }
) {

    this.setTransitionListener(object : MotionLayout.TransitionListener {
        override fun onTransitionChange(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float
        ) {
            onTransitionChange(motionLayout, startId, endId, progress)
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            onTransitionCompleted(motionLayout, currentId)
        }

        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
            onTransitionStarted(motionLayout, startId, endId)
        }

        override fun onTransitionTrigger(
            motionLayout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) {
            onTransitionTrigger(motionLayout, triggerId, positive, progress)
        }
    })
}
//endregion