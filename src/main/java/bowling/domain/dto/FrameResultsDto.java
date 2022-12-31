package bowling.domain.dto;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FrameResultsDto {

    private final LinkedList<String> results;

    public FrameResultsDto(Player player, List<Frame> frameResults) {
        results = frameResults.stream()
                .map(Object::toString)
                .collect(Collectors.toCollection(LinkedList::new));

        makeResultsMaxSize(frameResults);
        results.addFirst(player.name());
    }

    private void makeResultsMaxSize(List<Frame> frameResults) {
        int frameResultsSize = frameResults.size();
        for (int i = 0; i < (Frames.MAX_FRAME_NUMBER - frameResultsSize); i++) {
            results.addLast("");
        }
    }

    public List<String> results() {
        return Collections.unmodifiableList(results);
    }
}
