package bowling.dto;

import bowling.domain.engine.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ScoresDtoTest {

    /*
     * | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
     * |  CMC |   X  |   X  |  5|3 |  8|/ |   X  |   X  |   X  |   X  |   X  | X|5|/|
     */
    @Test
    @DisplayName("누적된 점수를 계산한 다음, DTO 를 생성한다.")
    void cumulativeSumOfScore() {
        List<Integer> scoreValues = Arrays.asList(25, 18, 8, 20, 30, 30, 30, 30, 30, 25, 20);

        List<Score> scoreList = scoreValues.stream()
                                           .map(Score::initReadyToUseScore)
                                           .collect(Collectors.toList());

        List<Integer> actual = ScoresDto.of(scoreList)
                                        .getScores()
                                        .stream()
                                        .map(ScoreDto::getScore)
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList());

        List<Integer> expected = new ArrayList<>();
        expected.add(scoreValues.get(0));
        for (int i = 1; i < 10; ++i) {
            expected.add(expected.get(i-1) + scoreValues.get(i));
        }

        assertThat(actual).containsAll(expected);
    }

}
