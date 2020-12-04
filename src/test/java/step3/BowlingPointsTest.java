package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.BowlingPoint;
import step3.domain.BowlingPoints;
import step3.exception.InvalidPitchesException;
import step3.type.PitchesOrderType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingPointsTest {

    @DisplayName("BowlingPoints 생성 테스트")
    @Test
    void createBowlingPoints() {
        BowlingPoints bowlingPoints = BowlingPoints.of(2);
        assertThat(bowlingPoints.isCompleted()).isFalse();
        assertThat(bowlingPoints.size()).isEqualTo(0);
        assertThat(bowlingPoints).isEqualTo(
                new BowlingPoints(
                        new HashMap<>(),
                        2,
                        false));
    }

    @DisplayName("push 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,0", "0,10", "5,5", "0,0", "1,5", "1,9"})
    void push(int first, int second) {
        HashMap<PitchesOrderType, BowlingPoint> map = new HashMap<PitchesOrderType, BowlingPoint>() {{
            put(PitchesOrderType.FIRST, BowlingPoint.of(first));
            put(PitchesOrderType.SECOND, BowlingPoint.of(second));
        }};
        BowlingPoints points = BowlingPoints.of(2);

        points.push(first);
        points.push(second);

        BowlingPoints bowlingPoints = new BowlingPoints(map, 2, true);

        assertThat(points).isEqualTo(bowlingPoints);
    }

    @DisplayName("getScore 테스트")
    @ParameterizedTest
    @MethodSource("provideBowlingPointsAndScore")
    void getScore(BowlingPoints points, int score) {
        assertThat(points.getScore()).isEqualTo(score);
    }

    private static Stream<Arguments> provideBowlingPointsAndScore() {
        BowlingPoints points1 = BowlingPoints.of(2).push(5)
                .push(5);

        BowlingPoints points2 = BowlingPoints.of(3).push(5)
                .push(5)
                .push(4);

        return Stream.of(
                Arguments.of(points1, 10),
                Arguments.of(points2, 14)
        );
    }

    @DisplayName("NormalFrame push 예외 테스트")
    @ParameterizedTest
    @MethodSource("providePitchesContAndValue")
    void normalPushWithException(int size, List<Integer> numbers, Class<RuntimeException> classType) {
        assertThatThrownBy(()->{
            BowlingPoints bowlingPoints = BowlingPoints.of(size);
            numbers.forEach(bowlingPoints::push);
        }).isInstanceOf(classType);
    }

    private static Stream<Arguments> providePitchesContAndValue() {
        return Stream.of(
                Arguments.of(2, Arrays.asList(10,10), InvalidPitchesException.class),
                Arguments.of(2, Arrays.asList(4,6,7), InvalidPitchesException.class),
                Arguments.of(2, Arrays.asList(5,5,5), IllegalArgumentException.class),
                Arguments.of(2, Arrays.asList(6,5), InvalidPitchesException.class),
                Arguments.of(3, Arrays.asList(10,10,11), IllegalArgumentException.class),
                Arguments.of(3, Arrays.asList(10,10,10,10), IllegalArgumentException.class),
                Arguments.of(3, Arrays.asList(5,4,5), IllegalArgumentException.class)
        );
    }



    @DisplayName("완료 확인 테스트")
    @Test
    void complete() {
        BowlingPoints normalPoints = BowlingPoints.of(2);
        BowlingPoints finalPoints = BowlingPoints.of(3);
        BowlingPoints finalPoints2 = BowlingPoints.of(3);
        normalPoints.push(3);
        assertThat(normalPoints.isCompleted()).isFalse();

        normalPoints.push(5);
        assertThat(normalPoints.isCompleted()).isTrue();

        finalPoints.push(4);
        assertThat(finalPoints.isCompleted()).isFalse();

        finalPoints.push(6);
        assertThat(finalPoints.isCompleted()).isFalse();

        finalPoints.push(4);
        assertThat(finalPoints.isCompleted()).isTrue();

        finalPoints2.push(5);
        finalPoints2.push(4);
        assertThat(finalPoints2.isCompleted()).isTrue();
    }



}
