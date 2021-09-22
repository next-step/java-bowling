package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    @DisplayName("생성 - 10개의 프레임이 존재하는데, 앞부터 9개는 노멀,마지막은 파이널")
    void create() {
        assertDoesNotThrow(() -> new Frames().equals(new Frames()));
    }

    @Test
    @DisplayName("finish 에서 투구할 경우 에러")
    void roll_finish_exception() {
        Frames frames = new Frames();
        finishGame(frames);
        assertThrows(FinishGameException.class, () -> frames.roll(Score.ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TEN,TEN", "TWO,EIGHT", "ONE"})
    @DisplayName("점수 계산이 끝나지 않음")
    void addingUpScore_InProgress(final String scores) {

        Frames frames = new Frames();
        for (String score : scores.split(",")) {
            frames.roll(Score.valueOf(score));
        }
        assertFalse(frames.isFinishAddingUpScores(0));
    }

    @ParameterizedTest
    @CsvSource(value = {"TEN,TEN,TEN:30", "TWO,EIGHT,FOUR:14", "ONE,EIGHT:9"}, delimiter = ':')
    @DisplayName("점수 계산이 끝남")
    void addingUpScore_finish(final String scores, final int result) {

        Frames frames = new Frames();
        for (String score : scores.split(",")) {
            frames.roll(Score.valueOf(score));
        }

        assertTrue(frames.isFinishAddingUpScores(0));
        assertEquals(result, frames.totalScore(0));
    }

    private void finishGame(final Frames frames) {
        IntStream.range(0, 12)
                .forEach(i -> frames.roll(Score.TEN));
    }
}