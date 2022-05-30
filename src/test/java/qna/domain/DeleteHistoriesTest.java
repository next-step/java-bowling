package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeleteHistoriesTest {

    @Test
    @DisplayName("Question 삭제 이력 추가")
    void questionDeleteHistory() throws CannotDeleteException {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(question, UserTest.JAVAJIGI);

        assertThat(deleteHistories.value().get(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("AnswerList 삭제 이력 추가")
    void answerListDeleteHistory() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(
                new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"))
        );

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(answers.value().get(0), UserTest.JAVAJIGI);
        deleteHistories.add(answers.value().get(1), UserTest.JAVAJIGI);


        assertAll("AnswerList 삭제 이력 확인",
                () -> assertThat(deleteHistories.value().get(0))
                        .isEqualTo(new DeleteHistory(
                                ContentType.ANSWER,
                                answers.value().get(0).getId(),
                                answers.value().get(0).getWriter(),
                                LocalDateTime.now())),
                () -> assertThat(deleteHistories.value().get(1))
                        .isEqualTo(new DeleteHistory(
                                ContentType.ANSWER,
                                answers.value().get(1).getId(),
                                answers.value().get(1).getWriter(),
                                LocalDateTime.now()))
                );
            }
}