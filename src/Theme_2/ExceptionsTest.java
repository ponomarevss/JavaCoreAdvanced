package Theme_2;

public class ExceptionsTest {

    public static int arraySum(String array[][]) throws MyArraySizeException, MyArrayDataException {
        int arraySum = 0;
        if (array.length != 4) throw new MyArraySizeException();
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) throw new MyArraySizeException();
            for (int j = 0; j < array.length; j++) {
                try {
                    arraySum += Integer.valueOf(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return arraySum;
    }

    public static void main(String[] args) {
        String[][] arr = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}}; //корректный массив
//        String[][] arr = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1"}, {"1", "1", "1", "1"}}; //некорректный размер
//        String[][] arr = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"f", "1", "1", "1"}, {"1", "1", "1", "1"}}; //некорректные данные
        try {
            System.out.println(arraySum(arr));
        } catch (MyArraySizeException e) {
//            e.printStackTrace();
        } catch (MyArrayDataException e) {
//            e.printStackTrace();
        }
    }
}
