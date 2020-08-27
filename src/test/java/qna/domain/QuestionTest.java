package qna.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1_WRITE_BY_JAVAJIGI = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2_WRITE_BY_SANJIGI = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 생성한 Owner 여부를 확인함")
    @Test
    void verifyOwner() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(()-> Q1_WRITE_BY_JAVAJIGI.verifyOwner(UserTest.SANJIGI))
        ;
    }

    @DisplayName("모든 답변에 대해서, 다른 사람이 작성한 답변이 있는지 확인")
    @Test
    void verifyOwnerForAnswers() {
        Question question = new Question("title1", "contents1");
        question.writeBy(UserTest.JAVAJIGI);

        question.addAnswer(AnswerTest.A2_WRITE_BY_SANJIGI); // 다른 사람이 작성한 답변

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(()-> question.verifyOwnerForAnswers(UserTest.JAVAJIGI))
            .withMessage(Answer.ERROR_MESSAGE_OWNER_CHECK)
        ;
    }

    @Test
    void ToDeleteHistoriesTest(){
        Question question = new Question("title1", "contents1");
        User javajigi = UserTest.JAVAJIGI;
        question.writeBy(javajigi);
        question.addAnswer(AnswerTest.A1_WRITE_BY_JAVAJIGI);
        List<DeleteHistory> deleteHistories = question.ToDeleteHistories();

        assertThat(deleteHistories.size()).isEqualTo(2);
        assertThat(deleteHistories).containsExactly(
            new DeleteHistory(ContentType.QUESTION, null, javajigi, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, null, javajigi, LocalDateTime.now())
        );
    }
}
