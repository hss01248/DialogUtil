package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperRcvAdapter;
import com.hss01248.dialog.adapter.SuperRcvHolder;
import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_common_progress)
    Button btnCommonProgress;
    @BindView(R.id.btn_context_progress)
    Button btnContextProgress;
    @BindView(R.id.btn_material_alert)
    Button btnMaterialAlert;
    @BindView(R.id.btn_ios_alert)
    Button btnIosAlert;
    @BindView(R.id.btn_ios_bottom_sheet)
    Button btnIosBottomSheet;
    @BindView(R.id.btn_ios_center_list)
    Button btnIosCenterList;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    Activity activity;
    Context context;
    @BindView(R.id.btn_ios_alert_vertical)
    Button btnIosAlertVertical;
    @BindView(R.id.btn_input)
    Button btnIosAlert2;
    @BindView(R.id.btn_multichoose)
    Button btnIosAlertVertical2;
    Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handler = new Handler();
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
            R.id.btn_multichoose, R.id.btn_singlechoose,R.id.btn_md_bs,R.id.btn_md_bs_listview,R.id.btn_md_bs_Gridview,
            R.id.btn_context_progress_h,R.id.btn_context_progress_c,R.id.btn_customview,R.id.btn_dismiss,
        R.id.btn_test_badToken,R.id.btn_customview2,R.id.btn_material_input,R.id.btn_ad,R.id.btn_ad_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_badToken:
                testBadToken();
                break;
            case R.id.btn_dismiss:
                StyledDialog.dismissLoading(this);
                break;
            case R.id.btn_common_progress:
                final Dialog[] dialog00 = new Dialog[1];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dialog00[0] =  StyledDialog.buildLoading( "加载中...").show();
                    }
                }).run();
                //StyledDialog.dismissLoading();
                //showToast("dismissLoading() called ");

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        StyledDialog.updateLoadingMsg("jjjjj"+ new Random().nextInt(100),dialog00[0]);
                    }
                },50,2000);



                break;
            case R.id.btn_context_progress:
                gloablDialog = StyledDialog.buildMdLoading().show();


                //StyledDialog.dismissLoading();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       StyledDialog
                               .updateLoadingMsg("jjjjj"+ new Random().nextInt(100),gloablDialog);
                    }
                },3000);
                break;
            case R.id.btn_context_progress_h:
               final ProgressDialog dialog= (ProgressDialog) StyledDialog.buildProgress( getString(R.string.dialogutil_loading),true).setCancelable(false,false).show();
                final int[] progress = {0};
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progress[0] +=10;
                        StyledDialog.updateProgress(dialog, progress[0],100,"progress",true);
                        if(progress[0]>100){
                            timer.cancel();
                            dialog.dismiss();
                        }
                    }
                },500,500);


                break;
            case R.id.btn_context_progress_c:
                final ProgressDialog dialog2= (ProgressDialog) StyledDialog.buildProgress( "下载中...",false).show();
                final int[] progress2 = {0};

                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progress2[0] +=10;
                        StyledDialog.updateProgress(dialog2, progress2[0],100,"progress",false);
                        if(progress2[0]>100){
                            timer2.cancel();
                        }
                    }
                },500,500);

                break;
            case R.id.btn_material_alert:
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
                        .setBtnSize(29)
                    .setForceHeightPercent(0.75f)//ugly
                    //.setForceWidthPercent(0.90f)
                        .setBtnText("i","b","3")
                    //.setBtnText("i")
                        .setBtnColor(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.dialogutil_text_black)
                        .show();

                break;
            case R.id.btn_ios_alert:
                String msg2 = "前言\n" +
                        "基于 Jenkins 的 KubeSphere DevOps 系统是专为 Kubernetes 中的 CI/CD 工作流设计的，它提供了一站式的解决方案，帮助开发和运维团队用非常简单的方式构建、测试和发布应用到 Kubernetes。它还具有插件管理、Binary-to-Image (B2I)、Source-to-Image (S2I)、代码依赖缓存、代码质量分析、流水线日志等功能。\n" +
                        "\n" +
                        "DevOps 系统为用户提供了一个自动化的环境，应用可以自动发布到同一个平台。它还兼容第三方私有镜像仓库（如 Harbor）和代码库（如 GitLab/GitHub/SVN/BitBucket）。它为用户提供了全面的、可视化的 CI/CD 流水线，打造了极佳的用户体验，而且这种兼容性强的流水线能力在离线环境中非常有用。\n" +
                        "\n" +
                        "简而言之，DevOps 可以帮助我们拉取代码、项目编译、构建镜像、推送镜像、项目部署全自动一条龙服务。\n" +
                        "\n" +
                        "关于如何安装 DevOps 功能请参照：https://kubesphere.io/zh/docs/pluggable-components/devops/。\n" +
                        "\n" +
                        "创建 DevOps 工程 && 创建流水线\n" +
                        "创建 DevOps 工程：企业空间—>DevOps 工程—>创建；填入工程名称，点击确定即可。"+
                        "前言2\n" +
                        "基于 Jenkins 的 KubeSphere DevOps 系统是专为 Kubernetes 中的 CI/CD 工作流设计的，它提供了一站式的解决方案，帮助开发和运维团队用非常简单的方式构建、测试和发布应用到 Kubernetes。它还具有插件管理、Binary-to-Image (B2I)、Source-to-Image (S2I)、代码依赖缓存、代码质量分析、流水线日志等功能。\n" +
                        "\n" +
                        "DevOps 系统为用户提供了一个自动化的环境，应用可以自动发布到同一个平台。它还兼容第三方私有镜像仓库（如 Harbor）和代码库（如 GitLab/GitHub/SVN/BitBucket）。它为用户提供了全面的、可视化的 CI/CD 流水线，打造了极佳的用户体验，而且这种兼容性强的流水线能力在离线环境中非常有用。\n" +
                        "\n" +
                        "简而言之，DevOps 可以帮助我们拉取代码、项目编译、构建镜像、推送镜像、项目部署全自动一条龙服务。\n" +
                        "\n" +
                        "关于如何安装 DevOps 功能请参照：https://kubesphere.io/zh/docs/pluggable-components/devops/。\n" +
                        "\n" +
                        "创建 DevOps 工程 && 创建流水线\n" +
                        "创建 DevOps 工程：企业空间—>DevOps 工程—>创建；填入工程名称，点击确定即可。";
                StyledDialog.buildIosAlert( "titlexxxxxxxxxxxxxxxxxxxxxtitlexxxxxxxxxxxxxxxxxxxxx", msg2,  new MyDialogListener() {
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
                    //.setBtnText("sure","cancle","hhhh")
                    .setBtnText("cancel","copy")
                    .setBtnColor(R.color.dialogutil_text_black,R.color.colorPrimaryDark,0)

                    //.setForceWidthPercent(0.99f)
                    //.setForceHeightPercent(0.88f)
                    //.setBgRes(R.drawable.leak_canary_icon)
                    //.setCustomContentHolder(new CustomContentHolder(this))
                    .show();
                break;
            case R.id.btn_ios_alert_vertical:
                StyledDialog.buildIosAlertVertical( "titlelll", msg,  new MyDialogListener() {
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

                StyledDialog.buildBottomItemDialog( strings, "好的",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                })
                    //.setTitle("人生若只如初见")
                    .show();
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

                StyledDialog.buildIosSingleChoose(strings, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                })
                    .setTitle("任选一个")
                    .show();

                break;
            case R.id.btn_input:
               StyledDialog.buildNormalInput("登录", "请输入用户名", "请输入密码",
                   "dashedsen", "51420",  new MyDialogListener() {
                   @Override
                   public void onFirst() {

                   }

                   @Override
                   public void onSecond() {

                   }

                   @Override
                   public boolean onInputValid(CharSequence input1, CharSequence input2, EditText editText1, EditText editText2) {
                       showToast("input1--input2:"+input1+"--"+input2 +"is not accepted!");
                       return false;
                   }

                   @Override
                   public void onGetInput(CharSequence input1, CharSequence input2) {
                       super.onGetInput(input1, input2);
                       showToast("input1:"+ input1 +"--input2:"+input2);
                   }
               })
                   .setInput2HideAsPassword(false)
                   .setCancelable(true,true)
                   .show();

                break;

            case R.id.btn_material_input:
                StyledDialog.buildMdInput("登录", "fefe", "请输入密码",
                    "fee544es", "fesfeee",  new MyDialogListener() {
                        @Override
                        public void onFirst() {

                        }

                        @Override
                        public void onSecond() {

                        }

                        @Override
                        public boolean onInputValid(CharSequence input1, CharSequence input2, EditText editText1, EditText editText2) {
                            showToast("input1--input2:"+input1+"--"+input2 +"is not accepted!");
                            return false;
                        }

                        @Override
                        public void onGetInput(CharSequence input1, CharSequence input2) {
                            super.onGetInput(input1, input2);
                            showToast("input1:"+ input1 +"--input2:"+input2);
                        }
                    })
                    .setInput2HideAsPassword(true)
                    .setCancelable(true,true)
                    .show();

                break;

            case R.id.btn_multichoose:
                String[] words = new String[]{"12","78","45","89","88","00"};


                //boolean[] choseDefault = new boolean[]{false,false,false,false,true,false};

                StyledDialog.buildMdMultiChoose( "xuanze", words, new ArrayList<Integer>(),  new MyDialogListener() {
                    @Override
                    public void onFirst() {

                    }

                    @Override
                    public void onSecond() {

                    }

                    @Override
                    public void onChoosen( List<Integer> selectedIndex, List<CharSequence> selectedStrs,boolean[] states) {
                        super.onChoosen( selectedIndex, selectedStrs,states);
                        Logger.object(states);
                        Logger.object(selectedIndex);
                        Logger.object(selectedStrs);
                    }

                    @Override
                    public void onGetChoose(boolean[] states) {
                        super.onGetChoose(states);
                    }
                }).setTitleColor(R.color.dialogutil_ios_btntext_blue).show();
                break;
            case R.id.btn_singlechoose:
                String[] words2 = new String[]{"12","78","45","89","88","00"};
                StyledDialog.buildMdSingleChoose("单选", 2, words2,  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "--" + position);
                    }
                }).setDismissAfterResultCallback(false).show();

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

               StyledDialog.buildCustomBottomSheet(recyclerView).show();//不好建立回调





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
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"7777"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"8"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"9"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"10"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"11"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"12"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"13"));
                datas2.add(new BottomSheetBean(R.mipmap.ic_launcher,"14"));




                StyledDialog.buildBottomSheetLv( "拉出来溜溜", datas2, "this is cancle button", new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text+"---"+position);
                    }
                }).setBottomSheetDialogMaxHeightPercent(0.3f).show();}
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


                StyledDialog.buildBottomSheetGv( "", datas2, "this is cancle button",4, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text+"---"+position);
                    }
                }).setHasBehaviour(false).show();
                break;
            case R.id.btn_customview:
                ViewGroup customView = (ViewGroup) View.inflate(this,R.layout.customview,null);
                final ConfigBean bean = StyledDialog.buildCustom(customView, Gravity.CENTER)
                    .setForceHeightPercent(0.7f)
                    .setForceWidthPercent(0.8f)
                    .setHasShadow(false);
                final Dialog dialog1 =   bean.show();
                WebView webView = (WebView) customView.findViewById(R.id.webview);
                final TextView textView = (TextView) customView.findViewById(R.id.tv_title);
                webView.loadUrl("http://www.jianshu.com/p/bcdee5821a7f");

                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        Tool.adjustWH(dialog1,bean);
                    }
                });
                webView.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        textView.setText(title);
                    }
                });

            break;
            case R.id.btn_customview2:
                ViewGroup customView2 = (ViewGroup) View.inflate(this,R.layout.customview2,null);
                StyledDialog.buildCustom(customView2,Gravity.CENTER).setForceWidthPercent(0.90f).setForceHeightPercent(0.8f).show();
                break;
            case R.id.btn_ad:
                ViewGroup customView3 = (ViewGroup) View.inflate(this,R.layout.customview2,null);
                StyledDialog.buildCustomAsAdStyle(customView3,Gravity.RIGHT|Gravity.TOP)
                    .setxMarginLR(15)
                    .setxMarginTB(30)
                    .setxWidth(55).show();
                break;
            case R.id.btn_ad_msg:

                StyledDialog.buildAlertAsAdStyle("title","hahahhahahahahha",Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM)
                    .setxWidth(25).show();
                break;
            default:break;


        }
    }

    private void testBadToken() {
        startActivity(new Intent(this,BadTokenActy.class));


    }


    public void showToast(CharSequence msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


}
