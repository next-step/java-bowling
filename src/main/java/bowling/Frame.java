package bowling;

public interface Frame {

  int frameNo();

  String desc();

  Frame bowl(Pins pins);

  boolean isGameEnd();
}
