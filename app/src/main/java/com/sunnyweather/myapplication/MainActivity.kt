package com.sunnyweather.myapplication

import SystemTTS
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var yuyin:TextToSpeech
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //定义属性动画---透明属性动画
        val objectAnimation1 = ObjectAnimator.ofFloat(image1,"alpha",1f,0f,1f)
        //动画事件
        objectAnimation1.duration = 1000
        //动画次数(无限播放)
        objectAnimation1.repeatCount = Animation.INFINITE
        //设置匀速播放
        objectAnimation1.interpolator = LinearInterpolator()
//        objectAnimation1.start()

        //缩放动画
        val objectAnimation2 =ObjectAnimator.ofFloat(image1,"scaleX",1f,2f)
        objectAnimation2.duration=1000
        objectAnimation2.repeatMode= ValueAnimator.REVERSE
        //动画次数(无限播放)
        objectAnimation1.repeatCount = Animation.INFINITE
        //设置匀速播放
        objectAnimation1.interpolator = LinearInterpolator()
//        objectAnimation2.start()

        val objectAnimation3 =ObjectAnimator.ofFloat(image1,"scaleY",1f,2f)
        objectAnimation3.duration=3000
        objectAnimation3.repeatMode= ValueAnimator.REVERSE
//        objectAnimation3.start()

        btn.setOnClickListener {
            Log.d(TAG, "点击了下面的图片 ")
            image1.visibility = View.VISIBLE
            AnimatorSet().apply {
                play(objectAnimation1)
                start()
            }
            SystemTTS.getInstance(this)?.playText("支付宝到账十万元")
        }

        //此处imagerview为ShapeableImageView
        /***
         * ShapeableImageView使用
         * 1.1.添加material:1.2.0依赖
            implementation 'com.google.android.material:material:1.2.0'
         2.2.在style.xml文件里面添加style
            <!-- 圆角图片 -->
            <style name="roundedCornerImageStyle">
            <item name="cornerFamily">rounded</item>
            <item name="cornerSize">25dp</item>
            </style>
        3.布局xml直接出效果
        <!-- 圆角图片 -->
        <!-- shapeAppearanceOverlay或shapeAppearance 加载style -->
        <!-- strokeColo描边颜色 -->
        <!-- strokeWidth描边宽度 -->
                <com.google.android.material.imageview.ShapeableImageView
                android:id="@+id/image1"
                android:layout_width="100dp"
                android:layout_height="100dp"
                android:padding="1dp"
                android:src="@mipmap/ic_launcher"
                app:shapeAppearanceOverlay="@style/roundedCornerImageStyle"
                app:strokeColor="#F44336"
                app:strokeWidth="2dp" />
        ————————————————
        版权声明：本文为CSDN博主「进击的包籽」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/u013365445/article/details/108538964
        ————————————————
        版权声明：本文为CSDN博主「进击的包籽」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/u013365445/article/details/108538964
         */
        image1.setOnClickListener {
            Log.d(TAG, "点击了上面的图片")
            image1.visibility = View.INVISIBLE
            objectAnimation1.cancel()

        }
    }








}