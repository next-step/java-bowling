package qna.domain.question;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.UserTest;
import qna.domain.histroy.ContentType;
import qna.domain.histroy.DeleteHistory;

class QuestionAnswersTest {

    private Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    private Answer answer = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");

    @DisplayName("답변들을 삭제하면 히스토리를 반환한다.")
    @Test
    void delete_question_then_history() throws CannotDeleteException {
        QuestionAnswers questionAnswers = new QuestionAnswers();
        questionAnswers.add(answer);

        List<DeleteHistory> histories = questionAnswers.deleteAllBy(UserTest.JAVAJIGI);

        assertThat(histories.size()).isEqualTo(1);
        assertThat(histories).contains(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같지 않을 경우 삭제를 하면 예외가 발생한다.")
    @Test
    void delete_then_throw_exception_if_other_user() {
        QuestionAnswers questionAnswers = new QuestionAnswers();
        questionAnswers.add(answer);

        assertThatThrownBy(()->questionAnswers.deleteAllBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
