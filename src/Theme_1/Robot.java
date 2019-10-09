package Theme_1;

public class Robot implements CrossCountryRunning {

    private final String model;
    private boolean isReady;

    public Robot(String model) {
        this.model = model;
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
        //System.out.println("Робот модели " + model + " прыгнул.");
        return 0.5;
    }

    @Override
    public int run() {
        //System.out.println("Робот модели " + model + " побежал.");
        return 100;
    }
}
