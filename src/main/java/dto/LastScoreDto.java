package dto;

import score.ScoreInfo;

import java.util.List;
import java.util.stream.Collectors;

public class LastScoreDto {
    private static final String DELIMITER = "|";
    private static final int DELIMITER_INDEX = 1;
    private static final String EMPTY = "";
    private static final int FIRST = 0;

    private final List<String> scores;

    public LastScoreDto(List<String> scores) {
        supplyEmpty(scores);
        String first = deleteStartDelimiter(scores.get(FIRST));
        scores.remove(FIRST);
        scores.add(0, first);
        this.scores = scores;
    }

    public static LastScoreDto of(List<ScoreInfo> scoreInfos) {
        List<String> scores = scoreInfos.stream()
                .map(FrontConverter::convert)
                .collect(Collectors.toList());
        return new LastScoreDto(scores);
    }

    private static String deleteStartDelimiter(String raw) {
        if (raw.startsWith(DELIMITER)) {
            raw = raw.substring(DELIMITER_INDEX);
        }
        return raw;
    }

    private void supplyEmpty(List<String> scores) {
        if (scores.size() < 3) {
            scores.add(EMPTY);
            scores.add(EMPTY);
            scores.add(EMPTY);
        }
    }

    public String get(int index) {
        return scores.get(index);
    }

}
