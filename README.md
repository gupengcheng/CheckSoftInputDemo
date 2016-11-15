# CheckSoftInputDemo Android输入法监听实现Demo
![device-2016-11-14-143621.png](http://upload-images.jianshu.io/upload_images/1761049-2751445d1756f6d7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/420)

用过ios的都知道ios上输入法关闭的同时会自动关闭输入框，那么在android上如何实现监听输入法弹出和关闭呢？本篇文章就为你提供了一种可靠的实现方式。

[演示效果视频地址](https://v.qq.com/x/page/u0345ymqfh1.html)

## 首先在AndroidManifest中配置
```
android:windowSoftInputMode="adjustResize"
```
这样每次输入法弹出和关闭都会重新计算高度实现把布局顶上去的效果

## 然后我们要自定义一个布局，监听布局大小变化
```
public class CheckSoftInputLayout extends FrameLayout {
    private OnResizeListener mOnResizeListener;
    public CheckSoftInputLayout(Context context) {
        super(context);
    }
    public CheckSoftInputLayout(Context context, AttributeSet attrs) {
        super(context, attires);
    }
    public CheckSoftInputLayout(Context context, AttributeSet attrs, int 
        defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(21)
    public CheckSoftInputLayout(Context context, AttributeSet attrs, int
        defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, old);
        if (mOnResizeListener != null) {
            mOnResizeListener.onResize(w, h, oldw, old);
        }
    }
    public void setOnResizeListener(OnResizeListener listener) { 
        this.mOnResizeListener = listener;
    }
    public interface OnResizeListener {
        void onResize(int w, int h, int oldw, int old);
    }
}
```
##然后把上面的自定义布局作为跟布局放到你需要的Activity中去，然后在Activity中绑定监听事件
```
mRootLayout.setOnResizeListener(this);
```
```
@Override
public void onResize(int w, int h, int oldw, int oldh) {
    //如果第一次初始化
    if (oldh == 0) {
        return;
    }
    //如果用户横竖屏转换
    if (w != oldw) {
        return;
    }
    if (h < oldh) {
        //输入法弹出
    } else if (h > oldh) {
        //输入法关闭
        setCommentViewEnabled(false, false);
    }
    int distance = h - old;
    EventBus.getDefault().post(new InputMethodChangeEvent(distance,mCurrentImageId));
}
```
这样只要输入法弹出和关闭就能自动实现监听，达到关闭输入框的效果，这样就和苹果的体验很一致。
到这里就介绍完了，如果有什么好的思路，也欢迎讨论分享。
[我的简书地址,欢迎关注](http://www.jianshu.com/users/590cfc23071d/latest_articles)