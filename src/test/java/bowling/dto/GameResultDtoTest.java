package bowling.dto;

import bowling.domain.result.GameResultTest;
import org.junit.jupiter.api.Test;

class GameResultDtoTest {

    @Test
    void createTest() {
        GameResultDto dto = GameResultDto.of(GameResultTest.MIZ_STRIKE);
    }
}
