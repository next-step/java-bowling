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
    
    public String calculateCumulativeScore(final PlayerDTO playerDTO, final int frameOrderNumber) {
        final List<List<Score>> allScores = getAllScores(playerDTO);
        if (isExceedFrameOrderNumber(frameOrderNumber, allScores)) {
            return String.valueOf(STOP_SCORE);
        }
        
        if (isFinalFrame(playerDTO, frameOrderNumber)) {
            return getFinalFrameScore(allScores, frameOrderNumber);
        }
        return parseCumulativeScore(frameOrderNumber, allScores);
    }
    
    private String getFinalFrameScore(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> scores = allScores.get(frameOrderNumber);
        if (isReadyContains(scores) || isNotMissContains(scores)) {
            return String.valueOf(STOP_SCORE);
        }
        
        return String.valueOf(cumulativeScore + getSumScores(scores, scores.size()));
    }
    
    private boolean isNotMissContains(final List<Score> scores) {
        return scores.size() == 1 || (scores.size() == 2 && getSumScores(scores, scores.size()) >= COUNT_OF_MAX_PINS);
    }
    
    private int getSumScores(final List<Score> scores, int limit) {
        return scores.stream()
                .mapToInt(Score::getFallenPins)
                .limit(limit)
                .sum();
    }
    
    private boolean isExceedFrameOrderNumber(final int frameOrderNumber, final List<List<Score>> allScores) {
        return allScores.size() <= frameOrderNumber;
    }
    
    private boolean isFinalFrame(final PlayerDTO playerDTO, final int frameOrderNumber) {
        final List<Frame> frames = playerDTO.getFrames();
        final Frame frame = frames.get(frameOrderNumber);
        return !frame.isNormalFrame();
    }
    
    private String parseCumulativeScore(final int frameOrderNumber, final List<List<Score>> allScores) {
        cumulativeScore += getCurrentScore(allScores, frameOrderNumber);
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
    
    private int getCurrentScore(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> currentScores = checkCalculateStop(allScores, frameOrderNumber);
        if (isStrike(currentScores)) {
            return MAX_PER_SCORE + getTwoNextScore(allScores, frameOrderNumber + 1);
        }
        
        if (isSpare(currentScores)) {
            return MAX_PER_SCORE + getOneNextScore(allScores, frameOrderNumber + 1);
        }
        return getSumScores(currentScores, currentScores.size());
    }
    
    private List<Score> checkCalculateStop(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> currentScores = allScores.get(frameOrderNumber);
        if (isReadyContains(currentScores) || isNormal(currentScores)) {
            isCalculateStop = true;
        }
        
        return currentScores;
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
    
    private int getTwoNextScore(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> nextScores = checkTwoNextCalculateStop(allScores, frameOrderNumber);
        if (nextScores.size() >= 2) {
            return getSumScores(nextScores, 2);
        }
        
        return nextScores.get(0).getFallenPins() + getOneNextScore(allScores, frameOrderNumber + 1);
    }
    
    private List<Score> checkTwoNextCalculateStop(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> currentScores = allScores.get(frameOrderNumber);
        if (isTwoReadyContains(currentScores)) {
            isCalculateStop = true;
        }
        
        return currentScores;
    }
    
    private boolean isTwoReadyContains(final List<Score> currentScores) {
        return currentScores.stream()
                .limit(2)
                .mapToInt(Score::getFallenPins)
                .anyMatch(score -> score == STOP_SCORE);
    }
    
    private int getOneNextScore(final List<List<Score>> allScores, final int frameOrderNumber) {
        final List<Score> nextScores = checkOneNextCalculateStop(allScores, frameOrderNumber);
        return nextScores.get(0).getFallenPins();
    }
    
    private List<Score> checkOneNextCalculateStop(final List<List<Score>> allScores, final int frameOrderNumber) {
        if (isExceedFrameOrderNumber(frameOrderNumber, allScores) || isReady(allScores.get(frameOrderNumber))) {
            isCalculateStop = true;
            return List.of(new Score(STOP_SCORE));
        }
        
        return allScores.get(frameOrderNumber);
    }
    
    private boolean isReady(final List<Score> currentScores) {
        return currentScores.get(0).getFallenPins() == STOP_SCORE;
    }
}
