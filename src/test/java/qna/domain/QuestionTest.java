package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(new QuestionBody("title1", "contents1")).writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(new QuestionBody("title2", "contents2")).writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문을 삭제하면 상태값이 변경된다")
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 당사자가 아닐 경우 삭제할 수 없다")
    void validateAnswersOwner() {
        Q1.addAnswer(AnswerTest.A1);
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
