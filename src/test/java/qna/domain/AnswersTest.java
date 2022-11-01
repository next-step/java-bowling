package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {
    private Answers answers;

    @BeforeEach
    void setup() {
        answers = new Answers();
    }

    @Test
    @DisplayName("동일한 유저가 작성한 답변만 있을 경우 답변 삭제가 가능하다.")
    void deleteWhenAnswerOfLoginUserExists() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "JAVAJIGI's answer1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "JAVAJIGI's answer2");

        answers.add(answer1);
        answers.add(answer2);

        assertThat(answers.delete(UserTest.JAVAJIGI)).hasSize(2);
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 사람이 작성한 답변이 존재할 경우, 예외를 반환한다.")
    void deleteWhenAnswerOfOtherUserExists() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "JAVAJIGI's answer");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "SANJIGI's answer");

        answers.add(answer1);
        answers.add(answer2);

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
