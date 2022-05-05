package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("답변을 일괄적으로 제거한다")
    void deleteAnswerSoftly() throws CannotDeleteException {
        //given
        Answer answer01 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer02 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        List<Answer> answerList = List.of(answer01, answer02);

        Answers answers = Answers.create(answerList);

        List<DeleteHistory> expectedDeleteHistories = List.of(
                DeleteHistory.createDeleteHistoryForAnswer(answer01),
                DeleteHistory.createDeleteHistoryForAnswer(answer02)
        );

        //when
        List<DeleteHistory> deleteHistories = answers.deleteAnswerSoftly(UserTest.JAVAJIGI);

        //then
        assertThat(deleteHistories).isEqualTo(expectedDeleteHistories);
    }

}