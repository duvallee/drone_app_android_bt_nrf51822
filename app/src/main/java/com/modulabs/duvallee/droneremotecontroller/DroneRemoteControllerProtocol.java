package com.modulabs.duvallee.droneremotecontroller;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-02.
 */

public class DroneRemoteControllerProtocol extends Object
{
    public static final String TAG = "DroneRemoteControllerProtocol";

    // ---------------------------------------------------------------------------------------------
    // Start : Shared Area (8 bytes)
    public final int PROTOCOL_HEADER_1_HIGH_BYTE_INDEX = 0;
    public final int PROTOCOL_HEADER_1_LOW_BYTE_INDEX = 1;

    public final int PROTOCOL_HEADER_COMMAND_INDEX = 2;
    public final int PROTOCOL_HEADER_SIZE_INDEX = 3;

    public final int PROTOCOL_OPTION_1_HIGH_BYTE_INDEX = 4;
    public final int PROTOCOL_OPTION_1_LOW_BYTE_INDEX = 5;

    public final int PROTOCOL_OPTION_2_HIGH_BYTE_INDEX = 6;
    public final int PROTOCOL_OPTION_2_LOW_BYTE_INDEX = 7;

    public final int PROTOCOL_BASIC_MAX_SIZE = 8;
    // End : Shared Area

    // Start : Channel Info
    public final int PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX = 8;
    public final int PROTOCOL_CHANEL_1_LOW_BYTE_INDEX = 9;

    public final int PROTOCOL_CHANEL_2_HIGH_BYTE_INDEX = 10;
    public final int PROTOCOL_CHANEL_2_LOW_BYTE_INDEX = 11;

    public final int PROTOCOL_CHANEL_3_HIGH_BYTE_INDEX = 12;
    public final int PROTOCOL_CHANEL_3_LOW_BYTE_INDEX = 13;

    public final int PROTOCOL_CHANEL_4_HIGH_BYTE_INDEX = 14;
    public final int PROTOCOL_CHANEL_4_LOW_BYTE_INDEX = 15;

    public final int PROTOCOL_CHANEL_5_HIGH_BYTE_INDEX = 16;
    public final int PROTOCOL_CHANEL_5_LOW_BYTE_INDEX = 17;

    public final int PROTOCOL_CHANEL_6_HIGH_BYTE_INDEX = 18;
    public final int PROTOCOL_CHANEL_6_LOW_BYTE_INDEX = 19;

    public final int PROTOCOL_CHANEL_7_HIGH_BYTE_INDEX = 20;
    public final int PROTOCOL_CHANEL_7_LOW_BYTE_INDEX = 21;

    public final int PROTOCOL_CHANEL_8_HIGH_BYTE_INDEX = 22;
    public final int PROTOCOL_CHANEL_8_LOW_BYTE_INDEX = 23;

    public final int PROTOCOL_CHANEL_9_HIGH_BYTE_INDEX = 24;
    public final int PROTOCOL_CHANEL_9_LOW_BYTE_INDEX = 25;

    public final int PROTOCOL_CHANEL_10_HIGH_BYTE_INDEX = 26;
    public final int PROTOCOL_CHANEL_10_LOW_BYTE_INDEX = 27;

    public final int PROTOCOL_CHANEL_11_HIGH_BYTE_INDEX = 28;
    public final int PROTOCOL_CHANEL_11_LOW_BYTE_INDEX = 29;

    public final int PROTOCOL_CHANEL_12_HIGH_BYTE_INDEX = 30;
    public final int PROTOCOL_CHANEL_12_LOW_BYTE_INDEX = 31;

    public final int PROTOCOL_CHANNEL_MAX_INDEX = 32;

    public final int PROTOCOL_CHANNEL_SEND_PACKET_SIZE = 16;                                        // (PROTOCOL_CHANNEL_MAX_INDEX / 2);

