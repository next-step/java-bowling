package bowling.domain;

import bowling.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.domain.FrameEnum.*;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameTest {

    private Game game;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new Game(subject -> {
        });
        player = new Player("GHO");
    }

    @Test
    @DisplayName("STRIKE 만 나오는 볼림 게임 플레이 테스트")
    void strike() {
        game.addPlayer(player, num -> Roll.of(10));
        game.play();
        PlayerStatusDto status = game.exportScoreBoardDto()
                .getScoreBoard()
                .entrySet()
                .iterator()
                .next()
                .getValue();
        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);
        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 290)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("SPARE 만 나오는 볼림 게임 플레이 테스트")
    void spare() {
        game.addPlayer(player, num -> Roll.of(5));
        game.play();
        PlayerStatusDto status = game.exportScoreBoardDto()
                .getScoreBoard()
                .entrySet()
                .iterator()
                .next()
                .getValue();
        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("MISS 만 나오는 볼림 게임 플레이 테스트")
    void miss() {
        game.addPlayer(player, num -> Roll.of(1));
        game.play();
        PlayerStatusDto status = game.exportScoreBoardDto()
                .getScoreBoard()
                .entrySet()
                .iterator()
                .next()
                .getValue();
        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);
        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("멀티 플레이 테스트")
    void multi() {
        String name1 = "GHO";
        String name2 = "JYP";
        String name3 = "POB";

        game.addPlayer(new Player(name1), num -> Roll.of(10));
        game.addPlayer(new Player(name2), num -> Roll.of(5));
        game.addPlayer(new Player(name3), num -> Roll.of(1));
        game.play();
        Iterator<Entry<PlayerDto, PlayerStatusDto>> itr = game.exportScoreBoardDto()
                .getScoreBoard()
                .entrySet()
                .iterator();

        Entry<PlayerDto, PlayerStatusDto> entry1 = itr.next();
        PlayerDto player1 = entry1.getKey();
        PlayerStatusDto status1 = entry1.getValue();
        List<Integer> rolls1 = toRolls(status1);
        List<FrameEnum> frames1 = toFrames(status1);
        List<Integer> scores1 = toScores(status1);

        Entry<PlayerDto, PlayerStatusDto> entry2 = itr.next();
        PlayerDto player2 = entry2.getKey();
        PlayerStatusDto status2 = entry2.getValue();
        List<Integer> rolls2 = toRolls(status2);
        List<FrameEnum> frames2 = toFrames(status2);
        List<Integer> scores2 = toScores(status2);

        Entry<PlayerDto, PlayerStatusDto> entry3 = itr.next();
        PlayerDto player3 = entry3.getKey();
        PlayerStatusDto status3 = entry3.getValue();
        List<Integer> rolls3 = toRolls(status3);
        List<FrameEnum> frames3 = toFrames(status3);
        List<Integer> scores3 = toScores(status3);


        assertAll(
                () -> assertThat(player1.getName())
                        .isEqualTo(name1),
                () -> assertThat(rolls1)
                        .isEqualTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0)),
                () -> assertThat(frames1)
                        .isEqualTo(Arrays.asList(STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE)),
                () -> assertThat(scores1)
                        .isEqualTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 290)),
                () -> assertThat(frames1.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores1.size())
                        .isEqualTo(MAX_FRAME_NO),

                () -> assertThat(player2.getName())
                        .isEqualTo(name2),
                () -> assertThat(rolls2)
                        .isEqualTo(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5)),
                () -> assertThat(frames2)
                        .isEqualTo(Arrays.asList(SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE)),
                () -> assertThat(scores2)
                        .isEqualTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150)),
                () -> assertThat(frames2.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores2.size())
                        .isEqualTo(MAX_FRAME_NO),

                () -> assertThat(player3.getName())
                        .isEqualTo(name3),
                () -> assertThat(rolls3)
                        .isEqualTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)),
                () -> assertThat(frames3)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS)),
                () -> assertThat(scores3)
                        .isEqualTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)),
                () -> assertThat(frames3.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores3.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    private List<Integer> toRolls(PlayerStatusDto status) {
        return status.getRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }

    private List<FrameEnum> toFrames(PlayerStatusDto status) {
        return status.getBoardDto()
                .getFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    private List<Integer> toScores(PlayerStatusDto status) {
        return status.getBoardDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }
}
