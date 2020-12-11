package qna.domain;

import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteTest() {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        Q1.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람의_댓글_존재() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        assertThatThrownBy( () ->
            Q1.delete(UserTest.JAVAJIGI, deleteHistories)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_다른_사람의_질문_삭제() {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        assertThatThrownBy( () ->
            Q1.delete(UserTest.SANJIGI, deleteHistories)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
