package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeleteHistoryListTest {

    @Test
    @DisplayName("Question 삭제 이력 추가")
    void questionDeleteHistory() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        DeleteHistoryList deleteHistoryList = new DeleteHistoryList();
        deleteHistoryList.addQuestionDeleteHistory(question);

        assertThat(deleteHistoryList.value().get(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("AnswerList 삭제 이력 추가")
    void answerListDeleteHistory() {
        AnswerList answerList = new AnswerList(Arrays.asList(
                new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"))
        );

        DeleteHistoryList deleteHistoryList = new DeleteHistoryList();
        deleteHistoryList.addAnswerListDeleteHistory(answerList);

        assertAll("AnswerList 삭제 이력 확인",
                () -> assertThat(deleteHistoryList.value().get(0))
                        .isEqualTo(new DeleteHistory(
                                ContentType.ANSWER,
                                answerList.value().get(0).getId(),
                                answerList.value().get(0).getWriter(),
                                LocalDateTime.now())),
                () -> assertThat(deleteHistoryList.value().get(1))
                        .isEqualTo(new DeleteHistory(
                                ContentType.ANSWER,
                                answerList.value().get(1).getId(),
                                answerList.value().get(1).getWriter(),
                                LocalDateTime.now()))
                );
            }
}