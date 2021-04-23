package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswersTest {

    @Test
    @DisplayName("답변등록 테스트")
    void registerAnswer() {
        // given
        Answers answers = new Answers();

        // when
        answers.add(AnswerTest.A1, QuestionTest.Q1);

        // then
        assertThat(1).isEqualTo(answers.count());
    }

    @Test
    @DisplayName("다른 사람의 답변이 존재하는 경우 답변삭제 불가")
    void deleteOthersAnswer() {
        // given
        Answers answers = new Answers();
        answers.add(AnswerTest.A1, QuestionTest.Q1);
        answers.add(AnswerTest.A2, QuestionTest.Q1);

        // when then
        assertThrows(CannotDeleteException.class, () -> answers.deleteAll(UserTest.JAVAJIGI), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변삭제 테스트")
    void deleteAll() {
        // given
        Answers answers = new Answers();
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "test answer1"), QuestionTest.Q1);
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "test answer2"), QuestionTest.Q1);

        // when
        answers.deleteAll(UserTest.JAVAJIGI);

        // then
        for (Answer answer : answers.getAnswers()) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }
}