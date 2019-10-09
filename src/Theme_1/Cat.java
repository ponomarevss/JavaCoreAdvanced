package Theme_1;

public class Cat implements CrossCountryRunning {

    private final String name;
    private boolean isReady;

    public Cat(String name) {
        this.name = name;
        isReady = true;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public double jump() {
//        System.out.print("Кот по имени " + name + " пытается перепрыгнуть");
        return 2.5;
    }

    @Override
    public int run() {
//        System.out.print("Кот по имени " + name + " пытается пробежать");
        return 300;
    }
}
