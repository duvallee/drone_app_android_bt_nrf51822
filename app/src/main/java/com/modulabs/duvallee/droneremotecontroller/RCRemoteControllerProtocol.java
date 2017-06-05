package com.modulabs.duvallee.droneremotecontroller;

/**
 * Created by duval on 2017-06-05.
 */

public class RCRemoteControllerProtocol {

    private static final int rc_position_min = 0;
    private static final int rc_position_max = 1023;

    private static final int throttle_default = 100;
    private static final int roll_default = 512;
    private static final int pitch_default = 512;
    private static final int yaw_default = 512;
    private static final int arming_default = 100;              // ADC 1
    private static final int arming_on = 800;                   // ADC 1

    private static final int position_move_veryvery_slow = 1;
    private static final int position_move_very_slow = 3;
    private static final int position_move_slow = 5;
    private static final int position_move_normal = 7;
    private static final int position_move_fast = 9;
    private static final int position_move_very_fast = 11;
    private static final int position_move_veryvery_fast = 13;

    public enum POSITION_LEVEL {
        enum_move_veryvery_slow,
        enum_move_very_slow,
        enum_move_slow,
        enum_move_normal,
        enum_move_fast,
        enum_move_very_fast,
        enum_move_veryvery_fast
    };

    private int throttle_position = throttle_default;
    private int roll_position = roll_default;
    private int pitch_position = pitch_default;
    private int yaw_position = yaw_default;
    private int arming_position = arming_default;                  // ADC 1
    private int adc2_position = arming_default;                    // ADC 2
    private int adc3_position = arming_default;                    // ADC 2

    private POSITION_LEVEL enum_position_move_level = POSITION_LEVEL.enum_move_veryvery_slow;
    private int position_move_value = position_move_veryvery_slow;

    private static final int PROTOCOL_BYTE = 16;
    private static final int CHANNEL_ID_SHIFT = 4;
    private static final int BYTE_SHIFT = 8;

    private static final int CHANNEL_ID_ROLL = 0;
    private static final int CHANNEL_ID_PITCH = 1;
    private static final int CHANNEL_ID_YAW = 2;
    private static final int CHANNEL_ID_THROTTLE = 3;
    private static final int CHANNEL_ID_ARMING = 4;
    private static final int CHANNEL_ID_ADC2 = 5;
    private static final int CHANNEL_ID_ADC3 = 6;

    // **************************************************************
    // Contructor
    //
    // **************************************************************
    public RCRemoteControllerProtocol() {

    }

    // **************************************************************
    // set default value ...
    //
    // **************************************************************
    public void setDefaultValue() {
        throttle_position = throttle_default;
        roll_position = roll_default;
        pitch_position = pitch_default;
        yaw_position = yaw_default;
        arming_position = arming_default;                  // ADC 1
        adc2_position = arming_default;                    // ADC 2
        adc3_position = arming_default;                    // ADC 2

        enum_position_move_level = POSITION_LEVEL.enum_move_veryvery_slow;
    }

    // **************************************************************
    // set throttle
    //
    // **************************************************************
    public int setThrottle(boolean inc) {
        if (inc) {
            throttle_position += position_move_value;
        }
        else {
            throttle_position -= position_move_value;
        }
        if (throttle_position < rc_position_min) {
            throttle_position = rc_position_min;
        }
        if (throttle_position > rc_position_max) {
            throttle_position = rc_position_max;
        }
        return throttle_position;
    }

    // **************************************************************
    // get throttle
    //
    // **************************************************************
    public int getThrottle() {
        return throttle_position;
    }

    // **************************************************************
    // set roll
    //
    // **************************************************************
    public int setRoll(boolean inc) {
        if (inc) {
            roll_position += position_move_value;
        }
        else {
            roll_position -= position_move_value;
        }
        if (roll_position < rc_position_min) {
            roll_position = rc_position_min;
        }
        if (roll_position > rc_position_max) {
            roll_position = rc_position_max;
        }
        return roll_position;
    }

    // **************************************************************
    // get roll
    //
    // **************************************************************
    public int getRoll() {
        return roll_position;
    }

    // **************************************************************
    // set pitch
    //
    // **************************************************************
    public int setPitch(boolean inc) {
        if (inc) {
            pitch_position += position_move_value;
        }
        else {
            pitch_position -= position_move_value;
        }
        if (pitch_position < rc_position_min) {
            pitch_position = rc_position_min;
        }
        if (pitch_position > rc_position_max) {
            pitch_position = rc_position_max;
        }
        return pitch_position;
    }

    // **************************************************************
    // get pitch
    //
    // **************************************************************
    public int getPitch() {
        return pitch_position;
    }

    // **************************************************************
    // set yaw
    //
    // **************************************************************
    public int setYaw(boolean inc) {
        if (inc) {
            yaw_position += position_move_value;
        }
        else {
            yaw_position -= position_move_value;
        }
        if (yaw_position < rc_position_min) {
            yaw_position = rc_position_min;
        }
        if (yaw_position > rc_position_max) {
            yaw_position = rc_position_max;
        }
        return yaw_position;
    }

    // **************************************************************
    // get yaw
    //
    // **************************************************************
    public int getYaw() {
        return yaw_position;
    }

