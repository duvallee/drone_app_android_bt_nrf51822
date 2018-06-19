package com.modulabs.duvallee.droneremotecontroller;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.concurrent.locks.Lock;

/**
 * Created by duval on 2017-07-02.
 */

public class DroneRemoteControllerProtocol extends Object
{
    public static final String TAG = "DroneRemoteControllerProtocol";

    // 12 bits
    public static final int CHANNEL_MIN_VALUE = 0;
    public static final int CHANNEL_DEFAULT_VALUE = 2048;
    public static final int CHANNEL_MAX_VALUE = 4095;

    // ver 1.0A ("10A")
    public static final int CHANNEL_HEADER_VERSION = 0x10A0;

    // Channel ID (TAER1234)
    public static final int CHANNEL_ID_THROTTLE = 0;
    public static final int CHANNEL_ID_ROLL = 1;
    public static final int CHANNEL_ID_PITCH = 2;
    public static final int CHANNEL_ID_YAW = 3;
    public static final int CHANNEL_ID_CH_1 = 4;
    public static final int CHANNEL_ID_CH_2 = 5;
    public static final int CHANNEL_ID_CH_3 = 6;
    public static final int CHANNEL_ID_CH_4 = 7;
    public static final int CHANNEL_ID_CH_5 = 8;

    // index
    public static final int CHANNEL_START_BYTE_INDEX = 2;

    // packet size = 20
    public static final int MAX_PACKET_SIZE = 20;
    public static final int MAX_CHANNEL_SIZE = 9;

    // Channel Data
    private int[] m_ChannelData;
    private byte[] m_PacketData;


    // ---------------------------------------------------------------------------------------------
    // members
    private MainRemoteControllerActivity mParent = null;

    private final static int MAX_SEND_PACKET_COUNT = 10;        // 5 second
    private int m_timer_duration = 50;                          // 100 ms
    private int m_timer_count = 0;
    private int m_send_timer_count = 0;

    // ****************************************************************************************** //
    //
    // int Send_Channel_Message(UartService uartservice)
    //
    // ****************************************************************************************** //
    public int Send_Channel_Message()
    {
        UartService uart_service = mParent.getUartService();
        if (uart_service == null)
        {
            return -1;
        }
        m_send_timer_count = 0;

        // Majer & Minor version
        m_PacketData[0] = ((CHANNEL_HEADER_VERSION >> 8) & 0xFF);
        // extra version & command
        m_PacketData[1] = (byte) ((CHANNEL_HEADER_VERSION & 0xF0) | (0x0 & 0x0F));

        for (int i = 0; i < MAX_CHANNEL_SIZE; i++)
        {
            m_PacketData[CHANNEL_START_BYTE_INDEX + (i * 2)] = (byte) (((i << 4) | ((m_ChannelData[i] >> 8) & 0xF)) & 0xFF);
            m_PacketData[CHANNEL_START_BYTE_INDEX + (i * 2) + 1] = (byte) (m_ChannelData[i] & 0xFF);
        }

        uart_service.writeRXCharacteristic(m_PacketData);
        return 0;
    }

