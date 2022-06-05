package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FrameState;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingGamesTest {
    @DisplayName("볼링게임모음 객체 초기화")
    @Test
    void initialize() {
        List<Player> players = List.of(new Player("w2e"));
        assertThat(BowlingGames.initialize(players)).isNotNull().isInstanceOf(BowlingGames.class);
    }

    @DisplayName("플레이어 한명일 때 다음 플레이어는 등록된 한명의 플레이어이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    void playBowlingGame_플레이어_한명(int hitPins) {
        List<Player> players = List.of(new Player("w2e"));
        BowlingGames bowlingGames = BowlingGames.initialize(players);
        bowlingGames.playBowlingGame(new Pins(hitPins));
        assertThat(bowlingGames.isCurrentPlayerName("w2e")).isTrue();
    }

    @DisplayName("볼링게임 시작 후, 해당 프레임이 종료되면 다음 플레이어 차례가 된다. 프레임이 종료되지 않으면 계속 그 플레이어의 차례이다.")
    @ParameterizedTest
    @CsvSource(value = {
            "pob, w2e, 10, w2e",
            "pob, w2e, 9, pob"
    })
    void playBowlingGame_인덱스_증가(String player1, String player2, int hitPins, String resultPlayer) {
        List<Player> players = List.of(new Player(player1), new Player(player2));
        BowlingGames bowlingGames = BowlingGames.initialize(players);
        bowlingGames.playBowlingGame(new Pins(hitPins));
        assertThat(bowlingGames.isCurrentPlayerName(resultPlayer)).isTrue();
    }

    @DisplayName("볼링게임 시작 후, 해당 프레임의 모든 플레이어가 종료되면 다음 플레이어 차례는 처음 투구를 한 사람부터 시작한다.")
    @Test
    void playBowlingGame_프레임_모두_종료_후_처음_투구한_사람() {
        List<Player> players = List.of(new Player("pob"), new Player("w2e"));
        BowlingGames bowlingGames = BowlingGames.initialize(players);
        bowlingGames.playBowlingGame(new Pins(10));
        bowlingGames.playBowlingGame(new Pins(10));
        assertThat(bowlingGames.isCurrentPlayerName("pob")).isTrue();
    }

    @DisplayName("모든 선수의 볼링게임이 끝날 경우 볼링 게임은 종료된다. 한 선수라도 볼링게임이 끝나지 않으면 볼링 게임은 아직 진행 중이다.")
    @ParameterizedTest
    @MethodSource("state_provider")
    void isRunning_모두_종료(FrameState finalState, int bowlCount, boolean trueOrFalse) {
        // given 1
        List<Frame> frames1 = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames1.add(new NormalFrame(new Strike()));
        }
        frames1.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new Strike())), 3));
        Frames resultFrames1 = new Frames(frames1, 9);
        BowlingGame bowlingGame1 = new BowlingGame(new Player("pob"), resultFrames1);

        // given 2
        List<Frame> frames2 = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames2.add(new NormalFrame(new Strike()));
        }
        frames2.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), finalState)), bowlCount));
        Frames resultFrames2 = new Frames(frames2, 9);
        BowlingGame bowlingGame2 = new BowlingGame(new Player("w2e"), resultFrames2);


        BowlingGames bowlingGames = new BowlingGames(List.of(bowlingGame1, bowlingGame2));

        // when & then
        assertThat(bowlingGames.isRunning()).isEqualTo(trueOrFalse);
    }

    static Stream<Arguments> state_provider() {
        return Stream.of(
                arguments(new BeforeProgress(), 2, true),
                arguments(new Strike(), 3, false)
        );
    }
}