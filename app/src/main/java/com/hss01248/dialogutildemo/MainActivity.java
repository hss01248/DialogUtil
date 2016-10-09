package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hss01248.dialog.MyDialogListener;
import com.hss01248.dialog.MyItemDialogListener;
import com.hss01248.dialog.StyledDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @Bind(R.id.btn_common_progress)
    Button btnCommonProgress;
    @Bind(R.id.btn_context_progress)
    Button btnContextProgress;
    @Bind(R.id.btn_material_alert)
    Button btnMaterialAlert;
    @Bind(R.id.btn_ios_alert)
    Button btnIosAlert;
    @Bind(R.id.btn_ios_bottom_sheet)
    Button btnIosBottomSheet;
    @Bind(R.id.btn_ios_center_list)
    Button btnIosCenterList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    Activity activity;
    Context context;
    @Bind(R.id.btn_ios_alert_vertical)
    Button btnIosAlertVertical;
    @Bind(R.id.btn_input)
    Button btnIosAlert2;
    @Bind(R.id.btn_multichoose)
    Button btnIosAlertVertical2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        activity = this;
        context = getApplication();


        /*<set xmlns:android="http://schemas.android.com/apk/res/android">
<rotate
android:fromDegrees="0"
android:toDegrees="359"
android:duration="500"
android:repeatCount="-1"
android:pivotX="50%"
android:pivotY="50%" />
</set> */

       /* RotateAnimation animation = new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //animation.setRepeatMode(RotateAnimation.INFINITE);
        animation.setDuration(4200);
        //animation.setFillAfter(true);
        animation.setRepeatCount(RotateAnimation.INFINITE);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        //ringView.setAnimation(animation);
        ringView.startAnimation(animation);*/

        //showMdLoading();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* Intent intent = new Intent(this, CustomDialogActivity.class);
        startActivity(intent);*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        // MyDialogUtils.showMdLoading(getApplicationContext(), "jindutiao", true, false);


        // showGlobleDialog(this);

        //showDialog();

        // showcenterDialog();
       /* MyDialogUtils.showIosAlert(getApplication(),"hh", "djdsjlfjsd", "本例子是一个自定义的弹出对话框例子源码本例子是一个自定义的弹出对话框例子源码",
                "弹出的时候有半透明效果", "", false, true, new MyDialogListener() {
            @Override
            public void onFirst(DialogInterface dialog) {

            }

            @Override
            public void onSecond(DialogInterface dialog) {

            }

            @Override
            public void onThird(DialogInterface dialog) {

            }

            @Override
            public void onCancle() {

            }
        });*/
       /* List<String> words = new ArrayList<>();
        words.add("相册");
        words.add("zhaoxiaoji");
        words.add("可以");

        MyDialogUtils.showBottomItemDialog(this,words, "", true, true, new MyItemDialogListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onBottomBtnClick() {

            }
        });*/


        // toast();
    }












    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {

        if (gloablDialog != null && gloablDialog.isShowing()) {
            gloablDialog.dismiss();

        } else {
            super.onBackPressed();
        }
    }

    Dialog gloablDialog;

    String msg = "如果你有心理咨询师般的敏锐，你会进一步发现——这个姑娘企图用考研来掩饰自己对于毕业的恐惧。";
           /* "\n" +
            "像琴姑娘这样的毕业生很多，她们一段时间内会认真地复习考研。可用不了多久，她们便会动摇，便会找出诸多借口给自己开脱，最后考研一事半途而废。\n" +
            "\n" +
            "原因，当事人根本不相信这件事能改变她的命运，能带给她想要的生活。她们相信自己不够努力，也愿意别人骂自己不努力。\n" +
            "\n" +
            "他们不愿意思考自己到底该干什么？他们抱着一个幻想，假如我真的努力就能解决问题了吧！于是他们把一个不可控的事件，在心理变成了可控，从而增加安全感。\n" +
            "\n" +
            "人真的可以为了逃避真正的思考，而做出任何你想象不到的事。\n" +
            "\n" +
            "这种目标是不重结果的，其实它跟刷微博是一个道理，它通过获取无用信息来给自己的生活制造一点喘息。\n" +
            "\n" +
            "只不过陷在“学习”中，要比陷在微博上更能安慰自己的内心，那个已经破了个大洞的内心。\n" +
            "\n" +
            "作者：剑圣喵大师\n" +
            "链接：https://www.zhihu.com/question/50126427/answer/119551026\n" +
            "来源：知乎\n" +
            "著作权归作者所有，转载请联系作者获得授权。";*/

    @Override
    public void onStop() {
        super.onStop();

    }

    @OnClick({R.id.btn_common_progress, R.id.btn_context_progress, R.id.btn_material_alert, R.id.btn_ios_alert,
            R.id.btn_ios_alert_vertical, R.id.btn_ios_bottom_sheet, R.id.btn_ios_center_list,R.id.btn_input, R.id.btn_multichoose, R.id.btn_singlechoose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common_progress:
                StyledDialog.showMdLoading(this, msg, true, true);

                break;
            case R.id.btn_context_progress:
                gloablDialog = StyledDialog.showMdLoading(getApplicationContext(), msg, true, true);
                break;
            case R.id.btn_material_alert:
                StyledDialog.showMdAlert(activity, "title", msg, "sure", "cancle", "think about", true, true, new MyDialogListener() {
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


                });
                break;
            case R.id.btn_ios_alert:
                StyledDialog.showIosAlert(activity, "title", msg, "sure", "cancle", "think about", true, true, new MyDialogListener() {
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


                });
                break;
            case R.id.btn_ios_alert_vertical:
                StyledDialog.showIosAlertVertical(this, "title", msg, "sure", "cancle", "think about", true, true, new MyDialogListener() {
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


                });
                break;
            case R.id.btn_ios_bottom_sheet: {
                final List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add(msg);
                strings.add("4");
                strings.add("5");
               /* strings.add(msg);
                strings.add("6");
                strings.add("7");
                strings.add(msg);
                strings.add("8");
                strings.add("9");
                strings.add(msg);

                strings.add("10");
                strings.add("11");
                strings.add(msg);
                strings.add("12");
                strings.add("13");
                strings.add(msg);*/

                StyledDialog.showBottomItemDialog(activity, strings, "cancle", true, true, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                });
            }
            break;
            case R.id.btn_ios_center_list:

                final List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add(msg);
                strings.add("4");
                strings.add("5");
                strings.add(msg);
             /*   strings.add("6");
                strings.add("7");
                strings.add(msg);
                strings.add("8");
                strings.add("9");
                strings.add(msg);

                strings.add("10");
                strings.add("11");
                strings.add(msg);
                strings.add("12");
                strings.add("13");
                strings.add(msg);*/

                StyledDialog.showIosSingleChoose(activity, strings, true, true, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                });

                break;
            case R.id.btn_input:
               StyledDialog.ShowNormalInput(activity, "登录", "请输入用户名", "请输入密码", "登录", "取消", true, new MyDialogListener() {
                   @Override
                   public void onFirst() {

                   }

                   @Override
                   public void onSecond() {

                   }

                   @Override
                   public void onGetInput(CharSequence input1, CharSequence input2) {
                       super.onGetInput(input1, input2);
                       showToast("input1:"+ input1 +"--input2:"+input2);
                   }
               });

                break;
            case R.id.btn_multichoose:
                String[] words = new String[]{"12","78","45","89","88","00"};


                boolean[] choseDefault = new boolean[]{false,false,false,false,true,false};

                StyledDialog.showMdMultiChoose(activity, "xuanze", words, choseDefault,  "sure", "no", new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {

                    }
                });
                break;
            case R.id.btn_singlechoose:
                String[] words2 = new String[]{"12","78","45","89","88","00"};
                StyledDialog.showMdSingleChoose(activity, "单选", 2, words2, true, true, "确定", "取消", new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        //showToast(text + "--" + position);
                    }
                },
                        new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {

                    }

                            @Override
                            public void onGetChoose(int positions, CharSequence text) {

                                showToast("chosen:"+text);
                            }
                        });

                break;


        }
    }


    public void showToast(CharSequence msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


}
