package bowling.domain;

import bowling.domain.State.Ready;
import bowling.domain.State.Strike;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    @DisplayName("두 플레이어 모두 맨 처음 프레임에 있을때 첫번쨰 플레이어가 나오는지 테스트")
    void playing_player_when_two_players_start() {
        int totalNumberOfFrame = 10;
        Player firstPlayerInSecondFrame = new Player("fir", totalNumberOfFrame);
        Player secondPlayerInFirstFrame = new Player("sec", totalNumberOfFrame);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));

        assertThat(players.playingPlayer()).isEqualTo(firstPlayerInSecondFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번쨰 프레임을 끝낸상태이고 두번째 플레이어는 한번도 플레이 하지 않은 상태에서 현재 플레이하는 플레이어는 두번재 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_not_played() {
        int totalNumberOfFrame = 10;
        Player firstPlayerInSecondFrame = new Player("fir", totalNumberOfFrame);
        Player secondPlayerInFirstFrame = new Player("sec", totalNumberOfFrame);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);

        assertThat(players.playingPlayer()).isEqualTo(secondPlayerInFirstFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번째 프레임을 끝낸상태이고 두번째 플레이어는 스트라이크가 아닌 투구를 상태에서 현재 플레이하는 플레이어는 두번재 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_is_still_playing_in_second_frame() {
        int totalNumberOfFrame = 10;
        Player firstPlayerInSecondFrame = new Player("fir", totalNumberOfFrame);
        Player secondPlayerInFirstFrame = new Player("sec", totalNumberOfFrame);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);
        players.play(5);

        assertThat(players.playingPlayer()).isEqualTo(secondPlayerInFirstFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번쨰 프레임을 끝낸상태이고 두번째 플레이어도 첫번쨰 프레임을 끝낸상태에서 현재 플레이하는 플레이어는 첫번째 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_just_finished_second_frame() {
        int totalNumberOfFrame = 10;
        Player firstPlayerInSecondFrame = new Player("fir", totalNumberOfFrame);
        Player secondPlayerInFirstFrame = new Player("sec", totalNumberOfFrame);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);
        players.play(5);
        players.play(5);

        assertThat(players.playingPlayer()).isEqualTo(firstPlayerInSecondFrame);
    }


    @Test
    @DisplayName("첫번째 플레이어는 모든 프레임을 끝낸상태이고 두번째 플레이어는 마지막 프레임만 남겨둔 상태에서 두번째 플레이어 인지 테스트")
    void playing_player_when_first_player_is_done_and_second_player_has_last_frame_to_play() {
        FinalFrame finalFrameForFirstPlayer = FinalFrame.from(3);
        NormalFrame secondStrikeFrame = NormalFrame.of(new FrameNumber(2), new Strike(), finalFrameForFirstPlayer);
        NormalFrame firstStrikeNormalFrame = NormalFrame.of(new FrameNumber(1), new Strike(), secondStrikeFrame);
        Frames firstPlayerFramesWithOnlyLastFrameToPlay = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondStrikeFrame, finalFrameForFirstPlayer));

        FinalFrame finalFrameForSecondPlayer = FinalFrame.from(3);
        Frames secondPlayerFramesWithOnlyLastFrameToPlay = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondStrikeFrame, finalFrameForSecondPlayer));

        Player firstPlayerInSecondFrame = new Player("fir", firstPlayerFramesWithOnlyLastFrameToPlay);
        Player secondPlayerInFirstFrame = new Player("sec", secondPlayerFramesWithOnlyLastFrameToPlay);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        playLastFrameOfFirstPlayer(players);

        assertThat(players.playingPlayer()).isEqualTo(secondPlayerInFirstFrame);
    }

    private void playLastFrameOfFirstPlayer(Players players){
        players.play(5);
        players.play(4);
    }


}
