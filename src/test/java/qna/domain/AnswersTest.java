package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("답변 삭제 테스트")
    @Test
    void delete() throws CannotDeleteException {
        // given
        Answers answers = makeMockAnswers();

        // when
        DeleteHistories result = answers.delete(UserTest.JAVAJIGI);

        // then
        assertThat(result.getDeleteHistoryList()).hasSize(2);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
    }

    private Answers makeMockAnswers() {
        return Answers.from(Arrays.asList(AnswerTest.A1, AnswerTest.A3));
    }
}
