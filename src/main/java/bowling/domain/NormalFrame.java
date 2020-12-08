package bowling.domain;

public class NormalFrame {
    private static final int FINAL_FRAME_INDEX = 9;
    
    private final int index;

    private NormalFrame(final int index) {
        this.index = index;
    }

    public static NormalFrame first() {
        return new NormalFrame(0);
    }
    
    public NormalFrame next() {
        return new NormalFrame(index + 1); 
    }
}
