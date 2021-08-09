package bowling.domain;

import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreBoardTest {
    @DisplayName("record 테스트")
    @CsvSource({
            "PJS",
            "PJS,KYJ"
    })
    @ParameterizedTest
    void recordTest(String strNames) {
        List<Name> names = toNames(strNames);
        ScoreBoard scoreBoard = ScoreBoard.generate(names);

        TurnScore turnScore = TurnScore.of(3);
        int frameSize = 10;
        int turnSize = 2;
        int bowlSize = names.size() * frameSize * turnSize;

        IntStream.range(0, bowlSize)
                .forEach(index -> scoreBoard.record(scoreBoard.currentPlayer(), turnScore));
        assertThat(
                scoreBoard.isCompleted()
        ).isTrue();
    }

    private List<Name> toNames(String strNames) {
        String[] splitNames = strNames.split(",");

        return Arrays.stream(splitNames)
                .map(Name::new)
                .collect(Collectors.toList());
    }
}