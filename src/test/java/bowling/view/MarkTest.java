package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.Scores;
import bowling.type.BowlingScore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static bowling.view.Mark.print;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkTest {

    static Stream<Arguments> markArgs() {
        return Stream.of(
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(10,6,3)), 10), "X|6|3"),
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(5,5,5)), 10), "5|/|5"),
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(5,5,10)), 10), "5|/|X"),
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(5,3)), 10), "5|3"),
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(10,6,4)), 10), "X|6|/"),
                Arguments.of(new FinalFrame(new Scores(Arrays.asList(10,10,10)), 10), "X|X|X"),
                Arguments.of(new NormalFrame(new Scores(Arrays.asList(10,3)), 7), "X|3"),
                Arguments.of(new NormalFrame(new Scores(Arrays.asList(0,10,3)), 7), "0|/"),
                Arguments.of(new NormalFrame(new Scores(Arrays.asList(5)), 7), "5")
        );
    }

    @ParameterizedTest
    @MethodSource("markArgs")
    void testMarkPrint(Frame frame, String result){
        assertThat(print(frame)).isEqualTo(result);
    }
}
