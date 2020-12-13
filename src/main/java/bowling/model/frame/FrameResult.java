package bowling.model.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrameResult {
    private final List<String> results;
    public static final FrameResult empty = new FrameResult(new LinkedList<>());

    private FrameResult(List<String> results){
        this.results = fillListUpToNumberOfFrames(results);
    }


    public static FrameResult from(List<String> results){
        return new FrameResult(results);
    }

    private List<String> fillListUpToNumberOfFrames(List<String> list){
        int dummySize = FrameNumber.MAX_FRAME_NUMBER - list.size();
        IntStream.range(0, dummySize).forEach(idx -> list.add(""));

        return list;
    }

    public Stream<String> stream(){
        return results.stream();
    }
}
