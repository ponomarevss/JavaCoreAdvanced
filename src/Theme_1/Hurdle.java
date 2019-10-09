package Theme_1;

public class Hurdle extends Obstacle{

    private final String type;
    private final double height;

    public Hurdle(double height) {
        type = "Барьер";
        this.height = height;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean overcome(CrossCountryRunning creature) {
        double jumpHeight = creature.jump();
        return jumpHeight >= height;
    }
}
