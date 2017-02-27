package app.xunxun.no3618;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fengdianxun on 16/9/27.
 */

public class RandomGenerator {
    public static String getIMEI() {// calculator IMEI
        int r1 = 1000000 + new java.util.Random().nextInt(9000000);
        int r2 = 1000000 + new java.util.Random().nextInt(9000000);
        String input = r1 + "" + r2;
        char[] ch = input.toCharArray();
        int a = 0, b = 0;
        for (int i = 0; i < ch.length; i++) {
            int tt = Integer.parseInt(ch[i] + "");
            if (i % 2 == 0) {
                a = a + tt;
            } else {
                int temp = tt * 2;
                b = b + temp / 10 + temp % 10;
            }
        }
        int last = (a + b) % 10;
        if (last == 0) {
            last = 0;
        } else {
            last = 10 - last;
        }
        return input + last;
    }

    public static String getImsi() {
        // 460022535025034
        String title = "4600";
        int second = 0;
        do {
            second = new java.util.Random().nextInt(8);
        } while (second == 4);
        int r1 = 10000 + new java.util.Random().nextInt(90000);
        int r2 = 10000 + new java.util.Random().nextInt(90000);
        return title + "" + second + "" + r1 + "" + r2;
    }

    public static String getMac() {
        char[] char1 = "abcdef".toCharArray();
        char[] char2 = "0123456789".toCharArray();
        StringBuffer mBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int t = new java.util.Random().nextInt(char1.length);
            int y = new java.util.Random().nextInt(char2.length);
            int key = new java.util.Random().nextInt(2);
            if (key == 0) {
                mBuffer.append(char2[y]).append(char1[t]);
            } else {
                mBuffer.append(char1[t]).append(char2[y]);
            }

            if (i != 5) {
                mBuffer.append(":");
            }
        }
        return mBuffer.toString();
    }

    public static String getAndroidId() {
        String androidid10 = String.valueOf(generateRandomArray(20));
        BigInteger bigInteger = new BigInteger(androidid10,10);
        return bigInteger.toString(16);
    }

    /**
     * 随机生成 num位数字字符数组
     *
     * @param num
     * @return
     */
    private static char[] generateRandomArray(int num) {
        String chars = "0123456789";
        char[] rands = new char[num];
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * 随机生成分辨率.
     * @return
     */
    public static WidthHeight genearateWidthHeight(){
        List<WidthHeight> list = new ArrayList<>();
        list.add(new WidthHeight(720,1280));
        list.add(new WidthHeight(1080,1920));
        list.add(new WidthHeight(480,854));
        list.add(new WidthHeight(540,960));
        list.add(new WidthHeight(480,800));
        list.add(new WidthHeight(720,1184));
        list.add(new WidthHeight(1080,1776));
        list.add(new WidthHeight(1080,1812));
        list.add(new WidthHeight(1440,2560));

        Random random = new Random();
        int index = random.nextInt(list.size()-1);
        return list.get(index);
    }

}
