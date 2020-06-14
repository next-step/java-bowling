package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        this.answers = new Answers(Arrays.asList(AnswerTest.A1));
    }

    @DisplayName("컬렉션 객체를 만들 수 있다.")
    @Test
    void canCreateAnswers() {
        assertThat(this.answers).isInstanceOf(Answers.class);
    }

    @DisplayName("다른 사람이 쓴 답변이 있어 삭제할 수 없다.")
    @Test
    void canNotDeleteOtherOwner() {
        assertThatThrownBy(() -> this.answers.validateOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제 리스트를 얻을 수 있다.")
    @Test
    void canCreateDeleteHistories() {
        assertThat(this.answers.createDeleteHistories()).isInstanceOf(List.class);
    }
}
