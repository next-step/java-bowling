package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.ScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {

    private Board board;
    private Rolls rolls;

    @BeforeEach
    void setUp() {
        board = new Board();
        rolls = new Rolls();
    }

    private void addRoll(Roll roll) {
        rolls.add(roll);
        board.update(rolls);
    }

    private List<FrameEnum> toFrameEnumList(Board board) {
        return board.exportBoardDto()
                .getFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    private List<Integer> toScoreList(Board board) {
        return board.exportBoardDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(board.frameNo())
                        .isEqualTo(1),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 치는 시나리오 테스트")
    void scenario_strike() {
        addRoll(Roll.of(10));
        assertAll(
                () -> assertThat(board.frameNo())
                        .isEqualTo(2),
                () -> assertThat(board.isStrike())
                        .isTrue(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));
        assertAll(
                () -> assertThat(board.frameNo())
                        .isEqualTo(3),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isTrue(),
                () -> assertThat(board.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE, MISS 를 치는 시나리오 테스트")
    void scenario_strike_spare_miss() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));

        addRoll(Roll.of(4));
        addRoll(Roll.of(5));
        assertAll(
                () -> assertThat(board.frameNo())
                        .isEqualTo(4),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("UNFINISHED 시나리오 테스트")
    void scenario_unfinished() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));

        addRoll(Roll.of(4));
        addRoll(Roll.of(5));

        addRoll(Roll.of(8));
        assertAll(
                () -> assertThat(board.frameNo())
                        .isEqualTo(4),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS, FrameEnum.UNFINISHED)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @ParameterizedTest
    @DisplayName("MAX_FRAME_NO 이하이면 Game Over 가 아니다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void isGameOver_false(int frameNo) {
        for (int i = 0; i < frameNo; i++) {
            addRoll(Roll.of(10));
        }

        assertThat(board.isGameOver())
                .isFalse();
    }

    @Test
    @DisplayName("MAX_FRAME_NO 을 넘기면 Game Over 가 된다.")
    void isGameOver_true() {
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            addRoll(Roll.of(10));
        }

        assertThat(board.isGameOver())
                .isTrue();
    }
}
