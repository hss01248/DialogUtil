package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.adapter.SuperRcvAdapter;
import com.hss01248.dialog.adapter.SuperRcvHolder;
import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
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
        StyledDialog.init(getApplicationContext());


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
       /* List<String> wordsIos = new ArrayList<>();
        wordsIos.add("相册");
        wordsIos.add("zhaoxiaoji");
        wordsIos.add("可以");

        MyDialogUtils.showBottomItemDialog(this,wordsIos, "", true, true, new MyItemDialogListener() {
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
            R.id.btn_ios_alert_vertical, R.id.btn_ios_bottom_sheet, R.id.btn_ios_center_list,R.id.btn_input,
            R.id.btn_multichoose, R.id.btn_singlechoose,R.id.btn_md_bs,R.id.btn_md_bs_listview,R.id.btn_md_bs_Gridview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common_progress:
                StyledDialog.buildLoading(this, "加载中...", true, false).show();


                break;
            case R.id.btn_context_progress:
                gloablDialog = StyledDialog.buildMdLoading(getApplicationContext(), msg, true, false).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        StyledDialog.buildLoading(MainActivity.this, "加载中...", true, false).show();
                    }
                },3000);
                break;
            case R.id.btn_material_alert:
                StyledDialog.buildMdAlert(activity, "title", msg,  new MyDialogListener() {
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
                break;
            case R.id.btn_ios_alert:
                StyledDialog.buildIosAlert(activity, "title", msg,  new MyDialogListener() {
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


                }).setBtnText("sure","cancle","hhhh").show();
                break;
            case R.id.btn_ios_alert_vertical:
                StyledDialog.buildIosAlertVertical(this, "title", msg,  new MyDialogListener() {
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


                }).show();
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

                StyledDialog.buildBottomItemDialog(activity, strings, "cancle",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                }).show();
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

                StyledDialog.buildIosSingleChoose(activity, strings, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                }).show();

                break;
            case R.id.btn_input:
               StyledDialog.buildNormalInput(getApplicationContext(), "登录", "请输入用户名", "请输入密码", "登录", "取消",  new MyDialogListener() {
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
               }).show();

                break;
            case R.id.btn_multichoose:
                String[] words = new String[]{"12","78","45","89","88","00"};


                boolean[] choseDefault = new boolean[]{false,false,false,false,true,false};

                StyledDialog.buildMdMultiChoose(activity, "xuanze", words, choseDefault,  new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {

                    }
                }).show();
                break;
            case R.id.btn_singlechoose:
                String[] words2 = new String[]{"12","78","45","89","88","00"};
                StyledDialog.buildMdSingleChoose(activity, "单选", 2, words2,  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "--" + position);
                    }
                }).show();

                break;
            case R.id.btn_md_bs:
                String[] words3 = new String[]{"12","78","45","89","88","00"};
                List<String> datas = Arrays.asList(words3);

              // final BottomSheetDialog dialog = new BottomSheetDialog(this);
                RecyclerView recyclerView = new RecyclerView(this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                SuperRcvAdapter adapter = new SuperRcvAdapter(this) {
                    @Override
                    protected SuperRcvHolder generateCoustomViewHolder(int viewType) {

                        return new SuperRcvHolder<String>(inflate(R.layout.item_text)) {

                            Button mButton;
                            @Override
                            public void assignDatasAndEvents(Activity context, final String data) {
                                if (mButton==null){
                                    mButton = (Button) itemView.findViewById(R.id.btnee);
                                }
                                mButton.setText(data);
                                mButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showToast(data);
                                    }
                                });
                            }
                        };
                    }
                };
                recyclerView.setAdapter(adapter);
                adapter.addAll(datas);
                adapter.addAll(datas);
                adapter.addAll(datas);

               StyledDialog.buildCustomBottomSheet(this,recyclerView).show();//不好建立回调





                break;

            case R.id.btn_md_bs_listview:{
                List<BottomSheetBean> datas2 = new ArrayList<>();

                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"1"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"222"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"333333"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"444"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"55"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"666"));

                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"7777"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"fddsf"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"67gfhfg"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"oooooppp"));




                StyledDialog.buildBottomSheetLv(activity, "拉出来溜溜", datas2, "this is cancle button", new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text+"---"+position);
                    }
                }).show();}
                break;

            case R.id.btn_md_bs_Gridview:
                List<BottomSheetBean> datas2 = new ArrayList<>();

                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"1"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"222"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"333333"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"444"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"55"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"666"));

                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"7777"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"fddsf"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"67gfhfg"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"oooooppp"));




                StyledDialog.buildBottomSheetGv(activity, "拉出来溜溜", datas2, "this is cancle button",3, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text+"---"+position);
                    }
                }).show();
                break;


        }
    }


    public void showToast(CharSequence msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


}
