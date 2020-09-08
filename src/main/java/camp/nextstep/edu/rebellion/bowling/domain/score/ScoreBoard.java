package camp.nextstep.edu.rebellion.bowling.domain.score;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.game.Player;
import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {
    private final Player player;
    private final Frames frames;

    public ScoreBoard(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public List<String> getResultSymbol() {
        return this.frames
                .getFrames()
                .stream()
                .map(f -> f.getStatus().makeSymbol())
                .collect(Collectors.toList());
    }

    public List<String> getHitsScores() {
        List<String> scores = new ArrayList<>();
        int previewScore = 0;

        for (Frame frame : frames.getFrames()) {
            previewScore = previewScore + frame.getHitsScore();
            scores.add(frame.canCalculateScore() ? String.valueOf(previewScore) : Strings.EMPTY);
        }

        return scores;
    }
}
