package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeAll
    static void setUp() {
        List<Answer> answers = List.of(
                new Answer(UserTest.JAVAJIGI, Q1, "contents1"),
                new Answer(UserTest.JAVAJIGI, Q1, "contents2"),
                new Answer(UserTest.SANJIGI, Q1, "contents3")
        );
        answers.forEach(Q1::addAnswer);

        List<Answer> answers2 = List.of(
                new Answer(UserTest.SANJIGI, Q2, "contents1"),
                new Answer(UserTest.SANJIGI, Q2, "contents2"),
                new Answer(UserTest.SANJIGI, Q2, "contents3")
        );

        answers2.forEach(Q2::addAnswer);
    }

    @Test
    public void when_Q1_writeBy() {
        Q1.writeBy(UserTest.JAVAJIGI);
    }

    @Test
    public void when_Q1_addAnswer() {
        Answer a1 = new Answer(UserTest.JAVAJIGI, Q1, "contents");
        Q1.addAnswer(a1);
        assertThat(Q1.getAnswers().hasAnswer(a1)).isEqualTo(true);
    }

    @Test
    public void delete_when_loginUser_실패() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    public void delete_when_answer_user_다름_실패() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void delete_when_loginUser_성공() throws CannotDeleteException {
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isEqualTo(true);
        assertThat(Q2.getAnswers().isAllDeleted()).isEqualTo(true);
    }

    @Test
    public void given_userTest_when_allAnswerIsOwners() {
        assertThat(Q1.allAnswerIsOwners(UserTest.JAVAJIGI)).isEqualTo(false);
        assertThat(Q1.allAnswerIsOwners(UserTest.SANJIGI)).isEqualTo(false);

        assertThat(Q2.allAnswerIsOwners(UserTest.SANJIGI)).isEqualTo(true);
        assertThat(Q2.allAnswerIsOwners(UserTest.JAVAJIGI)).isEqualTo(false);
    }

    @Test
    public void whern_getDeleteHistories_성공() {
        List<DeleteHistory> deleteHistoryList = Q1.getDeleteHistories();
        assertThat(deleteHistoryList.size()).isEqualTo(5);

        List<DeleteHistory> deleteHistoryList2 = Q2.getDeleteHistories();
        assertThat(deleteHistoryList2.size()).isEqualTo(4);

    }
}
