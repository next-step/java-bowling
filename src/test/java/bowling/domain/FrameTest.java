package bowling.domain;

import bowling.domain.scoreType.ScoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class FrameTest {

    @Test
    void of() {
        assertThatCode(() -> Frame.init())
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        Frame normalFrame = Frame.init();
        normalFrame.shot(4);
        assertThat(normalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.MISS_FIRST.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 4);

        normalFrame.shot(6);
        assertThat(normalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.SPARE.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 6);

        assertThatThrownBy(() -> normalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void next() {
        Frame normalFrame = Frame.init();
        assertThatCode(() -> normalFrame.next(5))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> normalFrame.next(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10"})
    void isClosed(String shotString) {
        Frame normalFrame = Frame.init();
        assertThat(normalFrame.isFrameSet())
                .isFalse();

        int[] shots = splitInts(shotString);
        for (int shot : shots) {
            normalFrame.shot(shot);
        }

        assertThat(normalFrame.isFrameSet())
                .isTrue();
    }

    @Test
    void shotLastFrame() {
        Frame finalFrame = Frame.init().last(4);
        assertThat(finalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.MISS_FIRST.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 4);

        finalFrame.shot(6);
        assertThat(finalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.SPARE.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 6);

        finalFrame.shot(5);

        assertThatThrownBy(() -> finalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10,10,10", "4,6,4", "10,4,5"})
    void isClosedLastFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.init().last(shots[0]);
        assertThat(finalFrame.isFrameSet())
                .isFalse();

        for (int i = 1; i < shots.length; i++) {
            finalFrame.shot(shots[i]);
        }

        assertThat(finalFrame.isFrameSet())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "10,10", "4,6", "10,4"})
    void isNotClosedFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.init().last(shots[0]);
        assertThat(finalFrame.isFrameSet())
                .isFalse();

        for (int i = 1; i < shots.length; i++) {
            finalFrame.shot(shots[i]);
        }

        assertThat(finalFrame.isFrameSet())
                .isFalse();
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Test
    void getFrameScore() {
        Frame firstFrame = Frame.init();
        firstFrame.shot(4);
        firstFrame.shot(4);
        assertThat(firstFrame.getFrameScore())
                .isEqualTo(8);


        firstFrame = Frame.init();
        firstFrame.shot(4);
        firstFrame.shot(6);
        firstFrame.next(10);
        assertThat(firstFrame.getFrameScore())
                .isEqualTo(20);

        firstFrame = Frame.init();
        firstFrame.shot(10);
        firstFrame.next(10).next(10);
        assertThat(firstFrame.getFrameScore())
                .isEqualTo(30);

        firstFrame = Frame.init();
        firstFrame.shot(10);
        firstFrame.next(4).shot(3);
        assertThat(firstFrame.getFrameScore())
                .isEqualTo(17);
    }

    @Test
    void isScoreCalculated(){
        Frame frame = Frame.init();
        assertThat(frame.isScoreCalculated())
                .isFalse();

        frame.shot(4);
        frame.shot(5);
        assertThat(frame.isScoreCalculated())
                .isTrue();

        frame = frame.next(10);
        assertThat(frame.isScoreCalculated())
                .isFalse();

        frame.next(4).shot(4);
        assertThat(frame.isScoreCalculated())
                .isTrue();

        frame = frame.next(4);
        frame.shot(6);
        assertThat(frame.isScoreCalculated())
                .isFalse();

        frame.next(5);
        assertThat(frame.isScoreCalculated())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "10,4,3",
            "4,3",
            "1,9,2",
            "10,10,3"
    })
    void isScoreCalculatedLastFrame(String shotString){
        int[] shots = splitInts(shotString);
        Frame frame = Frame.init();
        frame = frame.last(shots[0]);
        for (int i = 1; i < shots.length; i++) {
            frame.shot(shots[i]);
        }
        assertThat(frame.isScoreCalculated())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "10,4",
            "4",
            "1,9",
            "10,10"
    })
    void isNotScoreCalculatedLastFrame(String shotString){
        int[] shots = splitInts(shotString);
        Frame frame = Frame.init();
        frame = frame.last(shots[0]);
        for (int i = 1; i < shots.length; i++) {
            frame.shot(shots[i]);
        }
        assertThat(frame.isScoreCalculated())
                .isFalse();
    }

}
