package bowling.domain;

public interface Frame {

    Frame bowl(Pins pins);

    String mark();

    boolean isFinished();

    Frame nextFrame();

    Round round();

}
