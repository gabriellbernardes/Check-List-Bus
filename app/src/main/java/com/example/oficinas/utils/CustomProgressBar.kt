package com.example.oficinas.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.oficinas.R

import kotlinx.android.synthetic.main.item_progress_bar.view.progressBar


class CustomProgressBar(val mActivity: Activity){

    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        /**set View*/
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.item_progress_bar,null)
        dialogView.progressBar.max = 100
        val currentPro = 100
      //  ObjectAnimator.ofInt(dialogView.proBar, "Progress", currentPro).setDuration(5000).start()

        /**set Dialog*/
        val bulider = AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}