package domain;

import View.OutView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    private final int FIRST_POINT = 2;
    private final int SECOND_POINT = 3;
    private final int THIRD_POINT = 4;
    private final int SPARE_POINT = 8;
    private final int STRIKE_POINT = 10;

    private Bowling bowling;

    @BeforeEach
    void setUp() {
        bowling = new Bowling();

        bowling.playBowling(FIRST_POINT);
        bowling.playBowling(SPARE_POINT);
        bowling.getNextFrame();
        bowling.playBowling(STRIKE_POINT);
    }

    @DisplayName("넘어뜨린 핀 포맷된 결과 출력")
    @Test
    void getFormattedPointResultTest() {
        final String SCORE_CONNECTOR = " | ";
        final String INNER_CONNECTOR = "|";

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-4s", FIRST_POINT + INNER_CONNECTOR + PointName.valueOfPointName(SPARE_POINT, true)) + SCORE_CONNECTOR);
        builder.append(String.format("%-4s", PointName.valueOfPointName(STRIKE_POINT, false)) + SCORE_CONNECTOR);

        assertThat(bowling.getFormattedPointResult().contains(builder.toString())).isTrue();
    }

    @DisplayName("넘어뜨린 총 점 포맷된 결과 출력")
    @Test
    void getFormattedScoreResultTest() {
        final String SCORE_CONNECTOR = " | ";

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-4s", FIRST_POINT+SPARE_POINT+STRIKE_POINT) + SCORE_CONNECTOR);
        builder.append(String.format("%-4s", "") + SCORE_CONNECTOR);

        assertThat(bowling.getFormattedScoreResult().contains(builder.toString())).isTrue();
    }

    @DisplayName("게임 종료 여부 확인")
    @Test
    void isBowlingEndTest() {
        assertThat(bowling.isBowlingEnd()).isFalse();
    }

    @DisplayName("특정 프레임번호의 프레임 출력")
    @Test
    void getFrameTest() {
        final int STRIKE_FRAME_NUMBER = 2;
        assertThat(bowling.getFrame(STRIKE_FRAME_NUMBER).getState().getNameOfState()).isEqualTo("Strike");
    }
}
