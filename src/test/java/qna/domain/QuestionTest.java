package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    @DisplayName("Question의 delete메소드를 수행하면 delted가 true로 변경됨")
    @Test
    public void delete_question_true() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q3.delete();

        assertThat(Q3.isDeleted()).isTrue();
    }
}
