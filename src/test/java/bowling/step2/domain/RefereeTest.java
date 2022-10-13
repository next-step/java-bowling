package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import bowling.step2.dto.PlayerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RefereeTest {
    Referee referee;
    Player player;
    PlayerDTO playerDTO;
    
    @BeforeEach
    void setUp() {
        referee = new Referee();
        player = new Player("SJH");
        playerDTO = new PlayerDTO(player);
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크")
    void get_cumulative_score_strike() {
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("20");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스페어")
    void get_cumulative_score_spare() {
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("3"));
        player.bowl(new CountOfFallenPinsDTO("6"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("13");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 미스")
    void get_cumulative_score_miss() {
        player.bowl(new CountOfFallenPinsDTO("3"));
        player.bowl(new CountOfFallenPinsDTO("6"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("9");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 9 프레임 스트라이크")
    void get_cumulative_score_ninth_frame_strike() {
        IntStream.rangeClosed(1, 8).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("10"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 8);
        assertThat(cumulativeScore).isEqualTo("30");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 9 프레임 스페어")
    void get_cumulative_score_ninth_frame_spare() {
        IntStream.rangeClosed(1, 8).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("4"));
        player.bowl(new CountOfFallenPinsDTO("6"));
        player.bowl(new CountOfFallenPinsDTO("6"));
        assertAll(
                () -> assertThat(referee.calculateCumulativeScore(playerDTO, 8)).isEqualTo("16"),
                () -> player.bowl(new CountOfFallenPinsDTO("4")),
                () -> assertThat(new Referee().calculateCumulativeScore(playerDTO, 8)).isEqualTo("16")
        );
    }
    
    @Test
    @DisplayName("누적 점수 구하기 - 스트라이크 2번 => 노멀")
    void get_cumulative_score_final_frame_two_strike_normal() {
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("25");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 마지막 프레임 - 스트라이크 3번")
    void get_cumulative_score_final_frame_three_strike() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("10"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("30");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 마지막 프레임 - 스트라이크 => 스페어")
    void get_cumulative_score_final_frame_strike_spare() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("20");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 마지막 프레임 - 스트라이크 => 미스")
    void get_cumulative_score_final_frame_strike_miss() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("4"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("19");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 마지막 프레임 - 스페어 => 노멀")
    void get_cumulative_score_final_frame_spare_normal() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("4"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("14");
    }
    
    @Test
    @DisplayName("누적 점수 구하기 마지막 프레임 - 미스")
    void get_cumulative_score_final_frame_miss() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("4"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("9");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 - 노멀")
    void stop_calculate_normal() {
        player.bowl(new CountOfFallenPinsDTO("1"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 - 스트라이크")
    void stop_calculate_strike() {
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("1"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 - 스페어")
    void stop_calculate_spare() {
        player.bowl(new CountOfFallenPinsDTO("4"));
        player.bowl(new CountOfFallenPinsDTO("6"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 마지막 프레임 - 스트라이크 => 노멀")
    void stop_calculate_final_frame_strike_normal() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 마지막 프레임 - 스트라이크 => 레디")
    void stop_calculate_final_frame_strike_ready() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 마지막 프레임 - 스페어 => 레디")
    void stop_calculate_final_frame_spare_ready() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 마지막 프레임 - 레디")
    void stop_calculate_final_frame_ready() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
    
    @Test
    @DisplayName("누적 점수 계산 정지 조건 마지막 프레임 - 노멀")
    void stop_calculate_final_frame_normal() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("5"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("-1");
    }
}
