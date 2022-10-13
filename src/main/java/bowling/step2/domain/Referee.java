package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.dto.PlayerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Referee {
    private static final int STOP_SCORE = -1;
    private static final int MAX_PER_SCORE = 10;
    
    private int cumulativeScore;
    private boolean isCalculateStop;
    
    public String calculateCumulativeScore(final PlayerDTO playerDTO, final int index) {
        final List<List<Score>> allScores = getAllScores(playerDTO);
        if (allScores.size() <= index) {
            return String.valueOf(STOP_SCORE);
        }
    
        return parseCumulativeScore(index, allScores);
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
        final List<Score> currentScores = checkCalculateStop(allScores.get(index));
        if (isStrike(currentScores)) {
            return MAX_PER_SCORE + getTwoNextScore(allScores, index + 1);
        }
        
        if (isSpare(currentScores)) {
            return MAX_PER_SCORE + getOneNextScore(allScores, index + 1);
        }
        return getSumScores(currentScores);
    }
    
    private List<Score> checkCalculateStop(final List<Score> scores) {
        if (isReadyContains(scores) || isNormal(scores)) {
            isCalculateStop = true;
        }
        
        return scores;
    }
    
    private boolean isNormal(final List<Score> scores) {
        return scores.size() == 1 && !scores.get(0).isStrike();
    }
    
    private boolean isReadyContains(final List<Score> nextScores) {
        return nextScores.stream()
                .mapToInt(Score::getFallenPins)
                .anyMatch(score -> score == STOP_SCORE);
    }
    
    private boolean isStrike(final List<Score> currentScores) {
        return currentScores.get(0).isStrike();
    }
    
    private boolean isSpare(final List<Score> currentScores) {
        return currentScores.size() == 2 && currentScores.get(1).isSpare();
    }
    
    private int getTwoNextScore(final List<List<Score>> allScores, final int index) {
        final List<Score> nextScores = checkCalculateStop(allScores.get(index));
        
        if (nextScores.size() == 2) {
            return getSumScores(nextScores);
        }
        
        return nextScores.get(0).getFallenPins() + getOneNextScore(allScores, index + 1);
    }
    
    private int getSumScores(final List<Score> scores) {
        return scores.stream()
                .mapToInt(Score::getFallenPins)
                .sum();
    }
    
    private int getOneNextScore(final List<List<Score>> allScores, final int index) {
        if (allScores.size() <= index) {
            return STOP_SCORE;
        }
        
        final List<Score> nextScores = checkCalculateStop(allScores.get(index));
        return nextScores.get(0).getFallenPins();
    }
}
