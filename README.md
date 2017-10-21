


# DialogUtil
common used dialog with material style ( in support v7)，ios style，get top activity automatically,can invoke show() everywhere (any thread , any window)

[![](https://jitpack.io/v/hss01248/DialogUtil.svg)](https://jitpack.io/#hss01248/DialogUtil)

[中文文档](README-ch.md)
any problem or bug, join the qq group to get a quick response:
<br>
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=76d7e8396ae225861d714b7f1bb976e1a06c8a76cc35c532c113e892b2d49ff0"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="DialogUtil and Android" title="DialogUtil and Android"></a>

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
* support  custom dialog ,just deliver a view


* get the top activity automatically ,so no need to deliver the activity , but you also can  invoke setActivity(activit) if in oncreate()
* when the content is fullScreen ,it can adjust the margin automatically
* also can set the height percent and width percent
* has a shadow backgroud incase of the dimlayer not work,you can also disable it to show your own background in customview
* chained api, easy to use
* adapt to phone and tablet,high and low  resolution ratio screen 




# effect pics

ios  style - loading

```
buildLoading( )
buildLoading( CharSequence msg)

updateLoadingMsg(final String msg)
```

 ![loading-common](img0/loading-common.jpg)

android style-loading

```
buildMdLoading( )
buildMdLoading( CharSequence msg) 

updateLoadingMsg(final String msg)
```

  ![loading](img0/loading.jpg)

ProgressDialog of horizontal

```
buildProgress( CharSequence msg,boolean isHorizontal)


updateProgress(final Dialog dialog, final int progress, final int max, final CharSequence msg, final boolean isHorizontal)
```

 ![progress_h](img0/progress_h.jpg)

ProgressDialog of spinner

 ![progress_c](img0/progress_c.jpg)

AlertDialog of appcompat-v7

```
buildMdAlert( CharSequence title, CharSequence msg, MyDialogListener listener)
```

 ![md_alert](img0/md_alert.jpg)



```
buildMdSingleChoose( CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener)
```

 ![md_single_choose](img0/md_single_choose.jpg)

```
buildMdMultiChoose( CharSequence title, CharSequence[] words, List<Integer> selectedIndexs, MyDialogListener btnListener)
```

 ![md_multi_choose](img0/md_multi_choose.jpg)





ios style

```
buildIosAlert( CharSequence title, CharSequence msg, MyDialogListener listener)
```

 ![ios_alert](img0/ios_alert.jpg)



```
buildIosAlertVertical( CharSequence title, CharSequence msg, MyDialogListener listener) 
```

 ![ios_alert_v](img0/ios_alert_v.jpg)

```
buildNormalInput( CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener
```

 ![ios_input](img0/ios_input.jpg)



```
buildIosSingleChoose( List<? extends CharSequence> words, MyItemDialogListener listener)
```

 ![ios_centerlist](img0/ios_centerlist.jpg)

```
buildBottomItemDialog( List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener)
```

 ![ios_bottom](img0/ios_bottom.jpg)



BottomSheetDialog of design

```
buildBottomSheetLv(CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener)
```

  ![btnsheet-lv](img0/btnsheet-lv.jpg)





```
buildBottomSheetGv( CharSequence title, List datas, CharSequence bottomTxt,int columnsNum ,MyItemDialogListener listener)
```

 ![btnsheet-gv](img0/btnsheet-gv.jpg)





gridview拉出来时:

 ![btnsheet-gv-out](img0/btnsheet-gv-out.jpg)



 show a custom view:( the demo below contains a webview)

```
 ViewGroup customView = (ViewGroup) View.inflate(this,R.layout.customview,null);
final ConfigBean bean = StyledDialog.buildCustom(customView, Gravity.CENTER);
final Dialog dialog1 =   bean.show();
```

 ![customview-webview](img0/customview-webview.jpg)



# screen adapt

![ios_loading](imgs/ios_loading.jpg)



![md_loading](imgs/md_loading.jpg)

![progress_h](imgs/progress_h.jpg)

![progress_c](imgs/progress_c.jpg)



![md_alert](imgs/md_alert.jpg)

![md_multi_choose](imgs/md_multi_choose.jpg)

![md_single_chooose](imgs/md_single_chooose.jpg)



![ios_alert_h](imgs/ios_alert_h.jpg)



![ios_alert_v](imgs/ios_alert_v.jpg)



![ios_input](imgs/ios_input.jpg)



![ios_bottom](imgs/ios_bottom.jpg)



![ios_centerlist](imgs/ios_centerlist.jpg)



![md_bottom_list](imgs/md_bottom_list2.jpg)



![md_bottom_grid](imgs/md_bottom_grid.jpg)

> if do not need the haf transparent layer(by setTransparentBehind(true),or the haf transparent layer(dimlayer) do not funtion well,the shadow effect show as below :

![nodim_1](imgs/nodim_1.jpg)



![nodim_2](imgs/nodim_2.jpg)



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

