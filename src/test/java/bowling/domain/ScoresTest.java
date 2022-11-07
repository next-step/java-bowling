package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class ScoresTest {


    private static Stream<Arguments> generateScore() {
        return Stream.of(
                Arguments.of(List.of(1, 4, 2)),
                Arguments.of(List.of(1, 4, 3, 1, 10, 3))
        );
    }

    private static Stream<Arguments> generateScoreAndWhetherNormalRoundEnded() {
        return Stream.of(
                Arguments.of(List.of(1, 4), true),
                Arguments.of(List.of(10), true),
                Arguments.of(List.of(0), false),
                Arguments.of(List.of(5), false)
        );
    }

    private static Stream<Arguments> generateScoreAndWhetherLastRoundEnded() {
        return Stream.of(
                Arguments.of(List.of(1, 4), true),
                Arguments.of(List.of(0, 0), true),
                Arguments.of(List.of(0), false),
                Arguments.of(List.of(10, 10, 10), true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateScore")
    void shouldAddPins(List<Integer> inputs) {
        List<Score> result = inputs.stream()
                .map(Score::new)
                .collect(Collectors.toList());
        Scores scores = new Scores();
        inputs.forEach(scores::add);

        assertThat(scores.containsAll(result)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("generateScoreAndWhetherNormalRoundEnded")
    void shouldReturnNormalRoundEndOrNot(List<Integer> inputs, boolean expectedResult) {
        Scores scores = new Scores();
        inputs.forEach(scores::add);
        assertThat(scores.isNormalRoundEnd()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("generateScoreAndWhetherLastRoundEnded")
    void shouldReturnLastRoundEndOrNot(List<Integer> inputs, boolean expectedResult) {
        Scores scores = new Scores();
        inputs.forEach(scores::add);
        assertThat(scores.isLastRoundEnd()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"1,9,true", "4,6,true", "9,1,true", "10,0,false", "0,10,true"})
    void shouldValidateSecondSpare(int firstScore, int secondScore, boolean expectedResult) {
        Scores scores = new Scores();
        scores.add(firstScore);
        scores.add(secondScore);

        assertThat(scores.isSecondPinSpare()).isEqualTo(expectedResult);
    }


    @ParameterizedTest
    @CsvSource({"10,1,9,true", "0,1,9,true", "0,0,10,true", "0,10,0,false", "10,10,10,false"})
    void shouldValidateThirdSpare(int firstScore, int secondScore, int thirdScore, boolean expectedResult) {
        Scores scores = new Scores();
        scores.add(firstScore);
        scores.add(secondScore);
        scores.add(thirdScore);

        assertThat(scores.isThirdPinSpare()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"5,5,10", "1,9,10", "0,10,10", "10,10,20"})
    void shouldSumScore(int x, int y, int sum) {
        Scores scores = new Scores(List.of(x, y));
        assertThat(scores.sum()).isEqualTo(sum);
    }

    @ParameterizedTest
    @CsvSource({"5,15", "10,20", "0,10"})
    void shouldGetSpareBonus(int firstScore, int expectedResult) {
        Integer spareBonus = new Scores(List.of(firstScore)).spareBonus();
        assertThat(spareBonus).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"5,25", "10,30", "0,20"})
    void shouldGetDoubleStrikeBonus(int firstScore, int expectedResult) {
        Integer spareBonus = new Scores(List.of(firstScore)).doubleStrikeBonus();
        assertThat(spareBonus).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"10,true", "1,false", "3,false"})
    void shouldGetWhetherContainsStrike(int score, boolean expectedResult) {
        assertThat(new Scores(List.of(score)).containsStrike()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"7,3,true", "1,9,true", "5,3,false"})
    void shouldGetWhetherContainsSpare(int scoreA, int scoreB, boolean expectedResult) {
        assertThat(new Scores(List.of(scoreA, scoreB)).containsSpare()).isEqualTo(expectedResult);
    }

    @Test
    void shouldReturnHasScoreOrNot() {
        assertThat(new Scores(List.of(1)).hasScore()).isTrue();
        assertThat(new Scores().hasScore()).isFalse();
    }

    @Test
    void shouldReturnHasTwoScoreOrNot() {
        assertThat(new Scores(List.of(1, 2)).hasTwoScore()).isTrue();
        assertThat(new Scores(List.of(1)).hasTwoScore()).isFalse();
    }

    @Test
    void shouldTestFirstScoreIsStrike() {
        assertThat(new Scores(List.of(1, 2)).isFirstScoreStrike()).isFalse();
        assertThat(new Scores().isFirstScoreStrike()).isFalse();
        assertThat(new Scores(List.of(10, 1)).isFirstScoreStrike()).isTrue();
    }


}
