package bowling;

public interface Frame {
    void setKnockDownPins(int knockDownPins);

    String getStatus();

    boolean isEnd();
}
