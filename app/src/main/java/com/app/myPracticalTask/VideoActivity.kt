package com.app.myPracticalTask

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.simform.videooperations.*
import java.io.File


class VideoActivity : AppCompatActivity() {
      val REQUEST_VIDEO_CAPTURE = 1
    var videoView:VideoView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        var btnClick = findViewById<TextView>(R.id.tvVideo)
         videoView = findViewById<VideoView>(R.id.videoView)
        btnClick.setOnClickListener { v->
            dispatchTakeVideoIntent()
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
          //  takeVideoIntent.setAction(Intent.ACTION_CAMERA_BUTTON)
            takeVideoIntent.resolveActivity(packageManager)?.also {

                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }
   /* fun splitVideo(inputPath: Uri?) {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(this,  inputPath)
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val millis = time!!.toLong()
        Log.e("TAG", "onActivityResult: $millis.....$time", )
        var secs =TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
//        var s=   String.format("%02d:%02d:%02d",
//            TimeUnit.MILLISECONDS.toHours(millis),
//            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
//            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        Log.e("TAG", "onActivityResult..time split: $millis.....$time.....$.s", )
         var start =""
         var end =""
         for (i in 0..secs step 2  ) {
             Log.e("TAG", "splitVideo: $i",)

             if(i<10)  {
                 start ="0$i"
             }
             end =( i+2) .toString()
             getSPlitVideoOnTIme(inputPath?.path,start, end)
         }
    }
    private fun getSPlitVideoOnTIme(inputStrPath: String?, start: String, end: String) {

        val startTimeString = "00:00:$start" //(HH:MM:SS)
        val endTimeString = "00:02:$end"// (HH:MM:SS)
        val outputPath = com.simform.videooperations.Common.getFilePath(this, com.simform.videooperations.Common.VIDEO)
        val query:Array<String> = FFmpegQueryExtension().cutVideo(inputStrPath!!, startTimeString, endTimeString, outputPath)
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
              //var s = data.data?.encodedUserInfo.t

              splitVideo(data.data)
             // videoView.spli
          }
        }
    }*/

    fun splitVideo(inputPath: String?) {
        val startTimeString = "00:00:00" //(HH:MM:SS)
        val endTimeString = "00:00:05"// (HH:MM:SS)
//        val path: File = Environment.getExternalStoragePublicDirectory(
//            Environment.DIRECTORY_PICTURES
//        )
//        val file = File(path, "DemoPicture.jpg")

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
                Log.i("FFMPEG LOG : ", "success")
            }

            override fun cancel() {
                Log.i("FFMPEG LOG : ", "cancel")
            }

            override fun failed() {
                Log.i("FFMPEG LOG : ", "failed")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            if(data!=null) {
                splitVideo(data.data?.path)
                val videoUri: Uri = data.data!!
                videoView?.setVideoURI(videoUri)
                videoView?.start()


                // videoView.spli
            }
        }
    }
}