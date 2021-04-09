package bowling.domain.frame;

public interface Frame {

    void addPinCount(int pinCount);

    void addPinCount(PinCount pinCount);

    boolean isDone();

    Frame nextFrame();

    FrameNumber number();

    boolean isLast();

  //  FinalFrameResult result();

//    Score score();

//    List<Integer> pinCounts();
}
