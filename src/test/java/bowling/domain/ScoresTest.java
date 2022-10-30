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

    private static Stream<Arguments> generateScore(){
        return Stream.of(
                Arguments.of(List.of(1,4,2)),
                Arguments.of(List.of(1,4,3,1,10,3))
        );
    }

    @ParameterizedTest
    @MethodSource("generateScoreAndWhetherNormalRoundEnded")
    void shouldReturnNormalRoundEndOrNot(List<Integer> inputs , boolean expectedResult) {
        Scores scores = new Scores();
        inputs.forEach(scores::add);
        assertThat(scores.isNormalRoundEnd()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateScoreAndWhetherNormalRoundEnded(){
        return Stream.of(
                Arguments.of(List.of(1,4),true),
                Arguments.of(List.of(10),true),
                Arguments.of(List.of(0),false),
                Arguments.of(List.of(5),false)
        );
    }
    @ParameterizedTest
    @MethodSource("generateScoreAndWhetherLastRoundEnded")
    void shouldReturnLastRoundEndOrNot(List<Integer> inputs , boolean expectedResult) {
        Scores scores = new Scores();
        inputs.forEach(scores::add);
        assertThat(scores.isLastRoundEnd()).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> generateScoreAndWhetherLastRoundEnded(){
        return Stream.of(
                Arguments.of(List.of(1,4),false),
                Arguments.of(List.of(1,4,10),true),
                Arguments.of(List.of(0),false),
                Arguments.of(List.of(10,10,10),true)
        );
    }

    @ParameterizedTest
    @CsvSource({"1,9,true","4,6,true","9,1,true","10,0,false","0,10,true"})
    void shouldValidateSecondSpare(int firstScore , int secondScore ,boolean expectedResult) {
        Scores scores = new Scores();
        scores.add(firstScore);
        scores.add(secondScore);

        assertThat(scores.isSecondPinSpare()).isEqualTo(expectedResult);
    }


    @ParameterizedTest
    @CsvSource({"10,1,9,true","0,1,9,true","0,0,10,true","0,10,0,false","10,10,10,false"})
    void shouldValidateThirdSpare(int firstScore , int secondScore , int thirdScore ,boolean expectedResult) {
        Scores scores = new Scores();
        scores.add(firstScore);
        scores.add(secondScore);
        scores.add(thirdScore);

        assertThat(scores.isThirdPinSpare()).isEqualTo(expectedResult);
    }
}
