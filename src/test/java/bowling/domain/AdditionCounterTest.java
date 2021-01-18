package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdditionCounterTest {

    @DisplayName("strike 는 counter 가 2 다")
    @Test
    void strikeAdditionCounter(){
        AdditionCounter counter = AdditionCounter.ofStrike();
        assertThat(counter).isEqualTo(AdditionCounter.of(2));
    }

    @DisplayName("spare 는 counter 가 1 다")
    @Test
    void spareAdditionCounter(){
        AdditionCounter counter = AdditionCounter.ofSpare();
        assertThat(counter).isEqualTo(AdditionCounter.of(1));
    }

    @DisplayName("count 를 다 했으면 isDone 은 true 를 return 한다")
    @Test
    void isDone(){
        AdditionCounter counter = AdditionCounter.of(1);
        counter.count();
        assertThat(counter.isDone()).isTrue();
    }

    @DisplayName("count 가 남았으면 isDone 은 false 를 return 한다")
    @Test
    void isNotDone(){
        AdditionCounter counter = AdditionCounter.of(3);
        counter.count();
        assertThat(counter.isDone()).isFalse();
    }


}