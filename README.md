


# DialogUtil
common used dialog with material style ( in support v7)，ios style，get top activity automatically,can invoke show() everywhere (any thread , any window)

[![](https://jitpack.io/v/hss01248/DialogUtil.svg)](https://jitpack.io/#hss01248/DialogUtil)

[中文ReadMe](README-ch.md)
<br>
[wiki](https://github.com/hss01248/DialogUtil/wiki)
<br>
<br>
any problem or bug, join the qq group to get a quick response:
<br>
<br>
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=76d7e8396ae225861d714b7f1bb976e1a06c8a76cc35c532c113e892b2d49ff0"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="DialogUtil and Android" title="DialogUtil and Android"></a>

# important points

* if you do not invoke setActivity(activit), please  invoke show() after in or after the activity onResume,or it may show in previous activity!
* about BadWindowTokenException,see the blog:[关于dialog,PopupWindow,SoftInputBoard的弹出时机的问题](http://www.jianshu.com/p/bd98cee2854b)
* if some chinese phone do not show dialog ,please invoke  setActivity(activit)
* do not abuse loadingdialog:



> the right usage is :

```
 fist into the page/activity/fragment,use the loadingview inside your layout/xml,there is many statelayout lib,or you can use my: https://github.com/hss01248/PageStateManager
 refresh a part of the contentView,or click a button to request http,which has no effect on the whole contentview,then you can use the loadingDialog, just like the ajax in web.
```

# features

* include commo dialogs with material style ( in support v7)，ios style
* **support  custom dialog** ,just deliver a view. you can retain the buttons and title with ios or material style,or hide them.


* get the top activity automatically ,so no need to deliver the activity,also support show in paticular activity by setActivity(activity)
* safety :can be invoked in any thread 
* when the content is fullScreen ,it can adjust the margin automatically,also support set the **height percent and width percent**
* has a shadow **backgroud** incase of the dimlayer not work,you can also disable it to show your own background in customview
* chained api, easy to use
* adapt to phone and tablet,high and low  resolution ratio screen 
* support localization
* **support three window types**: as a common dialog ,as a widow with TYPE_TOAST,as a activity with dialog style.
* support show **softKeyboard** automatically ,just setNeedSoftKeyboard(true)




# effect pics

https://github.com/hss01248/DialogUtil/wiki/0_types(%E6%89%80%E6%9C%89%E7%9A%84%E7%B1%BB%E5%9E%8B)

# screen adapt

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

## init

```
//in oncreate() of BaseApplication:

StyledDialog.init(this);

get activity instance in ActivityLifecycleCallbacks:
 registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            	//keep a softReference inside
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

## demo code( in MainActivity)

```
        //use default style:
        StyledDialog.buildLoading().show();
        
        //set some style:
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

# callback

## MyDialogListener

```
	public abstract void onFirst();//md-sure button
    public abstract void onSecond();//md-cancel button
    public void onThird(){}//md-netural button

    public void onCancle(){}

    /**
     * callback for Input
     * @param input1
     * @param input2
     */
    public void onGetInput(CharSequence input1,CharSequence input2){

    }

    /**
     * callback for MdSingleChoose
     * @param chosen
     * @param chosenTxt
     */
    public void onGetChoose(int chosen,CharSequence chosenTxt){

    }

    /**
     * callback for MdMultiChoose
     * @param states
     */
    public void onChoosen( List<Integer> selectedIndex, List<CharSequence> selectedStrs,boolean[] states){

    }
```

## MyItemDialogListener

```
 /**
     * for IosSingleChoose,BottomItemDialog
     * @param text
     * @param position
     */
   public abstract void onItemClick(CharSequence text, int position);


    /**
     * for BottomItemDialog
     */
   public void onBottomBtnClick(){}
```





# apis

### build different dialogs :StyledDialog.buildxxx:

 ![methodsofstyledialog](img0/methodsofstyledialog.jpg)



## set custom style:setXxx



![methodsofconfig](img0/methodsofconfig.jpg)

 

##  finally ,you must invoke show(),it returns a dialog pbject





# dismiss

```
StyledDialog.dismiss(DialogInterface... dialogs);
```



## the loading dialog can be dismissed by call :

```
StyledDialog.dismissLoading();
```

### progress dialog  

```
/**
 *  call anywhere
 */
public static void updateProgress( Dialog dialog, int progress,  int max,  CharSequence msg,  boolean isHorizontal)
```

