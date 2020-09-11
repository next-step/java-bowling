package camp.nextstep.edu.rebellion.bowling.domain.score;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.player.Player;
import camp.nextstep.edu.rebellion.bowling.domain.frame.Frames;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static camp.nextstep.edu.rebellion.bowling.util.StringUtil.*;

public class PersonalScoreBoard {
    private final Player player;
    private final Frames frames;

    public PersonalScoreBoard(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public String generateForPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append(generateFrameStatus(player.getName(), getResultSymbol()))
                .append(ENTER)
                .append(generateScore(getHitsScores()));
        return builder.toString();
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

    private static String generateFrameStatus(String playerName, List<String> symbols) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapFormat(playerName));
        symbols.forEach(s -> builder.append(wrapFormat(s)));
        builder.append(PIPE);
        return builder.toString();
    }

    private static String generateScore(List<String> hitScores) {
        StringBuilder builder = new StringBuilder();
        builder.append(wrapFormat(Strings.EMPTY));
        hitScores.forEach(s -> builder.append(wrapFormat(s)));
        builder.append(PIPE);
        return builder.toString();

    }

    private static String wrapFormat(String str) {
        return String.format(FORMAT, str);
    }
}
