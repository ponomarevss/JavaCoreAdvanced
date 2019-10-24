package Theme_5;

import java.util.Arrays;

public class ThreadThisArray {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    private static float[] createArray(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    private static float[] calcIt(float[] arr, int indexShift) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + indexShift) / 5) * Math.cos(0.2f + (i + indexShift) / 5) * Math.cos(0.4f + (i + indexShift) / 2));
        }
        return arr;
    }

    private static void method1(float[] arr) {
        long a = System.currentTimeMillis();
        calcIt(arr, 0);
        System.out.println(System.currentTimeMillis() - a + " consequent method\t");
    }

    private static void method2(float[] arr) {
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                calcIt(a1, 0);
//            }
//        });
//        thread1.start();
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                calcIt(a2, HALF);
//            }
//        });
//        thread2.start();

        Thread thread1 = new Thread(() -> calcIt(a1, 0));
        Thread thread2 = new Thread(() -> calcIt(a2, HALF));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        System.out.println(System.currentTimeMillis() - a + " parallel method\t");
    }

    public static void main(String[] args) {
        float[] array1 = createArray(SIZE);
        method1(array1);
        float[] array2 = createArray(SIZE);
        method2(array2);
        System.out.println("arrays equals: " + Arrays.equals(array1, array2));
    }
}
