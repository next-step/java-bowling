package bowling.domain;

public class BowlingGame implements Playable{
    private Player player;
    private Bowling bowling;

    public BowlingGame(String name){
        player = new Player(name);
        bowling = new Bowling();
    }

    @Override
    public void play(int point) {
        bowling.play(point);
    }

    @Override
    public boolean isEnd() {
        return bowling.isEnd();
    }

    public int frameCount() {
        return bowling.frameCount();
    }
}
