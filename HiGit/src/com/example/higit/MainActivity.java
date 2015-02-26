package com.example.higit;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextToSpeech tts = null;

	private Boolean ttsIsInit = false;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		intent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(intent, 22);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {

		if (requestCode == 22) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				tts = new TextToSpeech(this, new OnInitListener() {

					@Override
					public void onInit(int status) {

						if (status == TextToSpeech.SUCCESS) {
							ttsIsInit = true;
							if (tts.isLanguageAvailable(Locale.ENGLISH) >= 0) {
								Toast.makeText(MainActivity.this, "ENGLISH", Toast.LENGTH_SHORT).show();
								tts.setLanguage(Locale.ENGLISH);
							}
							tts.setPitch(0.8f);
							tts.setSpeechRate(0.9f);
							tts.speak(
									"The Duke of Cambridge -- traveling without wife Kate or son Prince George this trip -- will arrive in Japan on Thursday and then will head to China on March 1, before returning to the U.K. on March 4.",
									TextToSpeech.QUEUE_ADD, null);
							Toast.makeText(MainActivity.this, "播报完成", Toast.LENGTH_SHORT).show();
						}
					}
				});
			} else {
				Toast.makeText(this, "未发现TTS_Engine", Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	protected void onDestroy() {

		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();

	}

}
