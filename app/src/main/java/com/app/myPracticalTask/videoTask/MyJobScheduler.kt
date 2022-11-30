package com.app.myPracticalTask.videoTask

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobScheduler :JobService() {
    val TAG= "MyScheduler"
     var jobCancelled=false

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.e(TAG, "onStartJob: started ", )
        doBackGround(p0)
     return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.e(TAG, "onStartJob: Stop ", )
         return false
    }

    fun doBackGround(p0: JobParameters?) {
        kotlin.run {
            for (i in 0 until 10)
            {
                Log.e(TAG, "onStartJob: run $i", )
            }
        }

    }
}