package Theme_2;

public class MyArrayDataException extends NumberFormatException {
    private int row;
    private int cell;
    public MyArrayDataException(int row, int cell) {
        this.row = row;
        this.cell = cell;
        System.out.println("Неверный формат данных в строке " + row + ", ячейке " + cell + ".");
    }
}
