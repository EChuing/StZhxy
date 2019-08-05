package com.fangzhizun.cowxgzh.util;

/**
 * 空调控制码
 * type 设备类型
 * code 码库编号
 * isOn 开关
 * temp 温度范围 16-30
 * mode 模式
 * speed 风速
 * codeType 功能
 *
 * <p> 模式
 * int Mode_Auto = 0;//自动
 * int Mode_Cold = 1;//制冷
 * int Mode_Un_Wet = 2;//除湿
 * int Mode_Wind = 3;//送风
 * int Mode_Hot = 4;//制热
 * <p> 风速
 * int Speed_Auto = 0;//自动
 * int Speed_Low = 1;//低
 * int Speed_Middle = 2;//中
 * int Speed_Hight = 3;//高
 * <p> 功能
 * int AirISON = 0;//电源
 * int AirMODE = 1;//模式
 * int AirSPEED = 2;//风速
 * int AirTEMP = 3;//温度
 * int AirTEMPPLUS = 4;//温度+
 * int AirTEMPREDUCE = 5;//温度-
 * int AirAUTOADIRECT = 6;//自动扫风
 * int AirMANUALADIRECT = 7;//手动扫风
 * int AirSWEEPAUTO = 8;//扫风自动
 * int AirSWEEPAROUND = 9;//扫风左右
 */

public class AirConvert {

