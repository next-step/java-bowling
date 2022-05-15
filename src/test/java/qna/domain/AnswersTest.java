package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.AnswerTest.JAVAJIGI_ANSWER;
import static qna.domain.AnswerTest.SANJIGI_ANSWER;
import static qna.domain.AnswerTest.WU22E_ANSWER;

class AnswersTest {
    @Test
    void getDeleteHistories_삭제된_답변_이력들을_가져온다() throws CannotDeleteException {
        JAVAJIGI_ANSWER.delete(UserTest.JAVAJIGI);
        SANJIGI_ANSWER.delete(UserTest.SANJIGI);
        Answers answers = new Answers(List.of(JAVAJIGI_ANSWER, SANJIGI_ANSWER, WU22E_ANSWER));
        assertThat(answers.getDeleteHistories()).isEqualTo(List.of(DeleteHistory.create(JAVAJIGI_ANSWER), DeleteHistory.create(SANJIGI_ANSWER)));
    }
}
