package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by duval on 2017-07-03.
 */

public class Setting_Fragment extends Fragment
{
    private MainRemoteControllerActivity parrent;
    public Setting_Fragment(MainRemoteControllerActivity p)
    {
        // Required empty public constructor }
        parrent = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//        LinearLayout linearLayout = (LinearLayout) inflater.inflate( R.layout.inflate_example, null );
//        setContentView( linearLayout );
//        LinearLayout linearLAyout = (LinearLayout) VIew.inflate( this, R.layout.inflate_example, null );


//        mEmptyList = (TextView) findViewById(R.id.empty);
//        Button cancelButton = (Button) findViewById(R.id.btn_cancel);
//        cancelButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                if (mScanning == false)
//                {
//                    scanLeDevice(true);
//                }
//                else
//                {
//                    finish();
//                }
//            }
//        });



        // -----------------------------------------------------------------------------------------
        // example
//        Button mLeft;
//        Button mRight;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mLeft = (Button) findViewById(R.id.btnleft);
//        mRight = (Button) findViewById(R.id.btnright);
//
//        mLeft.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 버튼 Left 클릭
//                setParam(3, 1);
//            }
//        });
//
//        mRight.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 버튼 Right 클릭
//                setParam(1, 3);
//            }
//        });
//    }
//        void setParam(int left, int right) {
//        LinearLayout.LayoutParams lparam = (LinearLayout.LayoutParams) mLeft
//                .getLayoutParams();
//        lparam.weight = left;
//        mLeft.setLayoutParams(lparam);
//        LinearLayout.LayoutParams rparam = (LinearLayout.LayoutParams) mRight
//                .getLayoutParams();
//        rparam.weight = right;
//        mRight.setLayoutParams(rparam);
//    }
        // -----------------------------------------------------------------------------------------



//        inflater.inflate(R.layout.setting_fragment, container, false);

//        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
//        layoutParams.gravity= Gravity.TOP;
//        layoutParams.y = 200;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.setting_fragment, container, false);
    }
}
