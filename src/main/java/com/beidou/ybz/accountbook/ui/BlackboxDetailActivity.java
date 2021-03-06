package com.beidou.ybz.accountbook.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.beidou.ybz.accountbook.util.Utils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlackboxDetailActivity extends MvpActivity<CommonPresenter> implements CommonView<GetHjzModel>,
        OtherView<SercetKeyOverdueModel>, View.OnClickListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll)
    BottomSheetLayout ll;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.userDayAssetJzc)
    TextView userDayAssetJzc;
    @Bind(R.id.userDayAssetZzc)
    TextView userDayAssetZzc;
    @Bind(R.id.userDayAssetZqk)
    TextView userDayAssetZqk;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.rel_eye)
    RelativeLayout relEye;
    private String encMsg, signMsg;
    private View sheetView;
    private Button close, compile, cancel;

    HomeAdapter homeAdapter;
    List<GetHjzModel.BodyBean.ProListBean> proListBeans;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackbox_detail);
        ButterKnife.bind(this);
        alertDialog = new AlertDialog.Builder(mActivity)
                .setTitle("真的要关闭黑匣子吗？")
                .setMessage("关闭后将无法享受资产传承服务")
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
        tvTitle.setText("我的黑匣子");
        tvRight.setText("更多");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        tvRight.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityUtils.finishActivity(mActivity);
                ActivityUtils.startActivity(mActivity, MainActivity.class);
            }
        });
        sheetView = LayoutInflater.from(BlackboxDetailActivity.this).inflate(R.layout.item_popupwindows, ll, false);
        close = (Button) sheetView.findViewById(R.id.close);
        compile = (Button) sheetView.findViewById(R.id.compile);
        cancel = (Button) sheetView.findViewById(R.id.cancel);
        close.setOnClickListener(this);
        compile.setOnClickListener(this);
        cancel.setOnClickListener(this);
//        gethjz();
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

    @OnClick({R.id.tv_right, R.id.rel_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                ll.showWithSheetView(sheetView);
                break;
            case R.id.rel_eye:
                if (isVisiable) {
                    userDayAssetJzc.setText(UserDayAssetJzc);
                    userDayAssetZzc.setText(UserDayAssetZzc);
                    userDayAssetZqk.setText(UserDayAssetZqk);
                    isVisiable = false;
                } else {
                    userDayAssetJzc.setText("****");
                    userDayAssetZzc.setText("****");
                    userDayAssetZqk.setText("****");
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

    private BlackboxCcrAdapter blackboxCcrAdapter;
    private String UserDayAssetJzc;
    private String UserDayAssetZzc;
    private String UserDayAssetZqk;
    private boolean isVisiable = false;

    @Override
    public void getDataSuccess(GetHjzModel model) {
        proListBeans = model.getBody().getProList();
        //净资产
        if (model.getBody().getUserDayAssetJzc() != null && model.getBody().getUserDayAssetJzc() != "") {
            userDayAssetJzc.setText(model.getBody().getUserDayAssetJzc());
            UserDayAssetJzc = model.getBody().getUserDayAssetJzc();
        }
        //总资产
        if (model.getBody().getUserDayAssetZzc() != null && model.getBody().getUserDayAssetZzc() != "") {
            userDayAssetZzc.setText(model.getBody().getUserDayAssetZzc());
            UserDayAssetZzc = model.getBody().getUserDayAssetZzc();
        }
        //总负债
        if (model.getBody().getUserDayAssetZqk() != null && model.getBody().getUserDayAssetZqk() != "") {
            userDayAssetZqk.setText(model.getBody().getUserDayAssetZqk());
            UserDayAssetZqk = model.getBody().getUserDayAssetZqk();
        }
        //留言
        if (model.getBody().getInfoDto().getMessage() != null && model.getBody().getInfoDto().getMessage() != "") {
            message.setText(model.getBody().getInfoDto().getMessage());
        }

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
            case R.id.close://------关闭黑匣子
                ll.dismissSheet();

                alertDialog.show();
                break;
            case R.id.compile://------
                ll.dismissSheet();
                ActivityUtils.startActivityRightIn(mActivity, CompileBlackboxActivity.class);
                break;
            case R.id.cancel:
                ll.dismissSheet();
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
        toastShow("关闭成功");
        spUtils.setOpenblackBox(false);
//        ActivityUtils.finishActivity(mActivity);

        ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "main");
    }

    @Override
    public void onFail(String model) {
        toastShow(model);
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    BlackboxDetailActivity.this).inflate(R.layout.item_home, parent,
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
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
