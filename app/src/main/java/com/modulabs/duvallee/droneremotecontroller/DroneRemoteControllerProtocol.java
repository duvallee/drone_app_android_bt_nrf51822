package com.modulabs.duvallee.droneremotecontroller;

/**
 * Created by duval on 2017-07-02.
 */

public class DroneRemoteControllerProtocol extends Object
{
    public static final String TAG = "DroneRemoteControllerProtocol";

    // ---------------------------------------------------------------------------------------------
    // constant ...
    public final int PROTOCOL_HEADER_1_HIGH_BYTE_INDEX = 0;
    public final int PROTOCOL_HEADER_1_LOW_BYTE_INDEX = 1;

    public final int PROTOCOL_HEADER_COMMAND_INDEX = 2;
    public final int PROTOCOL_HEADER_SIZE_INDEX = 3;

    public final int PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX = 4;
    public final int PROTOCOL_CHANEL_1_LOW_BYTE_INDEX = 5;

    public final int PROTOCOL_CHANEL_2_HIGH_BYTE_INDEX = 6;
    public final int PROTOCOL_CHANEL_2_LOW_BYTE_INDEX = 7;

    public final int PROTOCOL_CHANEL_3_HIGH_BYTE_INDEX = 8;
    public final int PROTOCOL_CHANEL_3_LOW_BYTE_INDEX = 9;

    public final int PROTOCOL_CHANEL_4_HIGH_BYTE_INDEX = 10;
    public final int PROTOCOL_CHANEL_4_LOW_BYTE_INDEX = 11;

    public final int PROTOCOL_CHANEL_5_HIGH_BYTE_INDEX = 12;
    public final int PROTOCOL_CHANEL_5_LOW_BYTE_INDEX = 13;

    public final int PROTOCOL_CHANEL_6_HIGH_BYTE_INDEX = 14;
    public final int PROTOCOL_CHANEL_6_LOW_BYTE_INDEX = 15;

    public final int PROTOCOL_CHANEL_7_HIGH_BYTE_INDEX = 16;
    public final int PROTOCOL_CHANEL_7_LOW_BYTE_INDEX = 17;

    public final int PROTOCOL_CHANEL_8_HIGH_BYTE_INDEX = 18;
    public final int PROTOCOL_CHANEL_8_LOW_BYTE_INDEX = 19;

    public final int PROTOCOL_CHANEL_9_HIGH_BYTE_INDEX = 20;
    public final int PROTOCOL_CHANEL_9_LOW_BYTE_INDEX = 21;

    public final int PROTOCOL_CHANEL_10_HIGH_BYTE_INDEX = 22;
    public final int PROTOCOL_CHANEL_10_LOW_BYTE_INDEX = 23;

    public final int PROTOCOL_CHANEL_11_HIGH_BYTE_INDEX = 24;
    public final int PROTOCOL_CHANEL_11_LOW_BYTE_INDEX = 25;

    public final int PROTOCOL_CHANEL_12_HIGH_BYTE_INDEX = 26;
    public final int PROTOCOL_CHANEL_12_LOW_BYTE_INDEX = 27;

    public final int PROTOCOL_CRC_HIGH_BYTE_INDEX = 28;
    public final int PROTOCOL_CRC_LOW_BYTE_INDEX = 29;

    public final int PROTOCOL_MAX_INDEX = 30;

    // ---------------------------------------------------------------------------------------------
    // channel id (constant)
    public final int SPEKTRUM_CHANNEL_ROLL = 0;
    public final int SPEKTRUM_CHANNEL_PITCH = 1;
    public final int SPEKTRUM_CHANNEL_YAW = 2;
    public final int SPEKTRUM_CHANNEL_THROTTLE = 3;
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
    public final int SPEKTRUM_CHANNEL_ROLL_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_ROLL_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_ROLL_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_PITCH_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_PITCH_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_PITCH_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_YAW_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_YAW_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_YAW_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_THROTTLE_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_THROTTLE_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_THROTTLE_DEFAULT_VALUE = 500;

    public final int SPEKTRUM_CHANNEL_GEAR_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_GEAR_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_GEAR_DEFAULT_VALUE= 768;

    public final int SPEKTRUM_CHANNEL_AUX_1_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_1_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_1_DEFAULT_VALUE = 500;

    public final int SPEKTRUM_CHANNEL_AUX_2_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_2_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_2_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_AUX_3_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_3_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_3_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_AUX_4_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_4_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_4_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_AUX_5_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_5_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_5_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_AUX_6_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_6_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_6_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_AUX_7_MIN_VALUE = 500;
    public final int SPEKTRUM_CHANNEL_AUX_7_MAX_VALUE = 1000;
    public final int SPEKTRUM_CHANNEL_AUX_7_DEFAULT_VALUE = 768;

    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE = 100;
    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE = 1023;

    // header
    public final int PROTOCOL_HEADER_HIGH_VERSION = 0x01;
    public final int PROTOCOL_HEADER_LOW_VERSION = 0x00;

    // Command
    public final int PROTOCOL_SEND_TO_TRANSMITTER = 0x01;
    public final int PROTOCOL_RESPONSE_FROM_TRANSMITTER = 0x02;
    public final int PROTOCOL_ALIVE_FROM_TRANSMITTER = 0x03;

    // ---------------------------------------------------------------------------------------------
    // members
    private MainRemoteControllerActivity mParent = null;

    private int[] mChannelValue;
    private int[] mChannelMinValue;
    private int[] mChannelMaxValue;

    // ---------------------------------------------------------------------------------------------
    // members

