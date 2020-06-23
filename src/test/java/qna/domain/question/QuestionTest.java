package qna.domain.question;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.UserTest;
import qna.domain.history.ContentType;
import qna.domain.history.DeleteHistory;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1")
        .writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 삭제하면 질문의 상태가 deleted로 변경된다.")
    @Test
    void delete_question() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.JAVAJIGI);

        question.deleteBy(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문을 삭제하면 답변, 질문에대한 히스토리를 반환한다.")
    @Test
    void delete_question_then_history() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.JAVAJIGI);

        List<DeleteHistory> histories = question.deleteBy(UserTest.JAVAJIGI);

        assertThat(histories.size()).isEqualTo(2);
        assertThat(histories).contains(
            new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI,
                LocalDateTime.now()));
        assertThat(histories).contains(
            new DeleteHistory(ContentType.ANSWER, AnswerTest.JAVAJIGI.getId(), UserTest.JAVAJIGI,
                LocalDateTime.now()));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같지 않을 경우 삭제를 하면 예외가 발생한다.")
    @Test
    void delete_then_throw_exception_if_other_user() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("다른 사람의 답변이 있을경우 삭제시 얘외가 발생한다.(질문자와 답변자가 다른경우 답변을 삭제 할 수 없다.)")
    @Test
    void delete_then_throw_exception_if_other_user_question_exist() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.SANJIGI);

        assertThatThrownBy(() -> question.deleteBy(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문을 삭제하면 답변도 모두 삭제(deleted==true)된다.")
    @Test
    void delete_then_removed_all_answer() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(answer1);

        question.deleteBy(UserTest.JAVAJIGI);

        Question expected = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThat(question).isEqualTo(expected);
        assertThat(answer1.isDeleted()).isTrue();
    }

}
