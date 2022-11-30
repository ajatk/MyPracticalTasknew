package com.app.myPracticalTask.videoTask

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.media.MediaRecorder.VideoSource.SURFACE
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.myPracticalTask.R
import java.io.IOException


class VideoCaptureActivity : AppCompatActivity() , View.OnClickListener,  SurfaceHolder.Callback{
    var recorder: MediaRecorder? = null
    var holder: SurfaceHolder? = null
    var recording = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_video_capture)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
      //  requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        recorder = MediaRecorder()

        setContentView(R.layout.activity_video_capture)

        val cameraView = findViewById<View>(R.id.CameraView) as SurfaceView
        holder = cameraView.holder
        holder!!.addCallback(this)
        holder!!.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        cameraView.isClickable = true
        cameraView.setOnClickListener(this)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,0)
        }
          else if (ContextCompat.checkSelfPermission(this,
                 Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                 Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
             val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
             ActivityCompat.requestPermissions(this, permissions,0)
         }
      //  for (count in 2..10 step 2)
        else initRecorder()
    }

    private fun initRecorder() {
        recorder!!.setAudioSource(MediaRecorder.AudioSource.CAMCORDER)
        recorder?.let {
             it.setVideoSource(MediaRecorder.VideoSource.CAMERA)
            val cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH)
            recorder?.setProfile(cpHigh)
            recorder?.setOutputFile("/sdcard/videocapture_example.mp4")
            recorder?.setMaxDuration(50000) // 50 seconds
            recorder?.setMaxFileSize(5000000)
        }
       // Approximately 5 megabytes
       // prepareRecorder()
    }

    private fun prepareRecorder() {
        recorder?.setPreviewDisplay(holder!!.surface)
        try {
            recorder!!.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            finish()
        } catch (e: IOException) {
            e.printStackTrace()
            finish()
        }
    }

   override fun onClick(v: View?) {
        if (recording) {
            recorder!!.stop()
            recording = false

            // Let's initRecorder so we can record again
            initRecorder()
            prepareRecorder()
        } else {
            recording = true
            recorder!!.start()
        }
    }
     

    override fun surfaceCreated(p0: SurfaceHolder) {
        prepareRecorder()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        if (recording) {
            recorder!!.stop()
            recording = false
        }
        recorder!!.release()
        finish()
    }
}

private fun View.setOnClickListener(videoCaptureActivity: VideoCaptureActivity) {

}
