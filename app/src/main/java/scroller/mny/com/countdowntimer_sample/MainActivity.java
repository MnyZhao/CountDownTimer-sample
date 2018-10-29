package scroller.mny.com.countdowntimer_sample;

import android.content.Context;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String TAG = MainActivity.class.getName();
    CountDownTimer countDownTimer;
    CountDownTimerUtils countDownTimerUtils;
    Button mBtnDown;

    @Override
    protected void onStart() {
        super.onStart();
        countDownTimerUtils = new CountDownTimerUtils(mBtnDown, "获取验证码",
                "s后重新获取",
                "重新获取", R.drawable.validate_code_press_bg,
                R.drawable.validate_code_normal_bg, 3 * 1000,
                1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnDown = findViewById(R.id.btn_down);
//        countDownTimerUtils = new CountDownTimerUtils(mBtnDown, "获取验证码",
//                "s后重新获取",
//                "重新获取", R.drawable.validate_code_press_bg,
//                R.drawable.validate_code_normal_bg, 3 * 1000,
//                1000);

        countDownTimer = new CountDownTimer(1000 * 3, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e(TAG, millisUntilFinished + "");
                mBtnDown.setClickable(false);
                mBtnDown.setText(millisUntilFinished / 1000 + "");
                Log.e(TAG, "onTick: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.e(TAG, "finished");
                mBtnDown.setText("重新获取");
                mBtnDown.setClickable(true);
            }
        };
        mBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_down:
//                        countDownTimer.start();
                        countDownTimerUtils.start();
                        break;
                }
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDownTimerUtils != null) {
                    countDownTimerUtils.cancel();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimerUtils != null) {
            countDownTimerUtils.cancel();
            Log.e(TAG, "cancel");
        }
    }
}