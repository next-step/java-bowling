package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

class AnswersTest {
    @Test
    @DisplayName("다른 답변자가 한명이라도 있을 경우 예외를 발생시킨다")
    public void deleteException() throws Exception {
        // given
        Answers answers = new Answers();
        answers.add(new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        // when, then
        Assertions.assertThatThrownBy(() -> {
                    answers.deleteBy(UserTest.JAVAJIGI);
                }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변자가 모두 같을 경우 삭제에 성공한다")
    public void deleteHistory() throws Exception {
        // given
        Answers answers = new Answers();
        answers.add(new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        int answerSize = answers.size();

        // when
        int deleteHistorySize = answers.deleteBy(UserTest.JAVAJIGI).size();

        // then
        Assertions.assertThat(answerSize).isEqualTo(deleteHistorySize);
    }
}
