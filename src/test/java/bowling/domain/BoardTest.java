package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.ScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList()),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList())
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
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList())
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
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20))
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
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20, 14, 9))
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
                () -> assertThat(board.isFrameFinished())
                        .isFalse(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS, FrameEnum.UNFINISHED)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20, 14, 9))
        );
    }
}
