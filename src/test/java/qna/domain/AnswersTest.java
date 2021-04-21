package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    @Test
    @DisplayName("다른 사람의 답변이 존재하는 경우 답변삭제 불가")
    void deleteOthersAnswer() {
        // given
        Answers answers = new Answers();
        answers.add(AnswerTest.A2, QuestionTest.Q1);

        // when then
        Assertions.assertThrows(CannotDeleteException.class, () -> answers.delete(UserTest.JAVAJIGI), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}