package bowling.domain;

import bowling.domain.frame.FrameResult;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BowlingGameResult {

    private final Map<Integer, List<FrameResult>> results = new LinkedHashMap<>();

    public void put(int position, List<FrameResult> frameResults){
        this.results.put(position, frameResults);
    }

    public List<FrameResult> get(int position){
        return this.results.getOrDefault(position, Collections.EMPTY_LIST);
    }

}
