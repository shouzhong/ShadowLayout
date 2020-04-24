package com.shouzhong.shadowlayout.demo.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shouzhong.shadowlayout.demo.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Test1Activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        tv = findViewById(R.id.tv);
        EventBusUtils.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    public void onClickNext(View v) {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }

    public void onClickPost(View v) {
        EventBusUtils.post(new TestBean("post"));
    }

    public void onClickPostSticky(View v) {
        EventBusUtils.postSticky(System.currentTimeMillis());
    }

    public void onClickRemoveStickyEvent(View v) {
        EventBusUtils.removeStickyEvent(Long.class);
    }

    public void onClickRemoveAllStickyEvents(View v) {
        EventBusUtils.removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void a(Long l) {
        tv.setText(l + "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void b(TestBean b) {
        tv.setText(b.s);
    }
}
