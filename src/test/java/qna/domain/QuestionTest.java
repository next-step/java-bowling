package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제요청 주체가 작성자와 동일하면 정상")
    @Test
    public void validateQuestionWriter_정상() {
        assertThatCode(() -> {
            Q1.validateDeleteRequestor(UserTest.JAVAJIGI);
        }).doesNotThrowAnyException();
    }

    @DisplayName("Question 삭제요청 주체가 작성자와 동일하지 않으면 예외 발생")
    @Test
    public void validateQuestionWriter_에러() {
        assertThatCode(() -> {
            Q1.validateDeleteRequestor(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Question의 답변이 작성자 본인이 작성한 답변만 있는 경우는 정상")
    @Test
    public void validateAnswerWriters_정상() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Q3.addAnswer(answer);

        assertThatCode(() -> {
            Q3.validateAnswerWriters();
        }).doesNotThrowAnyException();
    }

    @DisplayName("Question의 답변 중 작성자가 쓰지 않은 답변이 있는 경우 예외 발생")
    @Test
    public void validateAnswerWriters_에러() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.SANJIGI, Q3, "질문 작성자가 아닌 사람이 답변 달았음.");
        Q3.addAnswer(answer);

        assertThatThrownBy(() -> {
            Q3.validateAnswerWriters();
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
