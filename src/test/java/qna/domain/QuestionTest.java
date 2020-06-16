package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제요청 주체가 작성자와 동일하면 정상")
    @Test
    public void validateQuestionWriter_정상() {
        assertThatCode(() -> {
            Q1.deleteQnA(UserTest.JAVAJIGI, LocalDateTime.now());
        }).doesNotThrowAnyException();
    }

    @DisplayName("Question 삭제요청 주체가 작성자와 동일하지 않으면 예외 발생")
    @Test
    public void validateQuestionWriter_에러() {
        assertThatCode(() -> {
            Q1.deleteQnA(UserTest.SANJIGI, LocalDateTime.now());
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("Question의 delete메소드를 수행하면 delted가 true로 변경됨")
    @Test
    public void delete_question_true() throws CannotDeleteException {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q3.deleteQnA(UserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(Q3.isDeleted()).isTrue();
    }

    @DisplayName("QnA를 삭제하여 얻은 List<DeleteHistory>의 크기는 삭제된 게시글의 수와 동일")
    @Test
    public void delete_result_사이즈_동일() throws CannotDeleteException {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q3.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        Q3.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));

        assertThat(Q3.deleteQnA(UserTest.JAVAJIGI, LocalDateTime.now()).size())
                .isEqualTo(3);
    }
}
