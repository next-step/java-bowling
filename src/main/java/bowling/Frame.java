package bowling;

public interface Frame {

  int frameNo();

  String desc();

  Frame bowl(Pins pins);

  boolean isGameEnd();

  Score getScore();

  Score addAdditionalScore(Score prevScore);
}
