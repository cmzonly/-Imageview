import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import com.sunnyweather.myapplication.TTS
import java.util.*


class SystemTTS private constructor(context: Context) : UtteranceProgressListener(), TTS,
    OnUtteranceCompletedListener {
    private val mContext: Context
    private lateinit var textToSpeech // 系统语音播报类
            : TextToSpeech
    private var isSuccess = true
    override fun playText(playText: String?) {
        if (!isSuccess) {
            Toast.makeText(mContext, "系统不支持中文播报", Toast.LENGTH_SHORT).show()
            return
        }
        textToSpeech?.speak(
            playText,
            TextToSpeech.QUEUE_ADD, null, null
        )
    }

    override fun stopSpeak() {
        textToSpeech?.stop()
    }

    //    public boolean isSpeaking() {
    //        if (textToSpeech.isSpeaking()) {
    //            return true;
    //        }
    //        return false;
    //    }
    //播报完成回调
    override fun onUtteranceCompleted(utteranceId: String) {}
    override fun onStart(utteranceId: String) {}
    override fun onDone(utteranceId: String) {}
    override fun onError(utteranceId: String) {}

    companion object {
        private var singleton: SystemTTS? = null
        fun getInstance(context: Context): SystemTTS? {
            if (singleton == null) {
                synchronized(SystemTTS::class.java) {
                    if (singleton == null) {
                        singleton = SystemTTS(context)
                    }
                }
            }
            return singleton
        }
    }

    init {
        mContext = context.getApplicationContext()
        textToSpeech = TextToSpeech(mContext, OnInitListener { i ->
            //系统语音初始化成功
            if (i == TextToSpeech.SUCCESS) {
                val result = textToSpeech!!.setLanguage(Locale.CHINA)
                textToSpeech.setPitch(1.0f) // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                textToSpeech.setSpeechRate(1.0f)
                textToSpeech.setOnUtteranceProgressListener(this@SystemTTS)
                textToSpeech.setOnUtteranceCompletedListener(this@SystemTTS)
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    //系统不支持中文播报
                    isSuccess = false
                }
            }
        })
    }
}