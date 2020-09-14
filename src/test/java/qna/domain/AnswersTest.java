package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setup() {
        answers = Answers.of(Collections.singletonList(AnswerTest.A1));
    }

    @Test
    void validateAnswersWhenDeleting() {
        assertThatThrownBy(() -> answers.deleteAnswers(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void deleteAnswers() throws CannotDeleteException {
        answers.deleteAnswers(UserTest.JAVAJIGI);
        then(AnswerTest.A1.isDeleted()).isTrue();
    }
}
