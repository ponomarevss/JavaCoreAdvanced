package Theme_5;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class ThreadThisArray {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    private static float[] fillIt(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    private static float[] calcIt(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + 1 / 2));
        }
        return arr;
    }

    private static void method1() {
        float[] arr = new float[SIZE];
        fillIt(arr);
        long a = System.currentTimeMillis();
        calcIt(arr);
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void method2() {
        float[] arr = new float[SIZE];
        fillIt(arr);
        long a = System.currentTimeMillis();
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        new Thread(() -> calcIt(a1)).start();
        new Thread(() -> calcIt(a2)).start();

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);
/*
        calcIt(a1);
        calcIt(a2);
*/
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void main(String[] args) {
        method2();
        method1();
    }
}
