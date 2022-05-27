package bowling.domain.frame;

public class FrameIndex {

    private static final int MIN_FRAME_INDEX = 1;
    private static final int MAX_FRAME_INDEX = 10;
    private static final int LAST_NORMAL_FRAME_INDEX = 9;
    private static final String INDEX_OUT_OF_FRAME_INDEX_RANGE = "프레임의 index는 1~10사이만 가능합니다.";

    private final int index;

    private FrameIndex(int index) {
        validate(index);
        this.index = index;
    }

    public static FrameIndex of(int index) {
        return new FrameIndex(index);
    }

    private void validate(int index) {
        if(index < MIN_FRAME_INDEX || index > MAX_FRAME_INDEX){
            throw new IllegalArgumentException(INDEX_OUT_OF_FRAME_INDEX_RANGE);
        }
    }

    public FrameIndex nextIndex(){
        return FrameIndex.of(index+1);
    }

    public boolean isLastNormalIndex(){
        return index == LAST_NORMAL_FRAME_INDEX;
    }

    public int getIndex() {
        return index;
    }
}
