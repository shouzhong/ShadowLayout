# ShadowLayout
## 说明
这是一个是边上带阴影的Layout。
## 使用
### 依赖
```
implementation 'com.shouzhong:ShadowLayout:1.0.0'
```
### 代码
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">
    <com.shouzhong.shadowlayout.ShadowLayout
        android:layout_width="300dp"
        android:layout_height="100dp"
        app:slWidth="10dp"
        app:slRadius="40dp"
        app:slStartColor="#10000000"
        app:slEndColor="#00000000"></com.shouzhong.shadowlayout.ShadowLayout>

    <com.shouzhong.shadowlayout.ShadowLinearLayout
        android:layout_width="300dp"
        android:layout_height="100dp"
        app:sllWidth="10dp"
        app:sllRadius="40dp"
        app:sllStartColor="#10000000"
        app:sllEndColor="#00000000"></com.shouzhong.shadowlayout.ShadowLinearLayout>

    <com.shouzhong.shadowlayout.ShadowRelativeLayout
        android:layout_width="300dp"
        android:layout_height="100dp"
        app:srlWidth="10dp"
        app:srlRadius="40dp"
        app:srlStartColor="#10000000"
        app:srlEndColor="#00000000"></com.shouzhong.shadowlayout.ShadowRelativeLayout>
</LinearLayout>
```
如果想在自定义View中使用的话，继承View的请在onDraw方法中调用，继承ViewGroup请在dispatchDraw方法中调用
```
ShadowUtils.setShadow(Canvas canvas, Paint paint, int width, int height, int shadowWidth, int startColor, int endColor, int radius);
```
## 属性说明
### ShadowLayout
属性 | 说明
------------ | -------------
slWidth | 阴影宽度
slStartColor | 阴影起始颜色
slEndColor | 阴影结束颜色
slRadius | 圆角
### ShadowRelativeLayout
属性 | 说明
------------ | -------------
srlWidth | 阴影宽度
srlStartColor | 阴影起始颜色
srlEndColor | 阴影结束颜色
srlRadius | 圆角
### ShadowRelativeLayout
属性 | 说明
------------ | -------------
sllWidth | 阴影宽度
sllStartColor | 阴影起始颜色
sllEndColor | 阴影结束颜色
sllRadius | 圆角
## 效果图
<img width="270" height="480" src="https://github.com/shouzhong/ShadowLayout/blob/master/Screenshots/1.jpg"/>
