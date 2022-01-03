package bowling.domain;

public interface Frame {

    Frame bowl(Pins pins);

    Frame nextFrame();

    Round round();

    boolean isFinished();

    String mark();

}
