package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("작성자이면 질문을 삭제할 수 있다")
    void deleteQuestionSoftly() throws CannotDeleteException {
        //given
        Answer answer01 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer02 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer01);
        Q1.addAnswer(answer02);

        List<DeleteHistory> expectedDeleteHistories = List.of(
                DeleteHistory.createDeleteHistoryForQuestion(Q1),
                DeleteHistory.createDeleteHistoryForAnswer(answer01),
                DeleteHistory.createDeleteHistoryForAnswer(answer02)
        );

        //when
        List<DeleteHistory> deleteHistories = Q1.deleteQuestionAndAnswerSoftly(UserTest.JAVAJIGI);

        //then
        assertAll(
                () -> assertThat(deleteHistories).hasSize(3),
                () -> assertThat(deleteHistories).isEqualTo(expectedDeleteHistories)
        );

    }

    @Test
    @DisplayName("질문의 작성자가 아니면 질문을 제거할 수 없다")
    void doNotDeleteQuestion() {
        assertThatThrownBy(() -> Q1.deleteQuestionAndAnswerSoftly(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변의 질문자가 아닐 경우 답변을 제거할 수 없다.")
    void doNotDeleteAnswer() {
        //given
        Answer answer01 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1");
        Q2.addAnswer(answer01);

        //when, then
        assertThatThrownBy(() -> Q2.deleteQuestionAndAnswerSoftly(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
