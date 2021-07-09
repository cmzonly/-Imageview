package com.sunnyweather.myapplication


/*
 *  @项目名：  My Application 
 *  @包名：    com.sunnyweather.myapplication
 *  @文件名:   TTS
 *  @创建者:   lenovo
 *  @创建时间:  2021/7/10 0:46
 *  @描述：    TODO
 */
interface TTS {
    fun playText(playText: String?)

    fun stopSpeak()
}