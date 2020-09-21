package bowling.domain.score;

import bowling.domain.roll.Roll;
import bowling.domain.roll.RollType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreManagerTest {

    @Test
    @DisplayName("업데이트 결과에 따라 옵저버 삭제 (항상 실패하는 옵저버)")
    void testNotifyObservers() {
        // given
        ScoreManager scoreManager = new ScoreManager();
        ScoreObserver emptyObserver = score -> false;
        scoreManager.registerObserver(emptyObserver);
        // when
        scoreManager.notifyObservers(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(scoreManager.observerSize()).isEqualTo(0);
    }
}
