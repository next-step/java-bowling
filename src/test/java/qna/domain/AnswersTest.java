package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.*;
import static qna.domain.UserTest.*;

public class AnswersTest {
    @Test
    @DisplayName("Answers 의 모든 답변이 특정 User 의 답변인 경우 모두 삭제가 가능하다.")
    void deleteAllBy_성공() throws CannotDeleteException {
        // given
        Answers answers = new Answers(Arrays.asList(A1, A3));

        // when
        answers.deleteAllBy(JAVAJIGI);

        // then
        assertThat(answers.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Answers 의 답변이 다수의 User 에 의해 작성된 경우 모두 삭제가 불가능하다.")
    void deleteAllBy_실패() {
        Answers answers = new Answers(Arrays.asList(A2, A4, A5));

        assertThatThrownBy(() -> {
            answers.deleteAllBy(JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            answers.deleteAllBy(SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
