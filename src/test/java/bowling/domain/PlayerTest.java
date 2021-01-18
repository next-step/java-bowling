package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("이름으로 객체를 생성할 수 있다")
    @Test
    void create(){
        assertThat(new Player("NIO")).isEqualTo(new Player("NIO"));
    }

    @DisplayName("이름은 최대 3글자 까지 허용합니다")
    @Test
    void maxLength(){
        assertThatThrownBy(() -> new Player("HOON") )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름은 최소 1글자 이상 입력해야 합니다")
    void blankName(){
        assertThatThrownBy(() -> new Player("") )
                .isInstanceOf(IllegalArgumentException.class);
    }
}
