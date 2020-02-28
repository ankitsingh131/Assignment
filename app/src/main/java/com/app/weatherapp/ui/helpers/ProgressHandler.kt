package com.app.weatherapp.ui.helpers

import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.weatherapp.R
import com.app.weatherapp.ui.BaseActivity

class ProgressHandler(@NonNull private val activity: BaseActivity, private var cancelable: Boolean = true) {

    private var clContainerProgressBar: ConstraintLayout

    init {
        val viewGroup: ViewGroup =
            activity.findViewById<View>(android.R.id.content).rootView as ViewGroup
        val view: View =
            activity.layoutInflater.inflate(
                R.layout.layout_in_activity_progress_bar,
                viewGroup,
                false
            )
        clContainerProgressBar = view.findViewById(R.id.clContainerProgressBar)
        viewGroup.addView(view)
        hide()
    }

    fun isCancelable(): Boolean = cancelable

    fun show(cancelable: Boolean = this.cancelable) {
        this.cancelable = cancelable
        clContainerProgressBar.visibility = View.VISIBLE
    }

    fun hide() {
        clContainerProgressBar.visibility = View.GONE
    }

    fun isShowing(): Boolean = clContainerProgressBar.visibility == View.VISIBLE
}