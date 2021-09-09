package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;

    @BeforeEach
    void beforeEach() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("삭제 요청한 유저가 질문글 작성 유저가 아닐 경우 Exception이 발생해야 한다.")
    void failByLoginAndQuestionIdDismatchTest() {

        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.deleteQuestion(UserTest.SANJIGI))
            .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제가 가능한 상황이면 삭제 후 DeleteHistory를 반환한다.")
    void successDeleteQuestionTest() throws CannotDeleteException {

        // given
        User writer = question.getWriter();
        DeleteHistory expected = new DeleteHistory(question);

        // when
        DeleteHistory result = question.deleteQuestion(writer);

        // then
        assertAll(
            () -> assertThat(result).isEqualTo(expected),
            () -> assertTrue(question.isDeleted())
        );
    }

    @Test
    @DisplayName("Question과 Answers를 전부 삭제할 수 있다.")
    void successDeleteAllTest() {

        // given
        User writer = question.getWriter();
        question.addAnswer(answer);

        List<DeleteHistory> expected = Arrays.asList(
            new DeleteHistory(question),
            new DeleteHistory(answer)
        );

        // when
        List<DeleteHistory> result = question.delete(writer);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