    // ---------------------------------------------------------------------------------------------
    // channel id (constant)
    // channel map : spektrum / Graupner / JR   : TAER1234, (Throttle, ROLL, PITCH, YAW)
    //             : Default                    : AETR1234, (ROLL, PITCH, THROTTLE, YAW)
    public final int SPEKTRUM_CHANNEL_ROLL = 0;
    public final int SPEKTRUM_CHANNEL_PITCH = 1;
    public final int SPEKTRUM_CHANNEL_YAW = 3;
    public final int SPEKTRUM_CHANNEL_THROTTLE = 2;
    public final int SPEKTRUM_CHANNEL_GEAR = 4;
    public final int SPEKTRUM_CHANNEL_AUX_1 = 5;
    public final int SPEKTRUM_CHANNEL_AUX_2 = 6;
    public final int SPEKTRUM_CHANNEL_AUX_3 = 7;
    public final int SPEKTRUM_CHANNEL_AUX_4 = 8;
    public final int SPEKTRUM_CHANNEL_AUX_5 = 9;
    public final int SPEKTRUM_CHANNEL_AUX_6 = 10;
    public final int SPEKTRUM_CHANNEL_AUX_7 = 11;
    public final int SPEKTRUM_MAX_CHANNEL = 12;

    // ---------------------------------------------------------------------------------------------
    // min, max, default (constant)
    public final int SPEKTRUM_CHANNEL_ROLL_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_ROLL_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_ROLL_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_PITCH_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_PITCH_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_PITCH_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_YAW_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_YAW_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_YAW_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_THROTTLE_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_THROTTLE_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_THROTTLE_DEFAULT_VALUE = 100;

    public final int SPEKTRUM_CHANNEL_GEAR_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_GEAR_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_GEAR_DEFAULT_VALUE= 512;

    public final int SPEKTRUM_CHANNEL_AUX_1_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_1_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_1_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_2_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_2_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_2_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_3_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_3_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_3_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_4_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_4_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_4_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_5_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_5_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_5_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_6_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_6_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_6_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_AUX_7_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_AUX_7_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_AUX_7_DEFAULT_VALUE = 512;

    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE = 1023;

    // ---------------------------------------------------------------------------------------------
    // header (Version) ver 1.0.01 (high byte : 4 bit (Major) + 4 bit (Minor), low byte : sub version)
    public final int PROTOCOL_HEADER_HIGH_VERSION = 0x10;
    public final int PROTOCOL_HEADER_LOW_VERSION = 0x01;

    // Command (byte : 4 bit (phone -> transmitter : 0x0?, transmitter -> phone : 0xF?)
    public final int PROTOCOL_REGISTER_MESSAGE = 0x01;
    public final int PROTOCOL_REGISTER_RESPONSE = 0xF1;
    public final int PROTOCOL_ALIVE_MESSAGE = 0x02;
    public final int PROTOCOL_ALIVE_RESPONSE = 0xF2;
    public final int PROTOCOL_CHANNEL_MESSAGE = 0x03;
    public final int PROTOCOL_CHANNEL_RESPONSE = 0xF3;

    public final int PROTOCOL_CHANNEL_SHIFT = 4;

    // ---------------------------------------------------------------------------------------------
    // members
    private MainRemoteControllerActivity mParent = null;

    private int[] mChannelValue;
    private int[] mChannelMinValue;
    private int[] mChannelMaxValue;

    // buffer for packet
    private byte[] mRegisterProtocolData;
    private byte[] mAliveProtocolData;
    private byte[] mChannelProtocolData;

    private byte[] mLastProtocolData;


    private int mChannelPacketDataCount = 0;
    private WAIT_FOR_RESPONSE mWait_for_response;
    private RESULT_RESPONSE mResult_response;
    private int mResponseCode;

    private static enum WAIT_FOR_RESPONSE
    {
        NONE_WAIT_RESPONSE,
        WAIT_REGISTER_COMMAND_RESPONSE,
        WAIT_ALIVE_COMMAND_RESPONSE,
        WAIT_CHANNEL_COMMAND_RESPONSE,
    };

    private static enum RESULT_RESPONSE
    {
        SUCCESS_RESPONSE,
        FAILED_RESPONSE,
        TIMEOUT_RESPONSE,
    };


    // ---------------------------------------------------------------------------------------------
    // members

