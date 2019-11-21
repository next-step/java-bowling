package dto;

import dto.util.DelimiterUtil;
import dto.util.ListUtil;
import score.ScoreInfo;

import java.util.List;
import java.util.stream.Collectors;

public class LastScoreDto {
    private static final int FIRST = 0;

    private final List<String> scores;

    public LastScoreDto(List<String> scores) {
        supplyEmpty(scores);
        changeFirstScore(scores);
        this.scores = scores;
    }

    public static LastScoreDto of(List<ScoreInfo> scoreInfos) {
        List<String> scores = scoreInfos.stream()
                .map(FrontConverter::convert)
                .collect(Collectors.toList());
        return new LastScoreDto(scores);
    }

    private void changeFirstScore(List<String> scores) {
        String first = DelimiterUtil.deleteBar(scores.get(FIRST));
        scores.remove(FIRST);
        scores.add(FIRST, first);
    }

    private void supplyEmpty(List<String> scores) {
        ListUtil.addEmptyString(scores, 3);
    }

    public String get(int index) {
        return scores.get(index);
    }

}
