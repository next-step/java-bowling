package qna.domain;

import org.assertj.core.api.ObjectEnumerableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
    }

    @DisplayName("답변삭제 테스트")
    @Test
    void testDeleteALL() throws CannotDeleteException {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A1);

        answers.deleteAnswers(UserTest.JAVAJIGI);
        assertThat(answers.getAnswers()).allSatisfy(answer -> {
            assertThat(answer.isDeleted()).isTrue();
        });
    }

    @DisplayName("다른 아이디의 유저에 의한 삭제여부 테스트")
    @Test
    void testDeleteByOther() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> {
            answers.deleteAnswers(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
