package bowling.domain.frame;

import bowling.domain.Swing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class NormalFrameTest {

    @DisplayName("Frame 생성")
    @Test
    void frameInstanceCreate() {
        assertThat(FrameFactory.next(1)).isInstanceOf(AbstractFrame.class);
    }

    @DisplayName("투구 점수가 범위를 벗어난 경우")
    @ValueSource(ints = { -1, 11 })
    @ParameterizedTest
    void swingFailCase(int score) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new NormalFrame()).swing(score));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> (new NormalFrame()).swing(score));
    }

    @DisplayName("한 프레임 당 점수는 합쳐서 10점")
    @CsvSource(value = {"9,3", "5,7", "4,8"})
    @ParameterizedTest
    void overScoreCase(int score1, int score2) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            NormalFrame frame = new NormalFrame();
            frame.swing(score1);
            frame.swing(score2);
        });
    }

    @DisplayName("NormalFrame은 swing을 두 번 하면 종료")
    @CsvSource(value = {"1,2", "3,4"})
    @ParameterizedTest
    void only2swing(int score1, int score2) {

        NormalFrame frame = new NormalFrame();
        frame.swing(score1);
        frame.swing(score2);

        assertTrue(frame.isEndedFrame());
    }

    @DisplayName("spare나 strike 일 때 점수를 반환하면 DUMMY SCORE")
    @Test
    void getScoreAtStrikeAndSpareCase() {

        NormalFrame frame = new NormalFrame();
        frame.swing(10);

        assertEquals(frame.getScore(), Swing.DUMMY_SCORE);

        frame = new NormalFrame();
        frame.swing(3);
        frame.swing(7);

        assertEquals(frame.getScore(), Swing.DUMMY_SCORE);
    }

    @DisplayName("spare 점수 테스트")
    @Test
    void spareScoreTest() {

        NormalFrame frame = new NormalFrame();
        frame.swing(0);
        frame.swing(10);

        Random random = new Random();
        int addScore = random.nextInt();

        frame.updateScore(addScore);
        assertEquals(frame.getScore(), 10 + addScore);
        assertFalse(frame.needUpdate());
    }

    @DisplayName("strike 점수 테스트")
    @Test
    void strikeScoreTest() {

        NormalFrame frame = new NormalFrame();
        frame.swing(10);

        Random random = new Random();
        int addScore1 = random.nextInt();
        int addScore2 = random.nextInt();

        frame.updateScore(addScore1);

        // strike는 2번 업데이트 하지 않으면 DUMMY 반환
        assertEquals(frame.getScore(), Swing.DUMMY_SCORE);

        frame.updateScore(addScore2);
        assertEquals(frame.getScore(), 10 + addScore1 + addScore2);
        assertFalse(frame.needUpdate());
    }
}
