# ShadowLayout
## 说明
这是一个是边上带阴影的Layout，当然不仅仅可以是阴影，只要是渐变都可以。
## 效果图

<img width="270" height="480" src="https://github.com/shouzhong/ShadowLayout/blob/master/Screenshots/1.jpg"/>

## 使用
### 依赖
```
implementation 'com.shouzhong:ShadowLayout:1.0.1'
```
### 代码
这里提供了ShadowLayout（继承自FrameLayout）、ShadowLinearLayout、ShadowRelativeLayout，如果想在自定义View中使用的话，继承View的请在onDraw方法中调用，继承ViewGroup请在dispatchDraw方法中调用
```
ShadowUtils.setShadow(Canvas canvas, Paint paint, int width, int height, int shadowWidth, int startColor, int endColor, int ltRadius, int lbRadius, int rtRadius, int rbRadius);
```
## 属性说明
属性 | 说明
------------ | -------------
slWidth | 阴影宽度
slStartColor | 阴影起始颜色
slEndColor | 阴影结束颜色
slRadius | 圆角
slLeftTopRadius | 左上圆角，优先级大于slRadius
slLeftBottomRadius | 左下圆角，优先级大于slRadius
slRightTopRadius | 右上圆角，优先级大于slRadius
slRightBottomRadius | 右下圆角，优先级大于slRadius
