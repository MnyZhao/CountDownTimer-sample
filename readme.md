## 注意事项
* 调用 参数属性详见CountDownTimerUtils
```java
//创建
 countDownTimerUtils = new CountDownTimerUtils(mBtnDown, "获取验证码",
                "s后重新获取",
                "重新获取", R.drawable.validate_code_press_bg,
                R.drawable.validate_code_normal_bg, 3 * 1000,
                1000);
//启动
countDownTimerUtils.satrt();
```
* 注意事项
```java
//    常规操作 activity 停止时 onStop
//    为了避免内存溢出(因为内部实现用handler) 要再activity的onStop中
//    调用if(countDownTimerUtils!=null){
//    countDownTimerUtils.cancel();//移除msg消息
//    }
 @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimerUtils != null) {
            countDownTimerUtils.cancel();
            Log.e(TAG, "cancel");
        }
    }
```