    // **************************************************************
    // set arming
    //
    // **************************************************************
    public int setArming(boolean inc) {
        if (inc) {
            arming_position += position_move_value;
        }
        else {
            arming_position -= position_move_value;
        }
        if (arming_position < rc_position_min) {
            arming_position = rc_position_min;
        }
        if (arming_position > rc_position_max) {
            arming_position = rc_position_max;
        }
        return arming_position;
    }

    // **************************************************************
    // get arming
    //
    // **************************************************************
    public int getArming() {
        return arming_position;
    }

    // **************************************************************
    // PowerArming
    //
    // **************************************************************
    public void PowerArming(boolean on) {
        if (on) {
            arming_position = arming_on;
        }
        else {
            arming_position = arming_default;
        }
    }

    // **************************************************************
    // set adc2
    //
    // **************************************************************
    public int setADC2(boolean inc) {
        if (inc) {
            adc2_position += position_move_value;
        }
        else {
            adc2_position -= position_move_value;
        }
        if (adc2_position < rc_position_min) {
            adc2_position = rc_position_min;
        }
        if (adc2_position > rc_position_max) {
            adc2_position = rc_position_max;
        }
        return adc2_position;
    }

    // **************************************************************
    // get adc2
    //
    // **************************************************************
    public int getADC2() {
        return adc2_position;
    }

    // **************************************************************
    // set adc3
    //
    // **************************************************************
    public int setADC3(boolean inc) {
        if (inc) {
            adc3_position += position_move_value;
        }
        else {
            adc3_position -= position_move_value;
        }
        if (adc3_position < rc_position_min) {
            adc3_position = rc_position_min;
        }
        if (adc3_position > rc_position_max) {
            adc3_position = rc_position_max;
        }
        return adc3_position;
    }

    // **************************************************************
    // get adc3
    //
    // **************************************************************
    public int getADC3() {
        return adc3_position;
    }

    // **************************************************************
    // get move level
    //
    // **************************************************************
    public POSITION_LEVEL getMoveLevel() {
        return enum_position_move_level;
    }

    // **************************************************************
    // set move level
    //
    // **************************************************************
    public void setMoveLevel(POSITION_LEVEL level) {
        enum_position_move_level = level;
        switch (level) {
            case enum_move_veryvery_slow :
                position_move_value = position_move_veryvery_slow;
                break;

            case enum_move_very_slow :
                position_move_value = position_move_very_slow;
                break;

            case enum_move_slow :
                position_move_value = position_move_slow;
                break;

            case enum_move_normal :
                position_move_value = position_move_normal;
                break;

            case enum_move_fast :
                position_move_value = position_move_fast;
                break;

            case enum_move_very_fast :
                position_move_value = position_move_very_fast;
                break;

            case enum_move_veryvery_fast :
                position_move_value = position_move_veryvery_fast;
                break;
        }
    }

    // **************************************************************
    // getProtocolMessage
    //
    // **************************************************************

    // V 1.0
    private static final byte HEADER_0 = 0x31;                     // 1
    private static final byte HEADER_1 = 0x30;                      // 0

    // channel
    // 15  14  13  12  11  10  09  08  07  06  05  04  03  02  01  00
    // [ channel id ]  [ position value                              ]
    public byte[] getProtocolMessage() {
        byte[] protocol_msg = new byte[PROTOCOL_BYTE];

        // header
        protocol_msg[0] = HEADER_1;
        protocol_msg[1] = HEADER_0;

        // channel 0 : roll
        protocol_msg[2] = (byte) (roll_position & 0xFF);
        protocol_msg[3] = (byte) (((roll_position >> BYTE_SHIFT) | (CHANNEL_ID_ROLL << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 1 : pitch
        protocol_msg[4] = (byte) (pitch_position & 0xFF);
        protocol_msg[5] = (byte) (((pitch_position >> BYTE_SHIFT) | (CHANNEL_ID_PITCH << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 2 : yaw
        protocol_msg[6] = (byte) (yaw_position & 0xFF);
        protocol_msg[7] = (byte) (((yaw_position >> BYTE_SHIFT) | (CHANNEL_ID_YAW << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 3 : throttle
        protocol_msg[8] = (byte) (throttle_position & 0xFF);
        protocol_msg[9] = (byte) (((throttle_position >> BYTE_SHIFT) | (CHANNEL_ID_THROTTLE << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 4 : arming
        protocol_msg[10] = (byte) (arming_position & 0xFF);
        protocol_msg[11] = (byte) (((arming_position >> BYTE_SHIFT) | (CHANNEL_ID_ARMING << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 5 : adc2
        protocol_msg[12] = (byte) (adc2_position & 0xFF);
        protocol_msg[13] = (byte) (((adc2_position >> BYTE_SHIFT) | (CHANNEL_ID_ADC2 << CHANNEL_ID_SHIFT)) & 0xFF);

        // channel 6 : adc3
        protocol_msg[14] = (byte) (adc3_position & 0xFF);
        protocol_msg[15] = (byte) (((adc3_position >> BYTE_SHIFT) | (CHANNEL_ID_ADC3 << CHANNEL_ID_SHIFT)) & 0xFF);

        return protocol_msg;
    }
}
