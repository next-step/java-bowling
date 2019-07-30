package bowling;

public interface Frame {

  Frame roll(int countOfPin);

  boolean isGameEnd();

  int getFrameNo();

  Frame nextFrame();

  int score();

  int addScore(Score previousScore);
}
