package Theme_1;

public class Track extends Obstacle{

    private final String type;
    private final int distance;

    public Track(int distance) {
        type = "Дорожка";
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean overcome(CrossCountryRunning creature) {
        int runDistance = creature.run();
        return runDistance >= distance;
    }
}
