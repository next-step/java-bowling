package qna.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnswersTest {

    @DisplayName("답변글 작성자가 모두 같지 않으면 답변을 삭제할 수 없다.")
    @Test
    void delete_all_error() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)).deleteAnswers(UserTest.JAVAJIGI));
    }

    @DisplayName("답변글 작성자가 모두 같으면 답변을 삭제할 수 있다.")
    @Test
    void delete_all() throws CannotDeleteException {
        Answer answer = AnswerTest.A1;
        assertThat(new Answers(Arrays.asList(answer)).deleteAnswers(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistories(Arrays.asList(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))));
    }
}