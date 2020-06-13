package qna.domain.question;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.UserTest;

class QuestionAnswersTest {

    private Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    private Answer answer = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");

    @Test
    void delete() throws CannotDeleteException {
        QuestionAnswers questionAnswers = new QuestionAnswers();
        questionAnswers.add(answer);

        questionAnswers.deleteAllBy(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(questionAnswers).isEqualTo(new QuestionAnswers());
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
