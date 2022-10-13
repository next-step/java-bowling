package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.dto.PlayerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Referee {
    private static final int STOP_SCORE = -1;
    private static final int MAX_PER_SCORE = 10;
    private static final int COUNT_OF_MAX_PINS = 10;
    
    private int cumulativeScore;
    private boolean isCalculateStop;
    
    public String calculateCumulativeScore(final PlayerDTO playerDTO, final int index) {
        final List<List<Score>> allScores = getAllScores(playerDTO);
        if (isExceedIndex(index, allScores)) {
            return String.valueOf(STOP_SCORE);
        }
        
        if (isFinalFrame(playerDTO, index)) {
            return getFinalFrameScore(allScores, index);
        }
        return parseCumulativeScore(index, allScores);
    }
    
    private String getFinalFrameScore(final List<List<Score>> allScores, final int index) {
        final List<Score> scores = allScores.get(index);
        if (isReadyContains(scores) || isNotMissContains(scores)) {
            return String.valueOf(STOP_SCORE);
        }
        
        return String.valueOf(cumulativeScore + getSumScores(scores, scores.size()));
    }
    
    private boolean isNotMissContains(final List<Score> scores) {
        return scores.size() == 1 || (scores.size() == 2 && getSumScores(scores, scores.size()) >= COUNT_OF_MAX_PINS);
    }
    
    private boolean isExceedIndex(final int index, final List<List<Score>> allScores) {
        return allScores.size() <= index;
    }
    
    private boolean isFinalFrame(final PlayerDTO playerDTO, final int index) {
        final List<Frame> frames = playerDTO.getFrames();
        final Frame frame = frames.get(index);
        return !frame.isNormalFrame();
    }
    
    private String parseCumulativeScore(final int index, final List<List<Score>> allScores) {
        cumulativeScore += getCurrentScore(allScores, index);
        if (isCalculateStop) {
            return String.valueOf(STOP_SCORE);
        }
        
        return String.valueOf(cumulativeScore);
    }
    
    private List<List<Score>> getAllScores(final PlayerDTO playerDTO) {
        return playerDTO.getFrames().stream()
                .map(Frame::getScores)
                .collect(Collectors.toList());
    }
    
    private int getCurrentScore(final List<List<Score>> allScores, final int index) {
        final List<Score> currentScores = checkCalculateStop(allScores, index);
        if (isStrike(currentScores)) {
            return MAX_PER_SCORE + getTwoNextScore(allScores, index + 1);
        }
        
        if (isSpare(currentScores)) {
            return MAX_PER_SCORE + getOneNextScore(allScores, index + 1);
        }
        return getSumScores(currentScores, currentScores.size());
    }
    
    private List<Score> checkCalculateStop(final List<List<Score>> allScores, final int index) {
        final List<Score> currentScores = allScores.get(index);
        if (isReadyContains(currentScores) || isNinthScoreStop(allScores, index, currentScores)) {
            isCalculateStop = true;
        }
        
        return currentScores;
    }
    
    private boolean isNinthScoreStop(final List<List<Score>> allScores, final int index, final List<Score> currentScores) {
        return isNotFirstFrame(index) && isStrike(allScores.get(index - 1)) && isNormal(currentScores);
    }
    
    private boolean isNotFirstFrame(final int index) {
        return index != 0;
    }
    
    private boolean isNormal(final List<Score> scores) {
        return scores.size() == 1 && !isStrike(scores);
    }
    
    private boolean isReadyContains(final List<Score> nextScores) {
        return nextScores.stream()
                .mapToInt(Score::getFallenPins)
                .anyMatch(score -> score == STOP_SCORE);
    }
    
    private boolean isStrike(final List<Score> scores) {
        return scores.get(0).isStrike();
    }
    
    private boolean isSpare(final List<Score> currentScores) {
        return currentScores.size() == 2 && currentScores.get(1).isSpare();
    }
    
    private int getTwoNextScore(final List<List<Score>> allScores, final int index) {
        final List<Score> nextScores = checkCalculateStop(allScores, index);
        if (nextScores.size() >= 2) {
            return getSumScores(nextScores, 2);
        }
        
        return nextScores.get(0).getFallenPins() + getOneNextScore(allScores, index + 1);
    }
    
    private int getSumScores(final List<Score> scores, int limit) {
        return scores.stream()
                .mapToInt(Score::getFallenPins)
                .limit(limit)
                .sum();
    }
    
    private int getOneNextScore(final List<List<Score>> allScores, final int index) {
        if (isExceedIndex(index, allScores)) {
            return STOP_SCORE;
        }
        
        final List<Score> nextScores = checkCalculateStop(allScores, index);
        return nextScores.get(0).getFallenPins();
    }
}
