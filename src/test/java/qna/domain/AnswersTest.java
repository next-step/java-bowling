package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    @DisplayName("기본생성자로 생성된 Answers는 서로 같은 상태다.")
    void create() {
        assertThat(new Answers()).isEqualTo(new Answers());
    }

    @Test
    @DisplayName("기본생성자로 생성된 Answers와 빈 List로 생성한 Answers는 서로 같은 상태다.")
    void create_with_emptyList() {
        assertThat(new Answers(new ArrayList<>())).isEqualTo(new Answers());
    }

    @Test
    @DisplayName("answers에 answer를 추가할 수 있다.")
    void add() {
        // given
        final Answers answers = new Answers();

        // when
        answers.add(new Answer());

        // then
        assertThat(answers.size()).isEqualTo(1);
    }
}
