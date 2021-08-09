package bowling.domain;

import bowling.domain.score.TurnScore;
import bowling.domain.turn.Turn;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreBoardTest {
    @ValueSource(strings = {
            "PJS", "PJS,KYJ"
    })
    @ParameterizedTest
    void generateTest(String strNames) {
        List<Name> names = toNames(strNames);

        assertThat(
                ScoreBoard.generate(names).size()
        ).isEqualTo(names.size());
    }

    @CsvSource({
            "PJS",
            "PJS,KYJ"
    })
    @ParameterizedTest
    void bowlTest(String strNames) {
        List<Name> names = toNames(strNames);
        ScoreBoard scoreBoard = ScoreBoard.generate(names);

        TurnScore turnScore = TurnScore.of(3);
        int frameSize = 10;
        int turnSize = 2;
        int bowlSize = names.size() * frameSize * turnSize;

        for (Name iName : names) {
            IntStream.range(0, bowlSize)
                    .forEach(index -> scoreBoard.bowl(iName, turnScore));
        }
        assertThat(
                scoreBoard.isCompleted()
        ).isTrue();
    }

    private List<Name> toNames(String strNames) {
        return Arrays.stream(strNames.split(","))
                .map(Name::new)
                .collect(Collectors.toList());
    }
}