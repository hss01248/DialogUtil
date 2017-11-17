


# DialogUtil
material风格(v7支持包中的)，ios风格，自动获取顶层activity,可在任意界面弹出,可在任意线程弹出

[![](https://jitpack.io/v/hss01248/DialogUtil.svg)](https://jitpack.io/#hss01248/DialogUtil)

# 注意点

* 在activity已经resume后再调用,不要在onstart里用,否则可能会不显示. 见[关于dialog,PopupWindow,SoftInputBoard的弹出时机的问题](http://www.jianshu.com/p/bd98cee2854b)
* 如果非要在onstart里,就记得调用setActivity()
* 如果有的国产机不显示,就调用setActivity()
* 不要滥用loadingdialog:



> 注意使用的场景:

```
 第一此进入页面,用layout内部的loadingview,有很多statelayout框架,也可以用我的这个:
  https://github.com/hss01248/PageStateManager
  再次刷新,用刷新头显示刷新状态
  局部刷新或点击某按钮访问网络,用loading dialog,不影响页面本身状态,类似web中的ajax请求.
  
```

# 特性

* 自动获取顶层activity,无需传入activity也可弹出dialog.如果传入,则指定在此activity弹出.
* 安全,任意线程均可调用.

* 类型丰富,包括常用的ios风格dialog和meterial design风格的dialog,且按钮和文字样式可便捷地修改

* 自定义view:可以传入自定义的view,定义好事件,本工具负责安全地显示
* 也可以保留iso样式的底部按钮,上方的view完全自定义

* 考虑了显示内容超多时的滑动和与屏幕的间隙.

* 也可以设置宽高百分比来自定义宽高

* 可以关闭默认的阴影背景,从而能使用xml中自定义的背景(弹出自定义view的dialog时常用)

* 支持国际化

* input dialog 智能弹出和隐藏软键盘


# todo

bottomsheet 图标大小和文字大小的自定义
ios action sheet 加上标题
md 单选多选颜色自定义
md 增加自定义输入框+勾选功能
所有dialog 增加oncancellistener
progressdialog改成完全自定义的
所有dialog : 增加动画的自定义

选择https://github.com/liangchengcheng/android-loading-dialog中的一些好的效果加进来



# 示例图


https://github.com/hss01248/DialogUtil/wiki/0_types(%E6%89%80%E6%9C%89%E7%9A%84%E7%B1%BB%E5%9E%8B)

# 适配情况

https://github.com/hss01248/DialogUtil/wiki/screen-adapt(%E5%B1%8F%E5%B9%95%E9%80%82%E9%85%8D)




# useage



## gradle

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

**Step 2.** Add the dependency

```
	dependencies {
	        compile 'com.github.hss01248:DialogUtil:lastest release'
	}
```
lastest release: https://github.com/hss01248/DialogUtil/releases

## 初始化

```
//在Application的oncreate方法里:
传入context
StyledDialog.init(this);

在activity生命周期callback中拿到顶层activity引用:
 registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            	//在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

```

## 示例代码(MainActivity里)

```
        //使用默认样式时,无须.setxxx:
        StyledDialog.buildLoading().show();
        
        //自定义部分样式时:
        StyledDialog.buildMdAlert("title", msg,  new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        showToast("onFirst");
                    }

                    @Override
                    public void onSecond() {
                        showToast("onSecond");
                    }

                    @Override
                    public void onThird() {
                        showToast("onThird");
                    }


                })
                        .setBtnSize(20)
                        .setBtnText("i","b","3")
                        .show();
```

# 相关回调

## MyDialogListener

```
	public abstract void onFirst();//md-确定,ios-第一个
    public abstract void onSecond();//md-取消,ios-第二个
    public void onThird(){}//md-netural,ios-第三个

    public void onCancle(){}

    /**
     * 提供给Input的回调
     * @param input1
     * @param input2
     */
    public void onGetInput(CharSequence input1,CharSequence input2){

    }

    /**
     * 提供给MdSingleChoose的回调
     * @param chosen
     * @param chosenTxt
     */
    public void onGetChoose(int chosen,CharSequence chosenTxt){

    }

    /**
     * 提供给MdMultiChoose的回调
     * @param states
     */
    public void onChoosen( List<Integer> selectedIndex, List<CharSequence> selectedStrs,boolean[] states){

    }
```

## MyItemDialogListener

```
 /**
     * IosSingleChoose,BottomItemDialog的点击条目回调
     * @param text
     * @param position
     */
   public abstract void onItemClick(CharSequence text, int position);


    /**
     * BottomItemDialog的底部按钮(经常是取消)的点击回调
     */
   public void onBottomBtnClick(){}
```





# 提供的api

### 各类dialog的初始参数传递和回调:StyledDialog.buildxxx:

 ![methodsofstyledialog](img0/methodsofstyledialog.jpg)



## 自定义样式:setXxx



![methodsofconfig](img0/methodsofconfig.jpg)

 

## 最后必须调用show(),返回dialog对象





# 对话框的消失

```
StyledDialog.dismiss(DialogInterface... dialogs);
```



## 两个loading对话框不需要对象就可以直接dismisss:

```
StyledDialog.dismissLoading();
```

### progress dialog 的进度更新

```
/**
 *  可以在任何线程调用
 * @param dialog 传入show方法返回的对象
 * @param progress
 * @param max
 * @param msg 如果是转圈圈,会将msg变成msg:78%的形式.如果是水平,msg不起作用
 * @param isHorizontal 是水平线状,还是转圈圈
 */
public static void updateProgress( Dialog dialog, int progress,  int max,  CharSequence msg,  boolean isHorizontal)
```

