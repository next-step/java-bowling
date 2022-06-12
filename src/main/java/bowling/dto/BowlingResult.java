package bowling.dto;

import bowling.domain.BowlingGame;
import bowling.domain.FrameNo;
import bowling.domain.Frames;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.exception.CannotCalculateScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingResult {

    private String playerName;
    private List<Integer> frameNos;
    private List<String> descriptions;
    private List<String> scores;

    public BowlingResult(String playerName, List<Integer> frameNos, List<String> descriptions, List<String> scores) {
        this.playerName = playerName;
        this.frameNos = frameNos;
        this.descriptions = descriptions;
        this.scores = scores;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Integer> getFrameNos() {
        return frameNos;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getScores() {
        return scores;
    }

    public static BowlingResult from(BowlingGame bowlingGame) {
        String playerName = bowlingGame.player().toString();
        List<Integer> frameNos = findFrameNos(bowlingGame.frames());
        List<String> descriptions = findDescriptions(bowlingGame.frames());
        List<String> scores = findScoreValues(bowlingGame.frames());
        return new BowlingResult(playerName, frameNos, descriptions, scores);
    }

    private static List<Integer> findFrameNos(Frames frames) {
        return frames.toList()
                .stream()
                .map(Frame::frameNo)
                .map(FrameNo::toInt)
                .collect(Collectors.toList());
    }

    private static List<String> findDescriptions(Frames frames) {
        return frames.toList()
                .stream()
                .map(Frame::toString)
                .collect(Collectors.toList());
    }

    private static List<String> findScoreValues(Frames frames) {
        List<String> scores = new ArrayList<>();
        for (Frame frame : frames.toList()) {
            String score = validAndFindScores(frames, frame.frameNo());
            scores.add(score);
        }
        return scores;
    }

    private static String validAndFindScores(Frames frames, FrameNo frameNo) {
        try {
            return findScores(frames, frameNo).toString();
        } catch (CannotCalculateScore e) {
            return "";
        }
    }

    private static Score findScores(Frames frames, FrameNo frameNo) {
        return frames.toList()
                .stream()
                .limit(frameNo.toInt())
                .map(Frame::score)
                .reduce(Score::sum)
                .orElseThrow(CannotCalculateScore::new);
    }
}
