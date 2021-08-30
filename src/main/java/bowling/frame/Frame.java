package bowling.frame;

public interface Frame {

  Frame play(int pinCount, final int size);

  String getScore();

  boolean isGameEnd();
}