    // ****************************************************************************************** //
    //
    // void ClearProtocolVariable()
    //
    // ****************************************************************************************** //
    public void ClearProtocolVariable()
    {
        for (int i = 0; i < PROTOCOL_CHANNEL_MAX_INDEX; i++)
        {
            mLastProtocolData[i] = 0x0;
        }
    }


    // ****************************************************************************************** //
    //
    // int Send_Register_Message(UartService uartservice)
    //
    // ****************************************************************************************** //
    public int Send_Register_Message(UartService uartservice)
    {
        if (uartservice == null)
        {
            return -1;
        }
        if (mWait_for_response != WAIT_FOR_RESPONSE.NONE_WAIT_RESPONSE)
        {
            return -1;
        }

        mWait_for_response = WAIT_FOR_RESPONSE.WAIT_REGISTER_COMMAND_RESPONSE;

        mResult_response = RESULT_RESPONSE.SUCCESS_RESPONSE;
        mResponseCode = 0;

        ClearProtocolVariable();
        mLastProtocolData[PROTOCOL_HEADER_1_HIGH_BYTE_INDEX] = PROTOCOL_HEADER_HIGH_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_1_LOW_BYTE_INDEX] = PROTOCOL_HEADER_LOW_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_COMMAND_INDEX] = PROTOCOL_REGISTER_MESSAGE;
        mLastProtocolData[PROTOCOL_HEADER_SIZE_INDEX] = PROTOCOL_BASIC_MAX_SIZE;

        mLastProtocolData[PROTOCOL_OPTION_1_HIGH_BYTE_INDEX] = 0;
        mLastProtocolData[PROTOCOL_OPTION_1_LOW_BYTE_INDEX] = 0;
        mLastProtocolData[PROTOCOL_OPTION_2_HIGH_BYTE_INDEX] = 0;
        mLastProtocolData[PROTOCOL_OPTION_2_LOW_BYTE_INDEX] = 0;

        byte[] tmpData = new byte[PROTOCOL_BASIC_MAX_SIZE];
        for (int i = 0; i < PROTOCOL_BASIC_MAX_SIZE; i++)
        {
            tmpData[i] = mLastProtocolData[i];
        }
        uartservice.writeRXCharacteristic(tmpData);
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int Send_Alive_Message(UartService uartservice, int alive_count)
    //
    // ****************************************************************************************** //
    public int Send_Alive_Message(UartService uartservice, int alive_count)
    {
        if (uartservice == null)
        {
            return -1;
        }
        if (mWait_for_response != WAIT_FOR_RESPONSE.NONE_WAIT_RESPONSE)
        {
            return -1;
        }

        mWait_for_response = WAIT_FOR_RESPONSE.WAIT_ALIVE_COMMAND_RESPONSE;

        mResult_response = RESULT_RESPONSE.SUCCESS_RESPONSE;
        mResponseCode = 0;

        ClearProtocolVariable();
        mLastProtocolData[PROTOCOL_HEADER_1_HIGH_BYTE_INDEX] = PROTOCOL_HEADER_HIGH_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_1_LOW_BYTE_INDEX] = PROTOCOL_HEADER_LOW_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_COMMAND_INDEX] = PROTOCOL_REGISTER_MESSAGE;
        mLastProtocolData[PROTOCOL_HEADER_SIZE_INDEX] = PROTOCOL_BASIC_MAX_SIZE;

        mLastProtocolData[PROTOCOL_OPTION_1_HIGH_BYTE_INDEX] = (byte) ((alive_count >> 24) & 0xFF);
        mLastProtocolData[PROTOCOL_OPTION_1_LOW_BYTE_INDEX] = (byte) ((alive_count >> 16) & 0xFF);
        mLastProtocolData[PROTOCOL_OPTION_2_HIGH_BYTE_INDEX] = (byte) ((alive_count >> 8) & 0xFF);
        mLastProtocolData[PROTOCOL_OPTION_2_LOW_BYTE_INDEX] = (byte) (alive_count & 0xFF);

        byte[] tmpData = new byte[PROTOCOL_BASIC_MAX_SIZE];
        for (int i = 0; i < PROTOCOL_BASIC_MAX_SIZE; i++)
        {
            tmpData[i] = mLastProtocolData[i];
        }
        uartservice.writeRXCharacteristic(tmpData);
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int Send_Channel_Message(UartService uartservice)
    //
    // ****************************************************************************************** //
    private UartService mUartService = null;
    public int Send_Channel_Message(UartService uartservice)
    {
        if (uartservice == null)
        {
            return -1;
        }

//        if (mWait_for_response != WAIT_FOR_RESPONSE.NONE_WAIT_RESPONSE)
//        {
//            return -1;
//        }

        mWait_for_response = WAIT_FOR_RESPONSE.WAIT_CHANNEL_COMMAND_RESPONSE;

        mResult_response = RESULT_RESPONSE.SUCCESS_RESPONSE;
        mResponseCode = 0;

        ClearProtocolVariable();
        mLastProtocolData[PROTOCOL_HEADER_1_HIGH_BYTE_INDEX] = PROTOCOL_HEADER_HIGH_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_1_LOW_BYTE_INDEX] = PROTOCOL_HEADER_LOW_VERSION;
        mLastProtocolData[PROTOCOL_HEADER_COMMAND_INDEX] = PROTOCOL_CHANNEL_MESSAGE;
        mLastProtocolData[PROTOCOL_HEADER_SIZE_INDEX] = PROTOCOL_CHANNEL_MAX_INDEX;

        // Channel : ROLL : 0
        mLastProtocolData[PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_ROLL << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_ROLL] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_1_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_ROLL] & 0xFF);

        // Channel : PITCH : 1
        mLastProtocolData[PROTOCOL_CHANEL_2_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_PITCH << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_PITCH] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_2_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_PITCH] & 0xFF);

        // Channel : YAW : 2
        mLastProtocolData[PROTOCOL_CHANEL_3_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_YAW << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_YAW] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_3_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_YAW] & 0xFF);

        // Channel : THROTTLE : 3
        mLastProtocolData[PROTOCOL_CHANEL_4_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_THROTTLE << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_4_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] & 0xFF);

        // Channel : GEAR : 4
        mLastProtocolData[PROTOCOL_CHANEL_5_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_GEAR << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_GEAR] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_5_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_GEAR] & 0xFF);

        // Channel : AUX 1 : 5
        mLastProtocolData[PROTOCOL_CHANEL_6_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_1 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_1] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_6_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_1] & 0xFF);

        // Channel : AUX 2 : 6
        mLastProtocolData[PROTOCOL_CHANEL_7_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_2 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_2] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_7_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_2] & 0xFF);

        // Channel : AUX 3 : 7
        mLastProtocolData[PROTOCOL_CHANEL_8_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_3 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_3] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_8_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_3] & 0xFF);

        // Channel : AUX 4 : 8
        mLastProtocolData[PROTOCOL_CHANEL_9_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_4 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_4] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_9_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_4] & 0xFF);

        // Channel : AUX 5 : 9
        mLastProtocolData[PROTOCOL_CHANEL_10_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_5 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_5] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_10_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_5] & 0xFF);

        // Channel : AUX 6 : 10
        mLastProtocolData[PROTOCOL_CHANEL_11_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_6 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_6] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_11_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_6] & 0xFF);

        // Channel : AUX 7 : 11
        mLastProtocolData[PROTOCOL_CHANEL_12_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_7 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_7] >> 8) & 0xF));
        mLastProtocolData[PROTOCOL_CHANEL_12_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_7] & 0xFF);

        int crc = 0;
        for (int i = PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX; i <= (PROTOCOL_CHANEL_12_LOW_BYTE_INDEX); i++)
        {
            crc += mLastProtocolData[i];
            crc = (crc & 0xFFFF);
        }
        mLastProtocolData[PROTOCOL_OPTION_1_HIGH_BYTE_INDEX] = (byte) ((crc >> 8) & 0xFF);
        mLastProtocolData[PROTOCOL_OPTION_1_LOW_BYTE_INDEX] = (byte) (crc & 0xFF);

        mLastProtocolData[PROTOCOL_OPTION_2_HIGH_BYTE_INDEX] = 0;
        mLastProtocolData[PROTOCOL_OPTION_2_LOW_BYTE_INDEX] = 0;

        mChannelPacketDataCount = 2;
        mUartService = uartservice;
        mHandler.sendEmptyMessageDelayed(0, 1);
        return 0;
    }

    private Handler mHandler = new Handler()
    {
        @Override
        // Handler events that received from UART service
        public void handleMessage(Message msg)
        {
            byte[] packet_16_byte = new byte[PROTOCOL_CHANNEL_SEND_PACKET_SIZE];

            if (mChannelPacketDataCount == 2)
            {
                for (int i = 0; i < PROTOCOL_CHANNEL_SEND_PACKET_SIZE; i++)
                {
                    packet_16_byte[i] = mLastProtocolData[i];
                }
                mChannelPacketDataCount = 1;
                mUartService.writeRXCharacteristic(packet_16_byte);
                mHandler.sendEmptyMessageDelayed(0, 20);
            }
            else if (mChannelPacketDataCount == 1)
            {
                for (int i = 0; i < PROTOCOL_CHANNEL_SEND_PACKET_SIZE; i++)
                {
                    packet_16_byte[i] = mLastProtocolData[i + PROTOCOL_CHANNEL_SEND_PACKET_SIZE];
                }
                mChannelPacketDataCount = 0;
                mUartService.writeRXCharacteristic(packet_16_byte);
            }
            else
            {
                mUartService = null;
                mChannelPacketDataCount = 0;
            }
        }
    };

    // ****************************************************************************************** //
    //
    // int Response_Message(byte[] response_data)
    //
    // ****************************************************************************************** //
    private int test_count = 0;
    public int Response_Message(byte[] response_data)
    {
        mWait_for_response = WAIT_FOR_RESPONSE.NONE_WAIT_RESPONSE;

        test_count++;
        int size = response_data.length;
        if ((test_count % 30) == 0)
        {
            int i = 0;
            String szMsg = new String();
            for (i = 0; i < size; i++)
            {
                szMsg += "[" + response_data[i] + "] ";
            }
            Toast.makeText(mParent, szMsg, Toast.LENGTH_SHORT).show();
        }
        return 0;
    }


    // ****************************************************************************************** //
    //
    // byte[] getProtocolData()
    //
    // ****************************************************************************************** //
