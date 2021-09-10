package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    Answer B1 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    Answer B2 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");

    @DisplayName("답변글 작성자가 모두 같지 않을때 삭제 실패 테스트")
    @Test
    public void delete_all_error() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThatThrownBy(() -> answers.deleteAnswers(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변글 작성자가 모두 같을때 삭제 성공 테스트")
    @Test
    public void delete_all_success() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(B1);
        answers.add(B2);
        answers.deleteAnswers(UserTest.SANJIGI);

        assertThat(B1.isDeleted()).isTrue();
        assertThat(B2.isDeleted()).isTrue();
    }

    @DisplayName("Answer 삭제 성공 후 DeleteHistory 비교 성공 테스트")
    @Test
    public void delete_deleteHistory() throws CannotDeleteException {
        assertThat(A1.delete(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
