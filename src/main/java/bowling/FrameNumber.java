package bowling;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameNumber {
    private static final String FRAME_NUMBER_ERROR = "범위를 벗어난 프레임 수 입니다.";

    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;
    private static final int PLACE_VALUE = 2;

    private static final Map<Integer, FrameNumber> frameNumberCache;

    private final String frameNumber;

    static {
        frameNumberCache = IntStream.rangeClosed(MIN_FRAME_NUMBER, MAX_FRAME_NUMBER)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), FrameNumber::new));
    }

    private FrameNumber(int number){
        this.frameNumber = String.format("%0"+PLACE_VALUE+"d", number);
    }

    public static FrameNumber from(int number){
        if(frameNumberCache.containsKey(number)){
            return frameNumberCache.get(number);
        }
        throw new IllegalArgumentException(FRAME_NUMBER_ERROR);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FrameNumber){
            return frameNumber.equals(((FrameNumber) obj).frameNumber);
        }

        if(obj instanceof Integer){
            return Integer.parseInt(frameNumber) == (int) obj;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return frameNumber.hashCode();
    }

    @Override
    public String toString() {
        return frameNumber;
    }
}
