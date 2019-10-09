package Theme_1;

public class TestMain {

    public static void main(String[] args) {

        CrossCountryRunning creature[] = new CrossCountryRunning[3];
        creature[0] = new Man("Homer");
        creature[1] = new Cat("Scratchy");
        creature[2] = new Robot("Bender404");

        Obstacle obstacle[] = new Obstacle[6];
        obstacle[0] = new Track(50);
        obstacle[1] = new Hurdle(.5);
        obstacle[2] = new Track(150);
        obstacle[3] = new Hurdle(1.5);
        obstacle[4] = new Track(300);
        obstacle[5] = new Hurdle(2.5);

        for (int i = 0; i < obstacle.length; i++) {
            System.out.println("Препятствие ");
            for (int j = 0; j < creature.length; j++) {
                if (creature[j].isReady()) {
                    creature[j].setReady(obstacle[i].overcome(creature[j]));
                }
            }
        }
    }
}
