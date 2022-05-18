package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Answers answers = new Answers(Arrays.asList(
        new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1")
    ));

    @Test
    void createTest() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThat(answers).isNotNull();
        assertThat(answers.size()).isEqualTo(2);
        assertThat(answers.get().stream()
            .noneMatch(Answer::isDeleted))
            .isTrue();
    }

    @DisplayName("답변 전체를 지울 때, 작성자가 맞으면 전부 삭제 성공한다.")
    @Test
    void deleteAllTest() throws CannotDeleteException {
        answers.deleteAllByUser(UserTest.JAVAJIGI);

        assertThat(answers.size()).isEqualTo(1);
        assertThat(answers.get().stream()
            .allMatch(Answer::isDeleted))
            .isTrue();
    }

    @DisplayName("답변 전체를 지울 때, 작성자가 안맞는 것이 있으면 실패한다.")
    @Test
    void deleteAllTest2() {
        assertThatThrownBy(() -> {
            answers.deleteAllByUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}