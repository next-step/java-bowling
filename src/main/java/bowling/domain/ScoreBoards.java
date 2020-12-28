package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoards {

    private int currentFrameNo;
    private List<ScoreSheet> boards;

    public ScoreBoards(List<Player> players) {
        this.currentFrameNo = 0;
        this.boards = players.stream().map(DefaultScoreSheet::new).collect(Collectors.toList());
    }

    public boolean hasNextFrame() {
        return false;
    }

    public FrameSet nextFrameSet() {
        currentFrameNo++;
        return FrameSet.of(boards.stream()
                .map(ScoreSheet::nextFrame)
                .collect(Collectors.toList())
        );
    }

}
