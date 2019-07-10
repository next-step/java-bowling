package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 18:52
 */
public class BowlingExecutor {
    private GameBoard board;

    public BowlingExecutor() {
        this.board = new GameBoard();
    }

//    public List<Bowl> execute(int fallCount) {
    public List<List<Integer>> execute(int fallCount) {
//        return convert(board.play(fallCount));
//        return convertToScores(board.play(fallCount));
        return convert2(board.play(fallCount));
    }

    private List<List<Integer>> convert2(Map<Integer, FrameScore> soucre) {
        return soucre.keySet().stream()
                .map(key -> soucre.get(key))
                .map(frameScore -> frameScore.getScoreNumber())
                .collect(Collectors.toList());
    }

    private List<Bowl> convertToScores(Map<Integer, FrameScore> soucre) {
        return convertToBowl(soucre.keySet().stream()
                .map(key -> soucre.get(key))
                .map(frameScore -> frameScore.getScores())
                .reduce((sourceList, targetList) -> union(sourceList, targetList))
                .get());
    }

    private List<Bowl> convertToBowl(List<Score> scores) {
        return scores.stream()
                .map(score -> Bowl.check(score.getScore(), score.isStrike()))
                .collect(Collectors.toList());
    }

    private List<Score> union(List<Score> source, List<Score> target) {
        List<Score> newScores = new ArrayList<>(source);
        newScores.addAll(target);
        return newScores;
    }

    private List<Bowl> convert(Map<Integer, FrameScore> snapShot) {
        return snapShot.keySet().stream()
                .map(frameNumber ->
                        Bowl.check(
                                snapShot.get(frameNumber).searchRecentScore(),
                                snapShot.get(frameNumber).isStrike()))
                .collect(Collectors.toList());
    }
}
