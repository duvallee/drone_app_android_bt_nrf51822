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

    public class PacketInfo
    {
        public final static byte PACKET_HALF_SIZE = 16;
        public final static byte BASIC_PACKET_SIZE = 8;
        public final static byte CHANNEL_PACKET_SIZE = 32;

        public final static int PACKET_FREE = 0;
        public final static int PACKET_READY = 1;
        public final static int PACKET_HALF_SEND = 2;
        public final static int PACKET_WAIT_RESPONSE = 3;

        public int m_paket_length = 0;
        public int m_packet_status = 0;

        public int m_packet_response_timeout = 0;

        private byte[] m_basic_paket_byte;

        private byte[] m_low_paket_byte;
        private byte[] m_high_paket_byte;

        // ****************************************************************************************** //
        //
        // contructor
        //
        // ****************************************************************************************** //
        PacketInfo()
        {
            m_basic_paket_byte = new byte[BASIC_PACKET_SIZE];
            m_low_paket_byte = new byte[PACKET_HALF_SIZE];
            m_high_paket_byte = new byte[PACKET_HALF_SIZE];
            clean();
        }

        // ****************************************************************************************** //
        //
        // contructor
        //
        // ****************************************************************************************** //
        void clean()
        {
            m_paket_length = 0;
            m_packet_status = PACKET_FREE;
            m_packet_response_timeout = 0;

            for (int i = 0; i < BASIC_PACKET_SIZE; i++)
            {
                m_basic_paket_byte[i] = 0;
            }

            for (int i = 0; i < PACKET_HALF_SIZE; i++)
            {
                m_low_paket_byte[i] = 0;
                m_high_paket_byte[i] = 0;
            }
        }

        // ****************************************************************************************** //
        //
        // MakeBasicPacket
        //
        // ****************************************************************************************** //
        void MakeBasicPacket(byte header_1_hi_byte, byte header_1_low_byte,
                             byte header_command_byte, byte header_size_byte,
                             byte option_1_hi_byte, byte option_1_low_byte,
                             byte option_2_hi_byte, byte option_2_low_byte)
        {
            m_paket_length = BASIC_PACKET_SIZE;
            m_packet_status = PACKET_READY;
            m_packet_response_timeout = 0;

            m_basic_paket_byte[0] = header_1_hi_byte;
            m_basic_paket_byte[1] = header_1_low_byte;
            m_basic_paket_byte[2] = header_command_byte;
            m_basic_paket_byte[3] = header_size_byte;
            m_basic_paket_byte[4] = option_1_hi_byte;
            m_basic_paket_byte[5] = option_1_low_byte;
            m_basic_paket_byte[6] = option_2_hi_byte;
            m_basic_paket_byte[7] = option_2_low_byte;
        }

        // ****************************************************************************************** //
        //
        // MakeChannelPacket
        //
        // ****************************************************************************************** //
        void MakeChannelPacket(byte header_1_hi_byte, byte header_1_low_byte,
                             byte header_command_byte, byte header_size_byte,
                             byte option_1_hi, byte option_1_low,
                             byte option_2_hi, byte option_2_low,
                             byte ch_0_hi, byte ch_0_low,
                             byte ch_1_hi, byte ch_1_low,
                             byte ch_2_hi, byte ch_2_low,
                             byte ch_3_hi, byte ch_3_low,
                             byte ch_4_hi, byte ch_4_low,
                             byte ch_5_hi, byte ch_5_low,
                             byte ch_6_hi, byte ch_6_low,
                             byte ch_7_hi, byte ch_7_low,
                             byte ch_8_hi, byte ch_8_low,
                             byte ch_9_hi, byte ch_9_low,
                             byte ch_10_hi, byte ch_10_low,
                             byte ch_11_hi, byte ch_11_low)
        {
            m_paket_length = CHANNEL_PACKET_SIZE;
            m_packet_status = PACKET_READY;
            m_packet_response_timeout = 0;

            m_low_paket_byte[0] = header_1_hi_byte;
            m_low_paket_byte[1] = header_1_low_byte;
            m_low_paket_byte[2] = header_command_byte;
            m_low_paket_byte[3] = header_size_byte;
            m_low_paket_byte[4] = 0;    // crc
            m_low_paket_byte[5] = 0;    // crc
            m_low_paket_byte[6] = 0;
            m_low_paket_byte[7] = 0;
            m_low_paket_byte[8] = ch_0_hi;
            m_low_paket_byte[9] = ch_0_low;
            m_low_paket_byte[10] = ch_1_hi;
            m_low_paket_byte[11] = ch_1_low;
            m_low_paket_byte[12] = ch_2_hi;
            m_low_paket_byte[13] = ch_2_low;
            m_low_paket_byte[14] = ch_3_hi;
            m_low_paket_byte[15] = ch_3_low;

            m_high_paket_byte[0] = ch_4_hi;
            m_high_paket_byte[1] = ch_4_low;
            m_high_paket_byte[2] = ch_5_hi;
            m_high_paket_byte[3] = ch_5_low;
            m_high_paket_byte[4] = ch_6_hi;
            m_high_paket_byte[5] = ch_6_low;
            m_high_paket_byte[6] = ch_7_hi;
            m_high_paket_byte[7] = ch_7_low;
            m_high_paket_byte[8] = ch_8_hi;
            m_high_paket_byte[9] = ch_8_low;
            m_high_paket_byte[10] = ch_9_hi;
            m_high_paket_byte[11] = ch_9_low;
            m_high_paket_byte[12] = ch_10_hi;
            m_high_paket_byte[13] = ch_10_low;
            m_high_paket_byte[14] = ch_11_hi;
            m_high_paket_byte[15] = ch_11_low;


            int crc = 0;
            for (int i = 8; i < PACKET_HALF_SIZE; i++)
            {
                crc += m_low_paket_byte[i];
                crc = (crc & 0xFFFF);
            }

            for (int i = 0; i < PACKET_HALF_SIZE; i++)
            {
                crc += m_high_paket_byte[i];
                crc = (crc & 0xFFFF);
            }
            m_low_paket_byte[4] = (byte) ((crc >> 8) & 0xFF);
            m_low_paket_byte[5] = (byte) (crc & 0xFF);
        }

    };

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
    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE = 0;
    public final int SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE = 1023;
    public final int SPEKTRUM_CHANNEL_CENTER_VALUE = 512;

    // min, max, default (constant)
    public final int SPEKTRUM_CHANNEL_ROLL_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_ROLL_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_ROLL_DEFAULT_VALUE = SPEKTRUM_CHANNEL_CENTER_VALUE;

    public final int SPEKTRUM_CHANNEL_PITCH_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_PITCH_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_PITCH_DEFAULT_VALUE = SPEKTRUM_CHANNEL_CENTER_VALUE;

    public final int SPEKTRUM_CHANNEL_YAW_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_YAW_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_YAW_DEFAULT_VALUE = SPEKTRUM_CHANNEL_CENTER_VALUE;

    public final int SPEKTRUM_CHANNEL_THROTTLE_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_THROTTLE_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_THROTTLE_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_GEAR_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_GEAR_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_GEAR_DEFAULT_VALUE= SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_1_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_1_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_1_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_2_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_2_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_2_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_3_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_3_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_3_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_4_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_4_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_4_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_5_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_5_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_5_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_6_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_6_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_6_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    public final int SPEKTRUM_CHANNEL_AUX_7_MIN_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_7_MAX_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE;
    public final int SPEKTRUM_CHANNEL_AUX_7_DEFAULT_VALUE = SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE;

    // ---------------------------------------------------------------------------------------------
    // Packet Sync Byte
    public final byte PROTOCOL_HEADER_HIGH_SYNC = (byte) 0xD7;
    public final byte PROTOCOL_HEADER_LOW_SYNC = (byte) 0x5E;

    // Command (byte : 4 bit (phone -> transmitter : 0x0?, transmitter -> phone : 0xF?)
    public final byte PROTOCOL_REGISTER_MESSAGE = (byte) 0x01;
    public final byte PROTOCOL_REGISTER_RESPONSE = (byte) 0xF1;
    public final byte PROTOCOL_ALIVE_MESSAGE = (byte) 0x02;
    public final byte PROTOCOL_ALIVE_RESPONSE = (byte) 0xF2;
    public final byte PROTOCOL_CHANNEL_MESSAGE = (byte) 0x03;
    public final byte PROTOCOL_CHANNEL_RESPONSE = (byte) 0xF3;

    public final int PROTOCOL_CHANNEL_SHIFT = 4;

    // ---------------------------------------------------------------------------------------------
    // members
    private MainRemoteControllerActivity mParent = null;

    private int[] mChannelValue;
    private int[] mChannelMinValue;
    private int[] mChannelMaxValue;

    // buffer for packet
    private final static int MAX_PACKET_INFO_COUNT = 32;
    private PacketInfo[] mPacketInfo;
    private int mPacketInfo_fi_index = 0;           // first in index
    private int mPacketInfo_response_index = 0;     // index of wait for reponse
    private int mPacketInfo_fo_index = 0;           // first out index
    private int mPacketInfo_free_size = MAX_PACKET_INFO_COUNT;

