package bowling.frame;

public interface Frame {

  Frame play(int pinCount);

  String getScore();

  boolean isGameEnd();
}