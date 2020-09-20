package bowling.domain;


import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {


    @Test
    void strikeScoreTest(){
        Score score = new Score(10,2);
        assertThat(score.bowl(new Pin(5)).bowl(new Pin(5)).getScore()).isEqualTo(20);
    }

    @Test
    void spareScoreTest(){
        Score score = new Score(10,1);
        assertThat(score.bowl(new Pin(5)).getScore()).isEqualTo(15);
    }

    @Test
    void cannotCalculateTest(){
        assertThatThrownBy(() -> {
            new Score(10,2).getScore();
        }).isInstanceOf(CannotCalculateException.class);
    }

}
