package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public Answer answer = new Answer(11L, UserTest.JAVAJIGI, Q1, "Answers Contents1");


    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q1.checkAuthority(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_실패_답변_없음() {
        assertThatThrownBy(() -> {
            Q2.isHasAnswersIsNotOwner(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_실패_질문자_답변자_다름() {
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> {
            Q1.isHasAnswersIsNotOwner(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

}
