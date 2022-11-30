package com.app.myPracticalTask.videoTask

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import android.widget.VideoView
import com.app.myPracticalTask.R
import com.simform.videooperations.*

class VideoSchelueActivity : AppCompatActivity() {
    val REQUEST_VIDEO_CAPTURE = 1
    var videoView: VideoView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_schelue)

        var btnClick = findViewById<TextView>(R.id.tvVideo)
        videoView = findViewById<VideoView>(R.id.videoView)
        btnClick.setOnClickListener { v->
            dispatchTakeVideoIntent()
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }
    fun splitVideo(inputPath: String?) {
        val startTimeString = "00:01:00" //(HH:MM:SS)
        val endTimeString = "00:02:00"// (HH:MM:SS)
        val outputPath = com.simform.videooperations.Common.getFilePath(this, com.simform.videooperations.Common.VIDEO)
        val query:Array<String> = FFmpegQueryExtension().cutVideo(inputPath!!, startTimeString, endTimeString, outputPath)
        CallBackOfQuery().callQuery(query, object : FFmpegCallBack {
            override fun statisticsProcess(statistics: Statistics) {
                Log.i("FFMPEG LOG : ", "${statistics.videoFrameNumber}")
            }

            override fun process(logMessage: LogMessage) {
                Log.i("FFMPEG LOG : ", logMessage.text)
            }

            override fun success() {
                //Output = outputPath

            }

            override fun cancel() {
            }

            override fun failed() {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            if(data!=null) {
                val videoUri: Uri = data.data!!
                videoView?.setVideoURI(videoUri)
                videoView?.start()

                splitVideo(data.data?.path)
                // videoView.spli
            }
        }
    }
}