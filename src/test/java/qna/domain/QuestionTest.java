package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변글이 없는 질문 삭제")
    void deleteQuestion_noAnswer() throws Exception {
        // when
        List<DeleteHistory> actual = question.deleteQuestion(UserTest.JAVAJIGI);

        // then
        assertTrue(question.isDeleted());

        DeleteHistory deleteHistory = DeleteHistory.of(question);
        assertEquals(actual, Arrays.asList(deleteHistory));
    }

    @Test
    @DisplayName("답변글이 있는 질문 삭제 - 질문자와 답변자가 동일한 경우")
    void deleteQuestion_answersBySameWriter() throws Exception {
        // given
        question.addAnswer(AnswerTest.A1);

        // when
        List<DeleteHistory> actual = question.deleteQuestion(UserTest.JAVAJIGI);

        // then
        assertTrue(question.isDeleted());
        assertTrue(AnswerTest.A1.isDeleted());

        DeleteHistory questionDelete = DeleteHistory.of(question);
        DeleteHistory answerDelete = DeleteHistory.of(AnswerTest.A1);
        assertEquals(actual, Arrays.asList(questionDelete, answerDelete));
    }

    @Test
    @DisplayName("질문 삭제 실패 - 질문자와 답변자가 다른 경우")
    void deleteQuestion_fail_differentWriter() throws Exception {
        // given
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);

        // then
        assertThatThrownBy(() ->
                question.deleteQuestion(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
