package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    private void finishGame(final Frames frames) {
        IntStream.range(0, 12)
                .forEach(i -> frames.roll(Score.TEN));
    }
}