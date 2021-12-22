package bowling.model.gameresult;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    @Test
    @DisplayName("객체 생성시 사이즈 10인 리스트 생성 테스트")
    void initSizeTest(){
        assertThat(new GameResults().getGameResults().size()).isEqualTo(10);
    }
}
