package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    @DisplayName("두 플레이어 모두 맨 처음 프레임에 있을때 첫번쨰 플레이어가 나오는지 테스트")
    void playing_player_when_two_players_start() {
        Player firstPlayerInSecondFrame = new Player("fir", 10);
        Player secondPlayerInFirstFrame = new Player("sec", 10);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));

        assertThat(players.playingPlayer()).isEqualTo(firstPlayerInSecondFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번쨰 프레임을 끝낸상태이고 두번째 플레이어는 한번도 플레이 하지 않은 상태에서 현재 플레이하는 플레이어는 두번재 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_not_played() {
        Player firstPlayerInSecondFrame = new Player("fir", 10);
        Player secondPlayerInFirstFrame = new Player("sec", 10);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);

        assertThat(players.playingPlayer()).isEqualTo(secondPlayerInFirstFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번째 프레임을 끝낸상태이고 두번째 플레이어는 스트라이크가 아닌 투구를 상태에서 현재 플레이하는 플레이어는 두번재 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_is_still_playing_in_second_frame() {
        Player firstPlayerInSecondFrame = new Player("fir", 10);
        Player secondPlayerInFirstFrame = new Player("sec", 10);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);
        players.play(5);

        assertThat(players.playingPlayer()).isEqualTo(secondPlayerInFirstFrame);
    }

    @Test
    @DisplayName("첫번째 플레이어는 첫번쨰 프레임을 끝낸상태이고 두번째 플레이어도 첫번쨰 프레임을 끝낸상태에서 현재 플레이하는 플레이어는 첫번째 플레이어 인지 테스트")
    void playing_player_when_first_player_in_second_frame_and_second_player_just_finished_second_frame() {
        Player firstPlayerInSecondFrame = new Player("fir", 10);
        Player secondPlayerInFirstFrame = new Player("sec", 10);

        Players players = new Players(Arrays.asList(firstPlayerInSecondFrame, secondPlayerInFirstFrame));
        players.play(10);
        players.play(5);
        players.play(5);

        assertThat(players.playingPlayer()).isEqualTo(firstPlayerInSecondFrame);
    }




}
