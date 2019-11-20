package dto;

import score.ScoreInfo;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreDto {
    private static final String DELIMITER = "|";
    private static final int DELIMITER_INDEX = 1;
    private static final String EMPTY = "";
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

    private static String deleteStartDelimiter(String raw) {
        if (raw.startsWith(DELIMITER)) {
            raw = raw.substring(DELIMITER_INDEX);
        }
        return raw;
    }

    public ScoreDto(List<String> scores) {
        supplyEmpty(scores);

        this.first = deleteStartDelimiter(scores.get(FIRST));
        this.second = scores.get(SECOND);
    }

    private void supplyEmpty(List<String> scores) {
        if (scores.size() < 2) {
            scores.add(EMPTY);
            scores.add(EMPTY);
        }
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