//    public byte[] getProtocolData()
//    {
//        byte[] protocol_data = new byte[PROTOCOL_MAX_INDEX];
//
//        protocol_data[PROTOCOL_HEADER_1_HIGH_BYTE_INDEX] = PROTOCOL_HEADER_HIGH_VERSION;
//        protocol_data[PROTOCOL_HEADER_1_LOW_BYTE_INDEX] = PROTOCOL_HEADER_LOW_VERSION;
//
//        protocol_data[PROTOCOL_HEADER_COMMAND_INDEX] = PROTOCOL_SEND_TO_TRANSMITTER;
//        protocol_data[PROTOCOL_HEADER_SIZE_INDEX] = PROTOCOL_MAX_INDEX;
//
//        // Channel : ROLL : 0
//        protocol_data[PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_ROLL << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_ROLL] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_1_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_ROLL] & 0xFF);
//
//        // Channel : PITCH : 1
//        protocol_data[PROTOCOL_CHANEL_2_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_PITCH << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_PITCH] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_2_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_PITCH] & 0xFF);
//
//        // Channel : YAW : 2
//        protocol_data[PROTOCOL_CHANEL_3_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_YAW << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_YAW] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_3_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_YAW] & 0xFF);
//
//        // Channel : THROTTLE : 3
//        protocol_data[PROTOCOL_CHANEL_4_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_THROTTLE << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_4_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] & 0xFF);
//
//        // Channel : GEAR : 4
//        protocol_data[PROTOCOL_CHANEL_5_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_GEAR << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_GEAR] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_5_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_GEAR] & 0xFF);
//
//        // Channel : AUX 1 : 5
//        protocol_data[PROTOCOL_CHANEL_6_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_1 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_1] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_6_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_1] & 0xFF);
//
//        // Channel : AUX 2 : 6
//        protocol_data[PROTOCOL_CHANEL_7_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_2 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_2] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_7_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_2] & 0xFF);
//
//        // Channel : AUX 3 : 7
//        protocol_data[PROTOCOL_CHANEL_8_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_3 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_3] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_8_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_3] & 0xFF);
//
//        // Channel : AUX 4 : 8
//        protocol_data[PROTOCOL_CHANEL_9_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_4 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_4] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_9_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_4] & 0xFF);
//
//        // Channel : AUX 5 : 9
//        protocol_data[PROTOCOL_CHANEL_10_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_5 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_5] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_10_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_5] & 0xFF);
//
//        // Channel : AUX 6 : 10
//        protocol_data[PROTOCOL_CHANEL_11_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_6 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_6] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_11_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_6] & 0xFF);
//
//        // Channel : AUX 7 : 11
//        protocol_data[PROTOCOL_CHANEL_12_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_7 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_7] >> 8) & 0xF));
//        protocol_data[PROTOCOL_CHANEL_12_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_7] & 0xFF);
//
//        int crc = 0;
//        for (int i = 0; i < (PROTOCOL_CHANEL_12_LOW_BYTE_INDEX + 1); i++)
//        {
//            crc += protocol_data[i];
//            crc = (crc & 0xFFFF);
//        }
//        protocol_data[PROTOCOL_CRC_HIGH_BYTE_INDEX] = (byte) ((crc >> 8) & 0xFF);
//        protocol_data[PROTOCOL_CRC_LOW_BYTE_INDEX] = (byte) (crc & 0xFF);
//
//        return protocol_data;
//    }

    // ****************************************************************************************** //
    //
    // byte[] getProtocolData()
    //
    // ****************************************************************************************** //