//    private final static int RESPONSE_TIME_OUT = 60;      // 3 second
    private final static int RESPONSE_TIME_OUT = 600;       // 3 second
    private int m_Timer_duration = 100;                     // 200 ms

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

        if (mPacketInfo_free_size == 0)
        {
            return -1;
        }

        if (mPacketInfo[mPacketInfo_fi_index].m_packet_status == PacketInfo.PACKET_FREE)
        {
            mPacketInfo[mPacketInfo_fi_index].MakeBasicPacket(PROTOCOL_HEADER_HIGH_SYNC, PROTOCOL_HEADER_LOW_SYNC,
                    PROTOCOL_REGISTER_MESSAGE, PacketInfo.BASIC_PACKET_SIZE,
                    (byte) 0, (byte) 0,
                    (byte) 0, (byte) 0);
            mPacketInfo_free_size--;
            mPacketInfo_fi_index++;
            mPacketInfo_fi_index %= MAX_PACKET_INFO_COUNT;
            return 0;
        }
        else
        {
            Toast.makeText(mParent, "Send_Register_Message() index error", Toast.LENGTH_SHORT).show();
        }
        return -1;
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

        if (mPacketInfo_free_size == 0)
        {
            return -1;
        }

        if (mPacketInfo[mPacketInfo_fi_index].m_packet_status == PacketInfo.PACKET_FREE)
        {
            mPacketInfo[mPacketInfo_fi_index].MakeBasicPacket(PROTOCOL_HEADER_HIGH_SYNC, PROTOCOL_HEADER_LOW_SYNC,
                    PROTOCOL_ALIVE_MESSAGE, PacketInfo.BASIC_PACKET_SIZE,
                    (byte) ((alive_count >> 24) & 0xFF), (byte) ((alive_count >> 16) & 0xFF),
                    (byte) ((alive_count >> 8) & 0xFF), (byte) (alive_count & 0xFF));
            mPacketInfo_free_size--;
            mPacketInfo_fi_index++;
            mPacketInfo_fi_index %= MAX_PACKET_INFO_COUNT;
            return 0;
        }
        else
        {
            Toast.makeText(mParent, "Send_Register_Message() index error", Toast.LENGTH_SHORT).show();
        }
        return -1;
    }

    // ****************************************************************************************** //
    //
    // int Send_Channel_Message(UartService uartservice)
    //
    // ****************************************************************************************** //
    public int Send_Channel_Message(UartService uartservice)
    {
        if (uartservice == null)
        {
            return -1;
        }

        if (mPacketInfo_free_size == 0)
        {
            return -1;
        }

        if (mPacketInfo[mPacketInfo_fi_index].m_packet_status == PacketInfo.PACKET_FREE)
        {
            mPacketInfo[mPacketInfo_fi_index].MakeChannelPacket(PROTOCOL_HEADER_HIGH_SYNC, PROTOCOL_HEADER_LOW_SYNC,
                    PROTOCOL_CHANNEL_MESSAGE, PacketInfo.CHANNEL_PACKET_SIZE,
                    (byte) 0, (byte) 0,
                    (byte) 0, (byte) 0,
                    (byte) ((SPEKTRUM_CHANNEL_ROLL << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_ROLL] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_ROLL] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_PITCH << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_PITCH] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_PITCH] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_YAW << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_YAW] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_YAW] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_THROTTLE << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_THROTTLE] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_GEAR << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_GEAR] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_GEAR] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_1 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_1] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_1] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_2 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_2] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_2] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_3 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_3] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_3] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_4 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_4] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_4] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_5 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_5] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_5] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_6 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_6] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_6] & 0xFF),
                    (byte) ((SPEKTRUM_CHANNEL_AUX_7 << PROTOCOL_CHANNEL_SHIFT) | ((mChannelValue[SPEKTRUM_CHANNEL_AUX_7] >> 8) & 0xF)),
                    (byte) (mChannelValue[SPEKTRUM_CHANNEL_AUX_7] & 0xFF));
            mPacketInfo_free_size--;
            mPacketInfo_fi_index++;
            mPacketInfo_fi_index %= MAX_PACKET_INFO_COUNT;
            return 0;
        }
        else
        {
            Toast.makeText(mParent, "Send_Register_Message() index error", Toast.LENGTH_SHORT).show();
        }
        return -1;
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
            UartService uart_service = mParent.getUartService();;
            if (uart_service == null)
            {
                m_Send_Packet_Handler.sendEmptyMessageDelayed(0, m_Timer_duration);
                return;
            }

            if (mPacketInfo[mPacketInfo_fo_index].m_packet_status == PacketInfo.PACKET_READY)
            {
                if (mPacketInfo[mPacketInfo_fo_index].m_paket_length == PacketInfo.BASIC_PACKET_SIZE)
                {
                    // send packet for register & alive
                    uart_service.writeRXCharacteristic(mPacketInfo[mPacketInfo_fo_index].m_basic_paket_byte);
                    mPacketInfo[mPacketInfo_fo_index].m_packet_status = PacketInfo.PACKET_WAIT_RESPONSE;
                }
                else if (mPacketInfo[mPacketInfo_fo_index].m_paket_length == PacketInfo.CHANNEL_PACKET_SIZE)
                {
                    // send packet for low of channel
                    uart_service.writeRXCharacteristic(mPacketInfo[mPacketInfo_fo_index].m_low_paket_byte);
                    mPacketInfo[mPacketInfo_fo_index].m_packet_status = PacketInfo.PACKET_HALF_SEND;
                }
                else
                {
                    Toast.makeText(mParent, "internel error : unknown packet size", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else if (mPacketInfo[mPacketInfo_fo_index].m_packet_status == PacketInfo.PACKET_HALF_SEND)
            {
                // send packet for high of channel
                uart_service.writeRXCharacteristic(mPacketInfo[mPacketInfo_fo_index].m_high_paket_byte);
                mPacketInfo[mPacketInfo_fo_index].m_packet_status = PacketInfo.PACKET_WAIT_RESPONSE;
            }
            else if (mPacketInfo[mPacketInfo_fo_index].m_packet_status == PacketInfo.PACKET_WAIT_RESPONSE)
            {
                mPacketInfo[mPacketInfo_fo_index].m_packet_response_timeout++;
                if (mPacketInfo[mPacketInfo_fo_index].m_packet_response_timeout > RESPONSE_TIME_OUT)
                {
                    Toast.makeText(mParent, "internel error : time-out", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            m_Send_Packet_Handler.sendEmptyMessageDelayed(0, m_Timer_duration);
        }
    };

    // ****************************************************************************************** //
    //
    // int Response_Message(byte[] response_data)
    //
    // ****************************************************************************************** //
    public int Response_Message(byte[] response_data)
    {
        if (mPacketInfo[mPacketInfo_fo_index].m_packet_status == PacketInfo.PACKET_WAIT_RESPONSE)
        {
            mPacketInfo[mPacketInfo_fo_index].clean();
            mPacketInfo_free_size++;
            mPacketInfo_fo_index++;
            mPacketInfo_fo_index %= MAX_PACKET_INFO_COUNT;
        }
        return 0;
    }

    // ****************************************************************************************** //
    //
    // void init_fifo()
    //
    // ****************************************************************************************** //
    public void init_fifo()
    {
        for (int i = 0; i < MAX_PACKET_INFO_COUNT; i++)
        {
            mPacketInfo[i].clean();
        }

        mPacketInfo_fi_index = 0;           // first in index
        mPacketInfo_response_index = 0;     // index of wait for reponse
        mPacketInfo_fo_index = 0;           // first out index
        mPacketInfo_free_size = MAX_PACKET_INFO_COUNT;
    }

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

        mPacketInfo = new PacketInfo[MAX_PACKET_INFO_COUNT];
        for (int i = 0; i < MAX_PACKET_INFO_COUNT; i++)
        {
            mPacketInfo[i] = new PacketInfo();
        }

        mPacketInfo_fi_index = 0;           // first in index
        mPacketInfo_response_index = 0;     // index of wait for reponse
        mPacketInfo_fo_index = 0;           // first out index
        mPacketInfo_free_size = MAX_PACKET_INFO_COUNT;

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

        m_Send_Packet_Handler.sendEmptyMessageDelayed(0, m_Timer_duration);
    }

   // ****************************************************************************************** //
   //
   // void reset_all()
   //
   // ****************************************************************************************** //
   public void reset_all()
   {
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
   }

    // ****************************************************************************************** //
    //
    // int get_absolute_min_value()
    //
    // ****************************************************************************************** //
    public int get_absolute_min_value()
    {
        return (SPEKTRUM_CHANNEL_ABSOLUTE_MIN_VALUE);
    }

    // ****************************************************************************************** //
    //
    // int get_absolute_max_value()
    //
    // ****************************************************************************************** //
    public int get_absolute_max_value()
    {
        return (SPEKTRUM_CHANNEL_ABSOLUTE_MAX_VALUE);
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