    public String getState(int type, int code, Boolean isOn, int temp, int mode, int speed, int codeType) {

        StringBuilder stringBuilder = new StringBuilder();

        switch (type) {
            case 0x05:
                stringBuilder.append("00");
                String group_05 = decimal2hex(code, 4);
                stringBuilder.append(group_05.substring(2, 4));
                stringBuilder.append(group_05.substring(0, 2));

                switch (mode) {
                    //自动
                    case 0:
                        stringBuilder.append("00");
                        break;
                    //制冷
                    case 1:
                        stringBuilder.append("01");
                        break;
                    //除湿
                    case 2:
                        stringBuilder.append("02");
                        break;
                    //送风
                    case 3:
                        stringBuilder.append("03");
                        break;
                    //制热
                    case 4:
                        stringBuilder.append("04");
                        break;
                }

                //温度
                stringBuilder.append(decimal2hex(temp - 16, 2));

                switch (speed) {
                    //自动风
                    case 0:
                        stringBuilder.append("00");
                        break;
                    //低风
                    case 1:
                        stringBuilder.append("01");
                        break;
                    //中风
                    case 2:
                        stringBuilder.append("02");
                        break;
                    //高风
                    case 3:
                        stringBuilder.append("03");
                        break;
                }

                stringBuilder.append("00");
                stringBuilder.append("01");
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");

                switch (codeType) {
                    //电源
                    case 1:
                        stringBuilder.append("00");
                        break;
                    //模式
                    case 2:
                        stringBuilder.append("03");
                        break;
                    //风速
                    case 3:
                        stringBuilder.append("04");
                        break;
                    //温度+
                    case 4:
                        stringBuilder.append("01");
                        break;
                    //温度-
                    case 5:
                        stringBuilder.append("02");
                        break;
                    //自动扫风
                    case 6:
                        stringBuilder.append("05");
                        break;
                    //手动扫风
                    case 7:
                        stringBuilder.append("06");
                        break;
                    //默认
                    default:
                        stringBuilder.append("00");
                        break;
                }

                if (isOn) {
                    stringBuilder.append("21");
                } else {
                    stringBuilder.append("20");
                }
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");
                break;

            case 0x0A:
                stringBuilder.append("00");
                String group_0A = decimal2hex(code, 4);
                stringBuilder.append(group_0A.substring(2, 4));
                stringBuilder.append(group_0A.substring(0, 2));

                switch (mode) {
                    //自动
                    case 0:
                        stringBuilder.append("00");
                        break;
                    //制冷
                    case 1:
                        stringBuilder.append("01");
                        break;
                    //除湿
                    case 2:
                        stringBuilder.append("02");
                        break;
                    //送风
                    case 3:
                        stringBuilder.append("03");
                        break;
                    //制热
                    case 4:
                        stringBuilder.append("04");
                        break;
                }

                //温度
                stringBuilder.append(decimal2hex(temp - 16, 2));

                switch (speed) {
                    //自动风
                    case 0:
                        stringBuilder.append("00");
                        break;
                    //低风
                    case 1:
                        stringBuilder.append("01");
                        break;
                    //中风
                    case 2:
                        stringBuilder.append("02");
                        break;
                    //高风
                    case 3:
                        stringBuilder.append("03");
                        break;
                }

                stringBuilder.append("00");
                stringBuilder.append("01");
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");

                switch (codeType) {
                    //电源
                    case 1:
                        stringBuilder.append("00");
                        break;
                    //模式
                    case 2:
                        stringBuilder.append("03");
                        break;
                    //风速
                    case 3:
                        stringBuilder.append("04");
                        break;
                    //温度+
                    case 4:
                        stringBuilder.append("01");
                        break;
                    //温度-
                    case 5:
                        stringBuilder.append("02");
                        break;
                    //自动扫风
                    case 6:
                        stringBuilder.append("05");
                        break;
                    //手动扫风
                    case 7:
                        stringBuilder.append("06");
                        break;
                    //默认
                    default:
                        stringBuilder.append("00");
                        break;
                }

                if (isOn) {
                    stringBuilder.append("21");
                } else {
                    stringBuilder.append("20");
                }
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("00");
                stringBuilder.append("0000000000000000000000");
                break;

            case 0x2A:
                //子类型
                stringBuilder.append("0001");
                //室温
                stringBuilder.append("69");
                //码组号
                String c0 = Integer.toHexString(code);
                StringBuffer str_code = new StringBuffer();

                if (c0.length() == 1) {
                    str_code.append("000");
                    str_code.append(c0);
                } else if (c0.length() == 2) {
                    str_code.append("00");
                    str_code.append(c0);
                } else if (c0.length() == 3) {
                    str_code.append("0");
                    str_code.append(c0);
                } else {
                    str_code.append(c0);
                }

                stringBuilder.append(str_code.substring(2, 4) + str_code.substring(0, 2));

                switch (codeType) {
                    //电源
                    case 1:
                        stringBuilder.append("01");
                        break;
                    //模式
                    case 2:
                        stringBuilder.append("02");
                        break;
                    //风速
                    case 3:
                        stringBuilder.append("03");
                        break;
                    //温度+
                    case 4:
                        stringBuilder.append("04");
                        break;
                    //温度-
                    case 5:
                        stringBuilder.append("05");
                        break;
                    //扫风自动
                    case 6:
                        stringBuilder.append("06");
                        break;
                    //扫风左右
                    case 7:
                        stringBuilder.append("08");
                        break;
                    //默认
                    default:
                        stringBuilder.append("01");
                        break;
                }

                //参数 温度 开机 模式
                StringBuffer parm0 = new StringBuffer("00000000");
                parm0.replace(0, 4, decimal2binaryString(temp - 16, 4));

                if (isOn) {
                    parm0.replace(4, 5, "1");
                } else {
                    parm0.replace(4, 5, "0");
                }

                switch (mode) {
                    //自动
                    case 0:
                        parm0.replace(5, 8, "000");
                        break;
                    //制冷
                    case 1:
                        parm0.replace(5, 8, "001");
                        break;
                    //除湿
                    case 2:
                        parm0.replace(5, 8, "010");
                        break;
                    //送风
                    case 3:
                        parm0.replace(5, 8, "011");
                        break;
                    //制热
                    case 4:
                        parm0.replace(5, 8, "100");
                        break;
                }

                stringBuilder.append(binaryString2hexString(parm0.toString()));

                //参数 扫风 风量
                StringBuffer parm1 = new StringBuffer("00000000");

                parm1.replace(2, 6, "0001");

                switch (speed) {
                    //自动
                    case 0:
                        parm1.replace(6, 8, "00");
                        break;
                    //低
                    case 1:
                        parm1.replace(6, 8, "01");
                        break;
                    //中
                    case 2:
                        parm1.replace(6, 8, "10");
                        break;
                    //高
                    case 3:
                        parm1.replace(6, 8, "11");
                        break;
                    default:
                        parm1.replace(6, 8, "00");
                        break;
                }

                stringBuilder.append(binaryString2hexString(parm1.toString()));
                stringBuilder.append("00");
                stringBuilder.append("00000000");
                stringBuilder.append("0000");
                stringBuilder.append("0000");
                stringBuilder.append("0000");
                stringBuilder.append("00");
                stringBuilder.append(Integer.toHexString(35));
                stringBuilder.append(Integer.toHexString(80));
                stringBuilder.append("01");
                stringBuilder.append("0000");
                break;
        }


        return stringBuilder.toString();
    }


    /**
     * 10进制转16进制
     *
     * @param num   10进制数
     * @param limit 限制保留多少位，16进制大于限制位保留到下位限制位，限制位大于16进制时不够补0
     */
    private String decimal2hex(int num, int limit) {
        String hex = Integer.toHexString(num);
        if (hex.length() < limit) {
            int j = limit - hex.length();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < j; i++) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hex);
            return stringBuffer.toString();
        } else if (hex.length() > limit) {
            int i = hex.length() - limit;
            return hex.substring(i);
        } else {
            return hex;
        }
    }

    /**
     * 二进制转换十六进制
     *
     * @param bString
     * @return
     */
    private String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    public String decimal2binaryString(int num, int limit) {
        String binaryString = Integer.toBinaryString(num);
        if (binaryString.length() < limit) {
            int j = limit - binaryString.length();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < j; i++) {
                stringBuffer.append("0");
            }
            stringBuffer.append(binaryString);
            return stringBuffer.toString();
        } else if (binaryString.length() > limit) {
            int i = binaryString.length() - limit;
            return binaryString.substring(i);
        } else {
            return binaryString;
        }
    }
}
