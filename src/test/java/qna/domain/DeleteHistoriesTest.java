package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("Question 히스토리가 잘 생성 되는지 확인")
    @Test
    public void makeQuestionHistoryTest() {
        // given
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory deleteHistory = new DeleteHistory(
                ContentType.QUESTION,
                question.getId(),
                question.getWriter(),
                LocalDateTime.now());

        // when
        deleteHistories.makeQuestionHistory(question);

        // then
        assertThat(deleteHistories.getHistories().stream()
                .filter(h -> h.equals(deleteHistory))
                .count()).isEqualTo(1);
    }

    @DisplayName("Answer 히스토리가 잘 생성 되는지 확인")
    @Test
    public void makeAnswerHistoryTest() {
        // given
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory deleteHistory = new DeleteHistory(
                ContentType.ANSWER,
                answer.getId(),
                answer.getWriter(),
                LocalDateTime.now());

        // when
        deleteHistories.makeAnswersHistory(question.getAnswers());

        // then
        assertThat(deleteHistories.getHistories().stream()
                .filter(h -> h.equals(deleteHistory))
                .count()).isEqualTo(1);
    }
}
