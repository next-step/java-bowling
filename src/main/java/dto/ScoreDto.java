package dto;

import dto.util.DelimiterUtil;
import dto.util.ListUtil;
import score.ScoreInfo;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreDto {
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private final String first;
    private final String second;

    public static ScoreDto of(List<ScoreInfo> scoreInfos) {
        List<String> scores = scoreInfos.stream()
                .map(FrontConverter::convert)
                .collect(Collectors.toList());
        return new ScoreDto(scores);
    }

    public ScoreDto(List<String> scores) {
        supplyEmpty(scores);

        this.first = DelimiterUtil.deleteBar(scores.get(FIRST));
        this.second = scores.get(SECOND);
    }

    private void supplyEmpty(List<String> scores) {
        ListUtil.addEmptyString(scores, 2);

    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
