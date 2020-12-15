package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void delete() throws CannotDeleteException {
        assertThat(question.delete(UserTest.JAVAJIGI)).hasSize(1);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void cantDelete() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .withFailMessage("질문을 삭제할 권한이 없습니다.")
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void cantDelete2() {
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        Answer answer2 = new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer2);

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .withFailMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
                .isInstanceOf(CannotDeleteException.class);
    }
}
