package bowling.domain;

public class FrameFactory {
    public static final Frame FIRST = Frame.first();
    public static final Frame FINAL;
    
    static {
        Frame frame = Frame.first();
        for (int i = 1; i < 10; i++) {
            frame = frame.next();
        }
        FINAL = frame;
    }
}
