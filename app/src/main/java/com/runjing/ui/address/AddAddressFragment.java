package com.runjing.ui.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.runjing.base.SimpleBackPage;
import com.runjing.base.TitleBarFragment;
import com.runjing.common.AppMethod;
import com.runjing.common.Appconfig;
import com.runjing.wineworld.R;

import org.runjing.rjframe.ui.BindView;
import org.runjing.rjframe.ui.ViewInject;
import org.runjing.rjframe.utils.StringUtils;

/**
 * 新增地址
 */
public class AddAddressFragment  extends TitleBarFragment {
   @BindView(id = R.id.edit_rece_man)
   private EditText edit_rece_man;  //联系人
    @BindView(id = R.id.edit_rece_phone)
    private EditText edit_rece_phone; //联系电话
    @BindView(id = R.id.tv_rece_select_add,click = true)
    private TextView tv_rece_select_add; //选择地址
    @BindView(id = R.id.edit_rece_mpnum)
    private EditText edit_rece_mpnum;//补充说明
    @BindView(id = R.id.btn_style_home,click = true)
    private Button btn_style_home;//家
    @BindView(id = R.id.btn_style_company,click = true)
    private Button btn_style_company;//公司
    @BindView(id = R.id.btn_define,click = true)
    private Button btn_define;//确定

   private String contactPeople;//联系人
   private String contactPhone; //联系电话
    private String contactAddress; //地址
    private String houseNum;//门牌号
    private int mark = 1; //1，家  2，公司

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        bundle = outsideAty.getIntent().getBundleExtra(Appconfig.DATA_KEY);
        if(bundle!=null) {
            contactAddress = bundle.getString("address");
        }
        return inflater.inflate(R.layout.frag_rece_address, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.titleLayoutVisible = 1;
        actionBarRes.middleTitle = "添加地址";
        actionBarRes.rightVisiable = 1;
        actionBarRes.rightVal = "新增地址";
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if(!StringUtils.isEmpty(contactAddress)) {
            tv_rece_select_add.setText(contactAddress);
        }
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.btn_define:
                contactPeople = edit_rece_man.getText().toString();
                contactPhone = edit_rece_phone.getText().toString();

                if(StringUtils.isEmpty(contactPeople)){
                    ViewInject.toast("请输入收货人姓名");

                }else if(StringUtils.isEmpty(contactPeople)&&StringUtils.isPhone(contactPhone)){
                    ViewInject.toast("请输入正确的手机号码");

                }else if(StringUtils.isEmpty(contactAddress)){
                    ViewInject.toast("请选择收货地址");
                }

                break;
            case R.id.btn_style_home:
                mark = 1;
                break;
            case R.id.btn_style_company:
                mark = 2;
                break;
            case R.id.tv_rece_select_add:
                Bundle bundle = new Bundle();
                bundle.putString(Appconfig.DATA_KEY,"add");
                AppMethod.postShowForResult(outsideAty, 100,SimpleBackPage.SelectAddress,bundle);
                break;
        }
    }




    @Override
    public void onBackClick() {
        super.onBackClick();
        finish();
    }
}
