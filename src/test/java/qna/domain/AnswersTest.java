package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    @Test
    @DisplayName("답변들을 지우면 delete history 목록이 생성된다")
    void answersDeleteHistoryTest() throws CannotDeleteException {
        // given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

        Answers answers = new Answers(List.of(answer1, answer2));

        List<DeleteHistory> expectedList = List.of(
                DeleteHistory.createAnswerDeleteHistory(answer1),
                DeleteHistory.createAnswerDeleteHistory(answer2)
        );

        // when
        List<DeleteHistory> deleteHistories = answers.deleteAnswers(UserTest.JAVAJIGI);

        // then
        assertAll(
                () -> assertThat(deleteHistories).hasSize(2),
                () -> assertThat(answer1.isDeleted()).isTrue(),
                () -> assertThat(answer2.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).isEqualTo(expectedList)
        );
    }

}