    // test function
    public int roll_position = 1000;
    public int pitch_position = 1000;
    public int yaw_position = 1000;
    public int throttle_position = 1000;
    public int arming_position = 1000;
    public void SendBTMessage()
    {
        byte[] value = new byte[16];

        roll_position++;
        pitch_position++;
        yaw_position++;
        throttle_position++;
        arming_position++;

        if (roll_position > 2000)
        {
            roll_position = 1000;
        }
        if (pitch_position > 2000)
        {
            pitch_position = 1000;
        }
        if (yaw_position > 2000)
        {
            yaw_position = 1000;
        }
        if (throttle_position > 2000)
        {
            throttle_position = 1000;
        }
        if (arming_position > 2000)
        {
            arming_position = 1000;
        }

        //send data to service
        value[0] = (byte) 0x23;
        value[1] = (byte) 0x92;

        // roll
        value[2] = (byte) (roll_position >> 8);
        value[3] = (byte) (roll_position);

        // pitch
        value[4] = (byte) (pitch_position >> 8);
        value[5] = (byte) (pitch_position);

        // yaw_position
        value[6] = (byte) (yaw_position >> 8);
        value[7] = (byte) (yaw_position);

        // throttle_position
        value[8] = (byte) (throttle_position >> 8);
        value[9] = (byte) (throttle_position);

        // arming
        value[10] = (byte) (arming_position >> 8);
        value[11] = (byte) (arming_position);

        // 00
        value[12] = (byte) (0x00);
        value[13] = (byte) (0x00);

        // 00
        value[14] = (byte) (0x00);
        value[15] = (byte) (0x00);

//        mDroneTransmitterBtService.writeRXCharacteristic(value);
    };

    // ****************************************************************************************** //
    //
    // byte[] getProtocolData()
    //
    // ****************************************************************************************** //
    public byte[] getProtocolData()
    {
        byte[] protocol_data = new byte[PROTOCOL_MAX_INDEX];

        protocol_data[PROTOCOL_HEADER_1_HIGH_BYTE_INDEX] = PROTOCOL_HEADER_HIGH_VERSION;
        protocol_data[PROTOCOL_HEADER_1_LOW_BYTE_INDEX] = PROTOCOL_HEADER_LOW_VERSION;

        protocol_data[PROTOCOL_HEADER_COMMAND_INDEX] = PROTOCOL_SEND_TO_TRANSMITTER;
        protocol_data[PROTOCOL_HEADER_SIZE_INDEX] = PROTOCOL_MAX_INDEX;

        // Channel : ROLL : 0
        protocol_data[PROTOCOL_CHANEL_1_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_ROLL << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_ROLL] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_1_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_ROLL] & 0xFF);

        // Channel : PITCH : 1
        protocol_data[PROTOCOL_CHANEL_2_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_PITCH << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_PITCH] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_2_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_PITCH] & 0xFF);

        // Channel : YAW : 2
        protocol_data[PROTOCOL_CHANEL_3_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_YAW << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_YAW] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_3_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_YAW] & 0xFF);

        // Channel : THROTTLE : 3
        protocol_data[PROTOCOL_CHANEL_4_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_THROTTLE << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_4_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] & 0xFF);

        // Channel : GEAR : 4
        protocol_data[PROTOCOL_CHANEL_5_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_GEAR << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_GEAR] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_5_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_GEAR] & 0xFF);

        // Channel : AUX 1 : 5
        protocol_data[PROTOCOL_CHANEL_6_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_1 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_1] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_6_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_1] & 0xFF);

        // Channel : AUX 2 : 6
        protocol_data[PROTOCOL_CHANEL_7_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_2 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_2] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_7_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_2] & 0xFF);

        // Channel : AUX 3 : 7
        protocol_data[PROTOCOL_CHANEL_8_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_3 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_3] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_8_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_3] & 0xFF);

        // Channel : AUX 4 : 8
        protocol_data[PROTOCOL_CHANEL_9_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_4 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_4] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_9_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_4] & 0xFF);

        // Channel : AUX 5 : 9
        protocol_data[PROTOCOL_CHANEL_10_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_5 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_5] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_10_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_5] & 0xFF);

        // Channel : AUX 6 : 10
        protocol_data[PROTOCOL_CHANEL_11_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_6 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_6] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_11_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_6] & 0xFF);

        // Channel : AUX 7 : 11
        protocol_data[PROTOCOL_CHANEL_12_HIGH_BYTE_INDEX] = (byte) ((SPEKTRUM_CHANNEL_AUX_7 << 4) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_7] >> 8) & 0xF));
        protocol_data[PROTOCOL_CHANEL_12_LOW_BYTE_INDEX] = (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_7] & 0xFF);

        int crc = 0;
        for (int i = 0; i < (PROTOCOL_CHANEL_12_LOW_BYTE_INDEX + 1); i++)
        {
            crc += protocol_data[i];
            crc = (crc & 0xFFFF);
        }
        protocol_data[PROTOCOL_CRC_HIGH_BYTE_INDEX] = (byte) ((crc >> 8) & 0xFF);
        protocol_data[PROTOCOL_CRC_LOW_BYTE_INDEX] = (byte) (crc & 0xFF);

        return protocol_data;
    }

    // ****************************************************************************************** //
    //
    // byte[] getProtocolData()
    //
    // ****************************************************************************************** //
    public void responseProtocolData(byte[] data)
    {

    }


    // ****************************************************************************************** //
    //
    // int getProtocolbytelength()
    //
    // ****************************************************************************************** //
    public int getProtocolbytelength()
    {
        return (PROTOCOL_MAX_INDEX);
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
    public int get_pitch__min_value()
    {
        return (mChannelMinValue[SPEKTRUM_CHANNEL_PITCH]);
    }

    // ****************************************************************************************** //
    //
    // int get_pitch__min_value()
    //
    // ****************************************************************************************** //
    public int get_pitch__max_value()
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
