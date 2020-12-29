package io.pandora.mall.util;

/**
 * @author zhouhouxing
 * @Chinese Chinese Zone
 * @data 2019/6/28 19:22
 */
public class CodeUtils {

    /** 补位字符串 */
    private static final String e = "ATGSGHJ";

    /** 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符） */
    private static final char[] r = new char[] {
            'Q', 'W', 'E', '8', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P', '5', 'K', '3', 'M',
            'J', 'U', 'F', 'R', '4', 'V', 'Y', 'T', 'N', '6', 'B', 'G', 'H'
    };

    /** 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的） */
    private static final char b = 'A';

    /**
     * binLen -> 代表进制长度
     * s -> 代表邀请码长度
     * */
    private static final int binLen = r.length ,s = 6;

    /**
     * 根据ID生成六位随机码
     * @param  id
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }

        buf[--charPos] = r[(int)(id % binLen)];

        String str = new String(buf, charPos, (32 - charPos));

        // 不够长度的自动补全
        if(str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(e.subSequence(0, s - str.length()));
            str += sb.toString();
        }
        return str;
    }

}
