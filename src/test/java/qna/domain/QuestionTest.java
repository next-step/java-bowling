package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("답변과 질문의 작성자가 삭제한 경우 테스트")
    void deleteBy_owner_of_question_and_answers() {
        Question questionWithOneAnswer = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(1L, UserTest.JAVAJIGI, questionWithOneAnswer, "Answers Contents1");
        LocalDateTime deleteDate = LocalDateTime.now();
        DeleteHistory expectedQuestionDeleteHistory = new DeleteHistory(ContentType.QUESTION, questionWithOneAnswer.getId(), questionWithOneAnswer.getWriter(), deleteDate);
        DeleteHistory expectedAnswerDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteDate);
        questionWithOneAnswer.addAnswer(answer);

        DeleteHistories deleteHistories = questionWithOneAnswer.deleteBy(UserTest.JAVAJIGI, deleteDate);
        List<DeleteHistory> actualDeleteHistoryList = deleteHistories.getDeleteHistories();

        assertThat(questionWithOneAnswer.isDeleted()).isTrue();
        assertThat(actualDeleteHistoryList).containsExactlyInAnyOrder(expectedQuestionDeleteHistory,expectedAnswerDeleteHistory);
    }

    @Test
    @DisplayName("답변의 작성자가 아닌경우 삭제 테스트")
    void deleteBy_not_owner_of_question() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> question.deleteBy(UserTest.SANJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }


    @Test
    @DisplayName("답변의 작성자이지만 질문의 작성자가 아닌경우 삭제 테스트")
    void deleteBy_not_owner_of_answer() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answerByOwner = new Answer(1L, UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answerByOther = new Answer(2L, UserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswers(Arrays.asList(answerByOwner, answerByOther));

        assertThatThrownBy(() -> question.deleteBy(UserTest.JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
