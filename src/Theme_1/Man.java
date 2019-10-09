package Theme_1;

public class Man implements CrossCountryRunning {

    private final String name;
    private boolean isReady;

    public Man(String name) {
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
        //System.out.println("Человек по имени " + name + " прыгнул.");
        return 1;
    }

    @Override
    public int run() {
        //System.out.println("Человек по имени " + name + " побежал.");
        return 1000;
    }
}
