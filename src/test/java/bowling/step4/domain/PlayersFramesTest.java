package bowling.step4.domain;

import bowling.step4.domain.frame.FinalFrame;
import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.frame.NormalFrame;
import bowling.step4.domain.scores.FinalScores;
import bowling.step4.domain.scores.NormalScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersFramesTest {

    @DisplayName("Players로 부터 PlayersFrames가 정상적으로 생성되는지 확인")
    @Test
    void playersFrames_생성_테스트 () {
        Players players = Players.of(asList(Player.valueOf("p01"), Player.valueOf("p02")));
        PlayersFrames playersFrames = PlayersFrames.init(players);
        assertEquals(
            players.stream().map(Player::toString).collect(joining(";")),
            playersFrames.stream().map(PlayerFrames::getPlayerName).collect(joining(";"))
        );
        assertEquals(
            players.stream().count(),
            playersFrames.stream().count()
        );
    }

    @DisplayName("PlayersFrames에서 frames의 현재 상태가 마지막 프레임인지 확인")
    @Test
    void 마지막_프레임_확인_테스트() {
        Frame finalFrame = FinalFrame.of(2, FinalScores.init());
        Frame normalFrame = NormalFrame.of(1, NormalScores.init(), finalFrame);
        PlayerFrames playerFrames = PlayerFrames.of(Player.valueOf("hji"), normalFrame);
        PlayersFrames playersFrames = PlayersFrames.of(singletonList(playerFrames));
        assertEquals(true, playersFrames.isLast());
    }
}