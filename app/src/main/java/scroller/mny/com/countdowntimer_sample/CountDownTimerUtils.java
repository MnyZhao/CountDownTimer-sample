package scroller.mny.com.countdowntimer_sample;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

/**
 * Crate by E470PD on 2018/10/29
 * 常规操作 activity 停止时 onStop
 * 为了避免内存溢出 要再activity的onStop中
 * 调用if(countDownTimerUtils!=null){
 * countDownTimerUtils.cancel();
 * }
 */
public class CountDownTimerUtils extends CountDownTimer {
    public String TAG = this.getClass().getName();
    Button view;
    String msg;
    String msgEnd;
    int selectId;
    int unSelectId;

    /**
     * @param view              view 展示秒数的控件
     * @param defaultMsg        默认显示文字  比如 获取验证码
     * @param msg               msg 显示消息 比如 59 s后重新发送
     * @param msgEnd            msgEns 倒计时结束后显示的内容 比如 重新获取
     * @param selectId          点击背景资源id
     * @param unSelectId        未点击背景资源id
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.  开始到结束的总时间 单位 ：毫秒
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.  间隔时间 单位毫秒
     */
    public CountDownTimerUtils(Button view, String defaultMsg, String msg, String msgEnd, int selectId, int unSelectId, long millisInFuture, long countDownInterval) {
        super(millisInFuture + 500, countDownInterval);
        //这里加不一定是500毫秒 15毫秒以上1000 毫秒以下都可以
        //加500是因为CountDownTmer 计算非常精确 设置3000毫秒可能是从2899开始
        //加500 是为了找回取整值
        this.view = view;
        this.msg = msg;
        this.msgEnd = msgEnd;
        this.selectId = selectId;
        this.unSelectId = unSelectId;
        this.view.setBackgroundResource(unSelectId);
        this.view.setText(defaultMsg);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        view.setClickable(false);
        view.setBackgroundResource(selectId);

        view.setText(millisUntilFinished / 1000 + msg);
        Log.e(TAG, millisUntilFinished / 1000 + "bt");
    }

    @Override
    public void onFinish() {
        view.setClickable(true);
        view.setBackgroundResource(unSelectId);
        view.setText(msgEnd);
        this.cancel();//因为内部实现用的是handler 所以调用cancel 移除所有消息
    }
}
