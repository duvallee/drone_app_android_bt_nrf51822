package com.modulabs.duvallee.droneremotecontroller;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by duval on 2017-07-28.
 */

public class DroneSearchProgressDialog extends Dialog
{
    private MainRemoteControllerActivity mMainActivity = null;

    // ****************************************************************************************** //
    //
    // Handler mMainMenuUiHandler = new Handler()
    //
    //
    // ****************************************************************************************** //
    public DroneSearchProgressDialog(Context context)
    {
        super(context);
//        mMainActivity = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 지저분한(?) 다이얼 로그 제목을 날림
//        setContentView(R.layout.custom_dialog); // 다이얼로그에 박을 레이아웃
    }


}
