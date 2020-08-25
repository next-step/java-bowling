package bowling.domain.frame;

import bowling.domian.frame.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    @DisplayName("첫 프레임 생성 시 프레임 번호 1")
    public void generateNormalFrame() {
        NormalFrame normalFrame = NormalFrame.firstFrame();

        assertThat(normalFrame.getFrameNumber()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("첫 투구가 Gutter/Miss 시 프레임 유지 확인")
    public void bowlNormal(int falledPinsCount) {
        NormalFrame firstFrame = NormalFrame.firstFrame();

        Frame result = firstFrame.bowl(falledPinsCount);

        assertThat(firstFrame.equals(result)).isTrue();
    }

    @Test
    @DisplayName("Strike 시 다음 프레임 반환")
    public void bowlStrike() {
        NormalFrame firstFrame = NormalFrame.firstFrame();

        Frame result = firstFrame.bowl(10);

        assertThat(result.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("Spare 시 다음 프레임 반환")
    public void bowlSpare() {
        NormalFrame firstFrame = NormalFrame.firstFrame();

        Frame firstBowlResult = firstFrame.bowl(3);
        Frame secondBowlResult = firstBowlResult.bowl(7);

        assertThat(firstFrame.equals(firstBowlResult)).isTrue();
        assertThat(firstFrame.equals(secondBowlResult)).isFalse();
        assertThat(secondBowlResult.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("9번째 프레임 종료 시 FinalFrame 반환")
    public void returnFinalFrame() {
        NormalFrame firstFrame = NormalFrame.firstFrame();
        Frame result = firstFrame.bowl(10);

        for (int i = 2; i < 10; i++) {
            result = result.bowl(10);
        }

        assertThat(result instanceof FinalFrame).isTrue();
        assertThat(result.getFrameNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 추가 계산이 불필요한 경우 결과 반환")
    public void calculateScore() {
        NormalFrame frame = NormalFrame.firstFrame();

        frame.bowl(3).bowl(0)
                .bowl(3).bowl(4);

        FrameResult frameResult = frame.getFrameResult();

        assertThat(frameResult.getScore()).isEqualTo(3);
        assertThat(frameResult.getDesc()).isEqualTo("3|-");
    }

    @Test
    @DisplayName("Strike 점수 추가 계산 후 결과 반환")
    public void calculateStrikeAdditionalScore() {
        NormalFrame frame = NormalFrame.firstFrame();

        frame.bowl(10)
                .bowl(3).bowl(4);

        FrameResult frameResult = frame.getFrameResult();

        assertThat(frameResult.getScore()).isEqualTo(17);
        assertThat(frameResult.getDesc()).isEqualTo("X");
    }

    @Test
    @DisplayName("Spare 점수 추가 계산 후 결과 반환")
    public void calculateSpareAdditionalScore() {
        NormalFrame frame = NormalFrame.firstFrame();

        frame.bowl(3).bowl(7)
                .bowl(3).bowl(4);

        FrameResult frameResult = frame.getFrameResult();

        assertThat(frameResult.getScore()).isEqualTo(13);
        assertThat(frameResult.getDesc()).isEqualTo("3|/");
    }

    @Test
    @DisplayName("추가 계산이 끝나지 않은 경우 확인")
    public void calculateAdditionalScore() {
        NormalFrame frame = NormalFrame.firstFrame();

        frame.bowl(10).bowl(10);

        FrameResult frameResult = frame.getFrameResult();

        assertThat(frameResult.isCalculateDone()).isFalse();
    }

    @Test
    @DisplayName("투구 전일 경우 점수판에 추가하지 않음")
    public void notAddToBoardWhenReady() {
        NormalFrame frame = NormalFrame.firstFrame();
        Board board = new Board();

        Board result = frame.addBoard(board);

        assertThat(result).isEqualTo(result);
    }

    @Test
    @DisplayName("점수판에 투구 결과 추가")
    public void addToBoard() {
        NormalFrame frame = NormalFrame.firstFrame();
        Board board = new Board();

        frame.bowl(3).bowl(4);
        frame.addBoard(board);

        assertThat(board.getDescs().get(0)).isEqualTo("3|4");
        assertThat(board.getTotalScores().get(0)).isEqualTo(7);
    }

    @Test
    @DisplayName("Board 객체 반환 확인")
    public void getBoard() {
        NormalFrame frame = NormalFrame.firstFrame();
        Board board = new Board();

        frame.bowl(10)
                .bowl(3).bowl(7)
                .bowl(10)
                .bowl(0).bowl(0)
                .bowl(0).bowl(0)
                .bowl(8).bowl(0)
                .bowl(10)
                .bowl(0).bowl(10)
                .bowl(3).bowl(4)
                .bowl(10).bowl(4);

        System.out.println(frame.addBoard(board));
    }
}
