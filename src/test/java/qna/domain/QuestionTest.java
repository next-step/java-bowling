package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제 성공")
    void deleteSuccess() throws CannotDeleteException {
        // when
        Q1.delete(UserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 실패 - 작성자 다름")
    void deleteFailByIsNotWriter() {
        // expected
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 실패 - Question writer 와 다른 Answer 작성자")
    void deleteFailExistsAnswer() {
        // when
        Q1.addAnswer(AnswerTest.A2);

        // then
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
