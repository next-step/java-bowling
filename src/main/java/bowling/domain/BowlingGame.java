package bowling.domain;

public class BowlingGame {
    private Player player;
    private Bowling bowling;

    public BowlingGame(String name){
        player = new Player(name);
        bowling = new Bowling();
    }
}
