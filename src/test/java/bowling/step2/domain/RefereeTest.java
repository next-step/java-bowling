package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import bowling.step2.dto.PlayerDTO;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("누적 점수 구하기 - 마지막 프레임")
    void stop_calculate_final_frame() {
        IntStream.rangeClosed(1, 9).forEach(count -> player.bowl(new CountOfFallenPinsDTO("10")));
        player.bowl(new CountOfFallenPinsDTO("10"));
        player.bowl(new CountOfFallenPinsDTO("5"));
        player.bowl(new CountOfFallenPinsDTO("4"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 9);
        assertThat(cumulativeScore).isEqualTo("19");
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
        player.bowl(new CountOfFallenPinsDTO("6"));
        final String cumulativeScore = referee.calculateCumulativeScore(playerDTO, 0);
        assertThat(cumulativeScore).isEqualTo("16");
    }
}
