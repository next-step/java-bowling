package bowling.dto;

import java.util.List;

public class ScoresDto {

    private static final int FINAL_ROUND = 10;

    private final List<ScoreDto> scoreDtoList;

    public ScoresDto(List<ScoreDto> scoreDtoList) {
        this.scoreDtoList = scoreDtoList;
    }

    public static ScoresDto of(List<ScoreDto> scoreDtoList) {
        int emptyScoreCount = FINAL_ROUND - scoreDtoList.size();
        for (int i = 0; i < emptyScoreCount; i++) {
            scoreDtoList.add(ScoreDto.isEmpty());
        }
        return new ScoresDto(scoreDtoList);
    }

    public List<ScoreDto> getScoreDtoList() {
        return scoreDtoList;
    }
}
