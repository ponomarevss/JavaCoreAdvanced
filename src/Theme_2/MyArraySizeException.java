package Theme_2;

 public class MyArraySizeException extends RuntimeException {
   public MyArraySizeException() {
    System.out.println("Получен массив некорректного размера. Введите массив размером 4х4.");
   }
}
