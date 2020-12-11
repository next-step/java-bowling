package bowling.domain;

public class FrameFactory {
    public static Frame creates() {
        Frame first = Frame.createFirst();
        Frame frame = first;
        for (int i = 0; i < 9; i++) {
            frame = frame.createNext();
        }
        return first;
    }
}