//    public void responseProtocolData(byte[] data)
//    {
//
//    }


    // ****************************************************************************************** //
    //
    // int getProtocolRegisterMsgLength()
    //
    // ****************************************************************************************** //
    public int getProtocolRegisterMsgLength()
    {
        return (PROTOCOL_BASIC_MAX_SIZE);
    }

    // ****************************************************************************************** //
    //
    // int getProtocolAliveMsgLength()
    //
    // ****************************************************************************************** //
    public int getProtocolAliveMsgLength()
    {
        return (PROTOCOL_BASIC_MAX_SIZE);
    }

    // ****************************************************************************************** //
    //
    // int getProtocolChannelMsgLength()
    //
    // ****************************************************************************************** //
    public int getProtocolChannelMsgLength()
    {
        return (PROTOCOL_CHANNEL_MAX_INDEX);
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public DroneRemoteControllerProtocol(MainRemoteControllerActivity p)
    {
        mParent = p;
        mChannelValue = new int[SPEKTRUM_MAX_CHANNEL];
        mChannelMinValue = new int[SPEKTRUM_MAX_CHANNEL];
        mChannelMaxValue = new int[SPEKTRUM_MAX_CHANNEL];

        mLastProtocolData = new byte[PROTOCOL_CHANNEL_MAX_INDEX];
        mWait_for_response = WAIT_FOR_RESPONSE.NONE_WAIT_RESPONSE;
        mResult_response = RESULT_RESPONSE.SUCCESS_RESPONSE;
        mResponseCode = 0;

        // default value
        mChannelValue[SPEKTRUM_CHANNEL_ROLL] = SPEKTRUM_CHANNEL_ROLL_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_PITCH] = SPEKTRUM_CHANNEL_PITCH_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_YAW] = SPEKTRUM_CHANNEL_YAW_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] = SPEKTRUM_CHANNEL_THROTTLE_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_GEAR] = SPEKTRUM_CHANNEL_GEAR_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_1] = SPEKTRUM_CHANNEL_AUX_1_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_2] = SPEKTRUM_CHANNEL_AUX_2_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_3] = SPEKTRUM_CHANNEL_AUX_3_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_4] = SPEKTRUM_CHANNEL_AUX_4_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_5] = SPEKTRUM_CHANNEL_AUX_5_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_6] = SPEKTRUM_CHANNEL_AUX_6_DEFAULT_VALUE;
        mChannelValue[SPEKTRUM_CHANNEL_AUX_7] = SPEKTRUM_CHANNEL_AUX_7_DEFAULT_VALUE;

        // min value
        mChannelMinValue[SPEKTRUM_CHANNEL_ROLL] = SPEKTRUM_CHANNEL_ROLL_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_PITCH] = SPEKTRUM_CHANNEL_PITCH_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_YAW] = SPEKTRUM_CHANNEL_YAW_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_THROTTLE] = SPEKTRUM_CHANNEL_THROTTLE_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_GEAR] = SPEKTRUM_CHANNEL_GEAR_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_1] = SPEKTRUM_CHANNEL_AUX_1_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_2] = SPEKTRUM_CHANNEL_AUX_2_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_3] = SPEKTRUM_CHANNEL_AUX_3_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_4] = SPEKTRUM_CHANNEL_AUX_4_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_5] = SPEKTRUM_CHANNEL_AUX_5_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_6] = SPEKTRUM_CHANNEL_AUX_6_MIN_VALUE;
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_7] = SPEKTRUM_CHANNEL_AUX_7_MIN_VALUE;

        // max value
        mChannelMaxValue[SPEKTRUM_CHANNEL_ROLL] = SPEKTRUM_CHANNEL_ROLL_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_PITCH] = SPEKTRUM_CHANNEL_PITCH_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_YAW] = SPEKTRUM_CHANNEL_YAW_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_THROTTLE] = SPEKTRUM_CHANNEL_THROTTLE_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_GEAR] = SPEKTRUM_CHANNEL_GEAR_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_1] = SPEKTRUM_CHANNEL_AUX_1_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_2] = SPEKTRUM_CHANNEL_AUX_2_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_3] = SPEKTRUM_CHANNEL_AUX_3_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_4] = SPEKTRUM_CHANNEL_AUX_4_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_5] = SPEKTRUM_CHANNEL_AUX_5_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_6] = SPEKTRUM_CHANNEL_AUX_6_MAX_VALUE;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_7] = SPEKTRUM_CHANNEL_AUX_7_MAX_VALUE;
    }

    // ****************************************************************************************** //
    //
    // int set_roll_value(int value)
    //
    // ****************************************************************************************** //
    public int set_roll_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_ROLL] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_ROLL] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_ROLL] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_pitch_value(int value)
    //
    // ****************************************************************************************** //
    public int set_pitch_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_PITCH] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_PITCH] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_PITCH] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_yaw_value(int value)
    //
    // ****************************************************************************************** //
    public int set_yaw_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_YAW] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_YAW] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_YAW] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_throttle_value(int value)
    //
    // ****************************************************************************************** //
    public int set_throttle_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_THROTTLE] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_THROTTLE] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_gear_value(int value)
    //
    // ****************************************************************************************** //
    public int set_gear_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_GEAR] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_GEAR] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_GEAR] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_1_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_1_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_1] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_1] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_1] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_2_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_2_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_2] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_2] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_2] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_3_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_3_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_3] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_3] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_3] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_4_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_4_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_4] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_4] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_4] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_5_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_5_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_5] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_5] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_5] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_6_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_6_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_6] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_6] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_6] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_aux_7_value(int value)
    //
    // ****************************************************************************************** //
    public int set_aux_7_value(int value)
    {
        if (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_7] > value)
        {
            return -1;
        }

        if (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_7] < value)
        {
            return -1;
        }
        mChannelValue[SPEKTRUM_CHANNEL_AUX_7] = value;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_roll_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_roll_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_ROLL] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_ROLL] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_pitch_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_pitch_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_PITCH] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_PITCH] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_yaw_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_yaw_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_YAW] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_YAW] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_throttle_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_throttle_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_THROTTLE] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_THROTTLE] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_gear_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_gear_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_GEAR] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_GEAR] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_1_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_1_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_1] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_1] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_2_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_2_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_2] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_2] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_3_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_3_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_3] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_3] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_4_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_4_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_4] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_4] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_5_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_5_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_5] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_5] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_6_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_6_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_6] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_6] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int set_range_aux_7_value(int min, int max)
    //
    // ****************************************************************************************** //
    public int set_range_aux_7_value(int min, int max)
    {
        if (min <= min )
        {
            return -1;
        }

        if (min < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || min > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        if (max < SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE || max > SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE)
        {
            return -1;
        }
        mChannelMinValue[SPEKTRUM_CHANNEL_AUX_7] = min;
        mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_7] = max;
        return 0;
    }

    // ****************************************************************************************** //
    //
    // int get_roll_value()
    //
    // ****************************************************************************************** //
    public int get_roll_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_ROLL]);
    }

    // ****************************************************************************************** //
    //
    // public int get_pitch_value()
    //
    // ****************************************************************************************** //
    public int get_pitch_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_PITCH]);
    }

    // ****************************************************************************************** //
    //
    // int get_yaw_value()
    //
    // ****************************************************************************************** //
    public int get_yaw_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_YAW]);
    }

    // ****************************************************************************************** //
    //
    // int get_throttle_value()
    //
    // ****************************************************************************************** //
    public int get_throttle_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_THROTTLE]);
    }

    // ****************************************************************************************** //
    //
    // int get_gear_value()
    //
    // ****************************************************************************************** //
    public int get_gear_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_GEAR]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_1_value()
    //
    // ****************************************************************************************** //
    public int get_aux_1_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_1]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_2_value()
    //
    // ****************************************************************************************** //
    public int get_aux_2_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_2]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_3_value()
    //
    // ****************************************************************************************** //
    public int get_aux_3_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_3]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_4_value()
    //
    // ****************************************************************************************** //
    public int get_aux_4_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_4]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_5_value()
    //
    // ****************************************************************************************** //
    public int get_aux_5_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_5]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_6_value()
    //
    // ****************************************************************************************** //
    public int get_aux_6_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_6]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_7_value()
    //
    // ****************************************************************************************** //
    public int get_aux_7_value()
    {
        return (mChannelValue[SPEKTRUM_CHANNEL_AUX_7]);
    }

    // ****************************************************************************************** //
    //
    // int get_roll_min_value()
    //
    // ****************************************************************************************** //
    public int get_roll_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_ROLL]);
    }

    // ****************************************************************************************** //
    //
    // int get_roll_max_value()
    //
    // ****************************************************************************************** //
    public int get_roll_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_ROLL]);
    }

    // ****************************************************************************************** //
    //
    // int get_pitch__min_value()
    //
    // ****************************************************************************************** //
    public int get_pitch_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_PITCH]);
    }

    // ****************************************************************************************** //
    //
    // int get_pitch__min_value()
    //
    // ****************************************************************************************** //
    public int get_pitch_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_PITCH]);
    }

    // ****************************************************************************************** //
    //
    // int get_yaw_min_value()
    //
    // ****************************************************************************************** //
    public int get_yaw_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_YAW]);
    }

    // ****************************************************************************************** //
    //
    // int get_yaw_min_value()
    //
    // ****************************************************************************************** //
    public int get_yaw_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_YAW]);
    }

    // ****************************************************************************************** //
    //
    // int get_throttle_min_value()
    //
    // ****************************************************************************************** //
    public int get_throttle_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_THROTTLE]);
    }

    // ****************************************************************************************** //
    //
    // int get_throttle_max_value()
    //
    // ****************************************************************************************** //
    public int get_throttle_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_THROTTLE]);
    }

    // ****************************************************************************************** //
    //
    // int get_gear_min_value()
    //
    // ****************************************************************************************** //
    public int get_gear_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_GEAR]);
    }

    // ****************************************************************************************** //
    //
    // int get_gear_max_value()
    //
    // ****************************************************************************************** //
    public int get_gear_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_GEAR]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_1_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_1_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_1]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_1_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_1_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_1]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_2_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_2_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_2]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_2_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_2_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_2]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_3_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_3_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_3]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_3_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_3_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_3]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_4_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_4_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_4]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_4_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_4_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_4]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_5_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_5_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_5]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_5_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_5_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_5]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_6_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_6_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_6]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_6_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_6_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_6]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_7_min_value()
    //
    // ****************************************************************************************** //
    public int get_aux_7_min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_AUX_7]);
    }

    // ****************************************************************************************** //
    //
    // int get_aux_7_max_value()
    //
    // ****************************************************************************************** //
    public int get_aux_7_max_value()
    {
        return (mChannelMaxValue[SPEKTRUM_CHANNEL_AUX_7]);
    }

}
