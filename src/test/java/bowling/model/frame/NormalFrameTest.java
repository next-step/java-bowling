package bowling.model.frame;

import bowling.model.Score;
import bowling.model.delivery.NormalDeliveryEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private static Stream<Arguments> provideForRoll() {
        return Stream.of(
                Arguments.of(2, 8, NormalDeliveryEntry.of(2)),
                Arguments.of(3, 0, NormalDeliveryEntry.of(3)),
                Arguments.of(4, 5, NormalDeliveryEntry.of(4)));
    }

    @ParameterizedTest
    @MethodSource("provideForRoll")
    @DisplayName("투구 진행하기")
    void roll(int firstPins, int secondPins, NormalDeliveryEntry expectedEntry) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        expectedEntry.roll(secondPins);
        NormalFrame expected = new NormalFrame(expectedEntry);

        // when
        Frame result = normalFrame.roll(secondPins);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForIsEnd() {
        return Stream.of(
                Arguments.of(false, 2, null),
                Arguments.of(true, 1, 5),
                Arguments.of(true, 3, 7),
                Arguments.of(true, 10, null));
    }

    @ParameterizedTest
    @MethodSource("provideForIsEnd")
    @DisplayName("투구 끝났는지 확인하기")
    void isEnd(boolean expected, Integer firstPins, Integer secondPins) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        if (Objects.nonNull(secondPins)) {
            normalFrame = normalFrame.roll(secondPins);
        }

        // when
        boolean result = normalFrame.isEnd();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForCalculate() {
        return Stream.of(
                Arguments.of(2, 8, Score.of(9, 1) , Score.of(11, 0)),
                Arguments.of(3, 4, Score.of(10, 2), Score.of(17, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCalculate")
    @DisplayName("이전 프레임의 추가 점수 계산하기")
    void calculateAdditionalScore(int firstPins, int secondPins, Score beforeScore, Score expected) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        normalFrame.roll(secondPins);

        // when
        normalFrame.calculateAdditionalScore(beforeScore);

        // then
        assertThat(beforeScore).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForGetScore() {
        return Stream.of(
                Arguments.of(2, 8, Score.of(10, 1)),
                Arguments.of(3, 4, Score.of(7, 0)),
                Arguments.of(0, 9, Score.of(9, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetScore")
    @DisplayName("현재 프레임의 점수 계산하기 : 추가 점수가 없거나, 아직 다음 프레임이 없는 경우")
    void getScore_NO_ADDITIONAL_SCORE(int firstPins, int secondPins, Score expected) {
        // given
        Frame normalFrame = NormalFrame.of(firstPins);
        normalFrame.roll(secondPins);

        // when
        Score result = normalFrame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    //getScore
    private static Stream<Arguments> provideForGetScoreWithBonus() {
        return Stream.of(
                Arguments.of(2, 8, Score.of(20, 0)),
                Arguments.of(10, null, Score.of(22, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForGetScoreWithBonus")
    @DisplayName("현재 프레임의 점수 계산하기 : 추가 점수가 있는 경우")
    void getScore_ADDITIONAL_SCORE(Integer firstPins, Integer secondPins, Score expected) {
        // given
        Frame frame = NormalFrame.of(firstPins);
        if (Objects.nonNull(secondPins)) {
            frame = frame.roll(secondPins);
        }

        Frame secondFrame = NormalFrame.of(10);
        Frame thirdFrame = NormalFrame.of(2).roll(6);

        frame.next = secondFrame;
        secondFrame.next = thirdFrame;

        // when
        Score result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

}
