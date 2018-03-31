package com.beidou.ybz.accountbook.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.BlackboxCcrAdapter;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SppaConstant;
import com.beidou.ybz.accountbook.util.Utils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2018/2/9
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:黑匣子设置成功详情页面（优化）
 */
public class MyBlackboxDetailActivity extends MvpActivity<CommonPresenter> implements CommonView<GetHjzModel>,
        OtherView<SercetKeyOverdueModel>, View.OnClickListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.rel_eye)
    RelativeLayout relEye;
    @Bind(R.id.tvSendTime)
    TextView tvSendTime;
    @Bind(R.id.tvJzc)
    TextView tvJzc;
    @Bind(R.id.tvZzc)
    TextView tvZzc;
    @Bind(R.id.tvZqk)
    TextView tvZqk;
    @Bind(R.id.tvMessage)
    TextView tvMessage;
    @Bind(R.id.bottomSheet)
    BottomSheetLayout bottomSheet;
    @Bind(R.id.ivInvisible)
    ImageView ivInvisible;
    private String encMsg, signMsg;
    private View sheetView;
    private Button close, compile, cancel;

    private GetHjzModel getHjzModel;

    HomeAdapter homeAdapter;
    List<GetHjzModel.BodyBean.ProListBean> proListBeans;
    private AlertDialog alertDialog;
    private BlackboxCcrAdapter blackboxCcrAdapter;
    private String jzc, zzc, zqk;//净资产，总资产，总负债
    private boolean isVisiable = false;
    private String cfdate;//发送日期
    private String from;
    private boolean isFromEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myblackboxdetail);
        ButterKnife.bind(this);
        alertDialog = new AlertDialog.Builder(mActivity)
                .setTitle("提示")
                .setMessage("真的要关闭黑匣子吗？\n关闭后将无法享受资产传承服务")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delhjz();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);

        tvTitle.setText("我的黑匣子");
        tvRight.setText("更多");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        tvRight.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(mActivity, MainActivity.class);
                ActivityUtils.finishActivity(mActivity);
            }
        });
        sheetView = LayoutInflater.from(MyBlackboxDetailActivity.this).inflate(R.layout.item_popupwindows, bottomSheet, false);
        close = (Button) sheetView.findViewById(R.id.close);
        compile = (Button) sheetView.findViewById(R.id.compile);
        cancel = (Button) sheetView.findViewById(R.id.cancel);
        close.setOnClickListener(this);
        compile.setOnClickListener(this);
        cancel.setOnClickListener(this);

        handlerIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handlerIntent(intent);
    }

    void handlerIntent(Intent intent){
        if(intent != null){
            from = intent.getStringExtra("from");
        }
        if(from != null && from.equals("add")){//来自编辑界面
            isFromEdit = true;
        }else{
            isFromEdit = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gethjz();
        StatService.onPageStart(mActivity, "黑匣子详情页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "黑匣子详情页面");
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @OnClick({R.id.tv_right, R.id.rel_eye,R.id.ivInvisible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                bottomSheet.showWithSheetView(sheetView);
                break;
            case R.id.ivInvisible:
            case R.id.rel_eye:
                if (isVisiable) {
                    ivInvisible.setImageResource(R.drawable.visible);
                    tvJzc.setText(jzc);
                    tvZzc.setText(zzc);
                    tvZqk.setText(zqk);
                    isVisiable = false;
                } else {
                    ivInvisible.setImageResource(R.drawable.invisible);
                    tvJzc.setText("****");
                    tvZzc.setText("****");
                    tvZqk.setText("****");
                    isVisiable = true;
                }
                break;
        }
    }

    /**
     * 查询黑匣子
     */
    private void gethjz() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @Override
    public void getDataSuccess(GetHjzModel model) {
        getHjzModel = model;
        proListBeans = model.getBody().getProList();
        //净资产
        try {
            jzc = SppaConstant.addCommaContainsPoint(model.getBody().getUserDayAssetJzc());
            zzc = SppaConstant.addCommaContainsPoint(model.getBody().getUserDayAssetZzc());
            zqk = SppaConstant.addCommaContainsPoint(model.getBody().getUserDayAssetZqk());
            cfdate = model.getBody().getInfoDto().getCfDate();
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (cfdate != null && !TextUtils.isEmpty(cfdate)) {
            tvSendTime.setText("预计" + cfdate + "发送");
            tvSendTime.setVisibility(View.VISIBLE);
        } else {
            tvSendTime.setVisibility(View.GONE);
        }
        try {
            tvJzc.setText(jzc);
            tvZzc.setText(zzc);
            tvZqk.setText(zqk);
            tvMessage.setText(model.getBody().getInfoDto().getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }


        //留言
//        if (model.getBody().getInfoDto().getMessage() != null && model.getBody().getInfoDto().getMessage() != "") {
//            tvMessage.setText(model.getBody().getInfoDto().getMessage());
//        }

        homeAdapter = new HomeAdapter();
        blackboxCcrAdapter = new BlackboxCcrAdapter(this, proListBeans);
        recyclerview.setLayoutManager(new GridLayoutManager(mActivity, 2));
//        recyclerview.setAdapter(homeAdapter);
        recyclerview.setAdapter(blackboxCcrAdapter);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close://关闭黑匣子
                bottomSheet.dismissSheet();
                alertDialog.show();
                break;
            case R.id.compile://编辑黑匣子
                bottomSheet.dismissSheet();
//                ActivityUtils.startActivityRightIn(mActivity, CompileBlackboxActivity.class);
                Intent in = new Intent(mActivity, BlackBoxEditActivity.class);
                in.putExtra("getHjzModel",getHjzModel);
                in.putExtra("from","detail");
                startActivity(in);
                overridePendingTransition(R.anim.left_in, 0);
                break;
            case R.id.cancel:
                bottomSheet.dismissSheet();
                break;
        }
    }


    /**
     * 关闭黑匣子
     */
    private void delhjz() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.delhjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 删除黑匣子
     *
     * @param model
     */
    @Override
    public void onSuccess(SercetKeyOverdueModel model) {
        toastShow("关闭成功",R.drawable.gou_toast);
        spUtils.setOpenblackBox(false);
//        ActivityUtils.finishActivity(mActivity);

//        ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "main");
        ActivityUtils.startActivityWithFrom(mActivity, MainActivity.class, "main");
        ActivityUtils.finishActivity(MyBlackboxDetailActivity.this);
    }

    @Override
    public void onFail(String model) {
        toastShow(model);
    }




    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MyBlackboxDetailActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(proListBeans.get(position).getMobile());
        }

        @Override
        public int getItemCount() {
            return proListBeans.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.phone);
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (jumpToMain) {
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
//            }
//            ActivityUtils.finishActivity(LoginActivity.this);
            ActivityUtils.startActivity(mActivity, MainActivity.class);
            ActivityUtils.finishActivity(MyBlackboxDetailActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