    // ****************************************************************************************** //
    //
    // Handler m_Send_Packet_Handler
    //
    // ****************************************************************************************** //
    private Handler m_Send_Packet_Handler = new Handler()
    {
        @Override
        // Handler events that received from UART service
        public void handleMessage(Message msg)
        {
            m_timer_count++;
            m_send_timer_count++;
            if (m_timer_count > MAX_SEND_PACKET_COUNT)
            {
                Send_Channel_Message();
                m_timer_count = 0;
            }
            m_Send_Packet_Handler.sendEmptyMessageDelayed(0, m_timer_duration);
        }
    };


    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public DroneRemoteControllerProtocol(MainRemoteControllerActivity p)
    {
        mParent = p;
        m_ChannelData = new int[MAX_CHANNEL_SIZE];
        m_PacketData = new byte[MAX_PACKET_SIZE];

        m_ChannelData[CHANNEL_ID_THROTTLE] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_ROLL] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_PITCH] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_YAW] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_CH_1] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_2] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_3] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_4] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_5] = CHANNEL_MIN_VALUE;

        m_Send_Packet_Handler.sendEmptyMessageDelayed(0, m_timer_duration);
    }


    // ****************************************************************************************** //
    //
    // void reset_channel()
    //
    // ****************************************************************************************** //
    public void reset_channel()
    {
        m_ChannelData[CHANNEL_ID_THROTTLE] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_ROLL] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_PITCH] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_YAW] = CHANNEL_DEFAULT_VALUE;
        m_ChannelData[CHANNEL_ID_CH_1] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_2] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_3] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_4] = CHANNEL_MIN_VALUE;
        m_ChannelData[CHANNEL_ID_CH_5] = CHANNEL_MIN_VALUE;

        Send_Channel_Message();
    }


    // ****************************************************************************************** //
    //
    // void set_throttle_value(int value)
    //
    // ****************************************************************************************** //
    public void set_throttle_value(int value)
    {
        m_ChannelData[CHANNEL_ID_THROTTLE] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_throttle_value()
    //
    // ****************************************************************************************** //
    public int get_throttle_value()
    {
        return (m_ChannelData[CHANNEL_ID_THROTTLE]);
    }

    // ****************************************************************************************** //
    //
    // void set_roll_value(int value)
    //
    // ****************************************************************************************** //
    public void set_roll_value(int value)
    {
        m_ChannelData[CHANNEL_ID_ROLL] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_roll_value()
    //
    // ****************************************************************************************** //
    public int get_roll_value()
    {
        return (m_ChannelData[CHANNEL_ID_ROLL]);
    }

    // ****************************************************************************************** //
    //
    // void set_pitch_value(int value)
    //
    // ****************************************************************************************** //
    public void set_pitch_value(int value)
    {
        m_ChannelData[CHANNEL_ID_PITCH] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_pitch_value()
    //
    // ****************************************************************************************** //
    public int get_pitch_value()
    {
        return (m_ChannelData[CHANNEL_ID_PITCH]);
    }

    // ****************************************************************************************** //
    //
    // void set_yaw_value(int value)
    //
    // ****************************************************************************************** //
    public void set_yaw_value(int value)
    {
        m_ChannelData[CHANNEL_ID_YAW] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_yaw_value()
    //
    // ****************************************************************************************** //
    public int get_yaw_value()
    {
        return (m_ChannelData[CHANNEL_ID_YAW]);
    }

    // ****************************************************************************************** //
    //
    // void set_ch1_value(int value)
    //
    // ****************************************************************************************** //
    public void set_ch1_value(int value)
    {
        m_ChannelData[CHANNEL_ID_CH_1] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_ch1_value()
    //
    // ****************************************************************************************** //
    public int get_ch1_value()
    {
        return (m_ChannelData[CHANNEL_ID_CH_1]);
    }

    // ****************************************************************************************** //
    //
    // void set_ch2_value(int value)
    //
    // ****************************************************************************************** //
    public void set_ch2_value(int value)
    {
        m_ChannelData[CHANNEL_ID_CH_2] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_ch2_value()
    //
    // ****************************************************************************************** //
    public int get_ch2_value()
    {
        return (m_ChannelData[CHANNEL_ID_CH_2]);
    }


    // ****************************************************************************************** //
    //
    // void set_ch3_value(int value)
    //
    // ****************************************************************************************** //
    public void set_ch3_value(int value)
    {
        m_ChannelData[CHANNEL_ID_CH_3] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_ch3_value()
    //
    // ****************************************************************************************** //
    public int get_ch3_value()
    {
        return (m_ChannelData[CHANNEL_ID_CH_3]);
    }

    // ****************************************************************************************** //
    //
    // void set_ch4_value(int value)
    //
    // ****************************************************************************************** //
    public void set_ch4_value(int value)
    {
        m_ChannelData[CHANNEL_ID_CH_4] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_ch4_value()
    //
    // ****************************************************************************************** //
    public int get_ch4_value()
    {
        return (m_ChannelData[CHANNEL_ID_CH_4]);
    }

    // ****************************************************************************************** //
    //
    // void set_ch5_value(int value)
    //
    // ****************************************************************************************** //
    public void set_ch5_value(int value)
    {
        m_ChannelData[CHANNEL_ID_CH_5] = value;
    }

    // ****************************************************************************************** //
    //
    // int get_ch5_value()
    //
    // ****************************************************************************************** //
    public int get_ch5_value()
    {
        return (m_ChannelData[CHANNEL_ID_CH_5]);
    }

    // ****************************************************************************************** //
    //
    // int get_channel_min_value()
    //
    // ****************************************************************************************** //
    public int get_channel_min_value()
    {
        return (CHANNEL_MIN_VALUE);
    }

    // ****************************************************************************************** //
    //
    // int get_channel_max_value()
    //
    // ****************************************************************************************** //
    public int get_channel_max_value()
    {
        return (CHANNEL_MAX_VALUE);
    }

    // ****************************************************************************************** //
    //
    // int get_channel_default_value()
    //
    // ****************************************************************************************** //
    public int get_channel_default_value()
    {
        return (CHANNEL_DEFAULT_VALUE);
    }
}
