package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("작성자가 본인이 아니면, 질문을 삭제할 수 없다.")
    void cannotDeleteQuestionByOtherUser() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI).toReadOnlyList())
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 다른 사람이 답변을 달면, 질문을 삭제할 수 없다.")
    void cannotDeleteQuestionWithAnswers() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI).toReadOnlyList())
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문을 삭제하면, DeleteHistory의 List을 반환한다.")
    void deleteQuestionTest() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI)
                .toReadOnlyList()
                .size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("질문을 삭제하면, 질문에 달린 답변까지 삭제된다.")
    void deleteQuestionWithAnswersTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);

        assertThat(Q1.delete(UserTest.JAVAJIGI)
                .toReadOnlyList()
                .size())
                .isEqualTo(3);
    }
}
