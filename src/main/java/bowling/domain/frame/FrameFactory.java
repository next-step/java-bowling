package bowling.domain.frame;

public class FrameFactory {
    public static Frame creates() {
        Frame first = Frame.createFirst();
        Frame frame = first;
        for (int i = 0; i < FinalFrame.INDEX; i++) {
            frame = frame.createNext();
        }
        return first;
    }
}