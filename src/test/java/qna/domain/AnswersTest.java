package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AnswersTest {

    @Test
    void hasAnotherOwner_false() {
        //given
        List<Answer> mockAnswers = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        Answers answers = new Answers(mockAnswers);

        //when&then
        assertThat(answers.hasAnotherOwner(UserTest.JAVAJIGI))
            .isTrue();
    }

    @Test
    void hasAnotherOwner_true() {
        //given
        List<Answer> mockAnswers = Arrays.asList(AnswerTest.A1, AnswerTest.A1);
        Answers answers = new Answers(mockAnswers);

        //when&then
        assertThat(answers.hasAnotherOwner(UserTest.JAVAJIGI))
            .isFalse();
    }

    @Test
    void delete() {
        //given
        List<Answer> mockAnswers = Arrays.asList(AnswerTest.A1);
        Answers answers = new Answers(mockAnswers);

        List<DeleteHistory> deleteHistories = Arrays.asList(
            new DeleteHistory(AnswerTest.A1));

        //when&then
        assertThat(answers.delete())
            .isEqualTo(deleteHistories);
    }
}