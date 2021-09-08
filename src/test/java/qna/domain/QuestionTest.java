package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 질문_삭제_권한_없음_예외발생() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 다른사람_답변_존재_예외발생() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 본인이_단_답변만_존재() throws CannotDeleteException {
        //when
        Q2.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3"));
        Q2.delete(UserTest.SANJIGI);

        //then
        assertTrue(Q2.isDeleted());
    }

    @Test
    public void 답변_존재_안함() throws CannotDeleteException {
        //when
        Q2.delete(UserTest.SANJIGI);

        //then
        assertTrue(Q2.isDeleted());
    }
}
