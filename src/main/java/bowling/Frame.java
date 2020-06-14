package bowling;

public interface Frame {

    void play(int numberOfDownPin);

    boolean hasTurn();

    FrameResult getResult();
}
