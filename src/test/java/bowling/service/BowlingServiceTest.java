package bowling.service;

import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;
import bowling.domain.user.UserTest;
import bowling.service.dto.BoardDto;
import bowling.service.dto.FrameResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingServiceTest {

    private BowlingService bowlingService;

    @BeforeEach
    void setUp() {
        bowlingService = new BowlingService(BowlingGame.readyGame(UserTest.MIZ));
    }

    @DisplayName("bowl 호출 시 BoardDto 객체를 반환한다.")
    @Test
    void bowlTest() {
        BoardDto boardDto = bowlingService.bowl(Pin.TEN);
        List<FrameResultDto> frameResultDtos = boardDto.getGameResultDto().getFrameResultsDto().getFrameResultDtos();
        assertThat(frameResultDtos.get(0).getPins()).isEqualTo(Arrays.asList(Pin.TEN));
    }
}
