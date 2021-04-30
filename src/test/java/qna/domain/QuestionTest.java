package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("오너가 일치하는지 테스트")
    @Test
    void onwerTest() {
        assertThrows(CannotDeleteException.class, () -> Q1.deleted(UserTest.SANJIGI));
    }

    @DisplayName("정상적으로 DeleteHistories를 반환하는지 테스트")
    @Test
    void deletedTest() throws CannotDeleteException {
        DeleteHistory deleteHistory =
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());
        Answers answers = new Answers(Q1.getAnswers());
        Deque<DeleteHistory> deleteHistories = answers.deletedAnswers(UserTest.JAVAJIGI);

        DeleteHistories expectedDeleteHistories = DeleteHistories.createOf(deleteHistory, deleteHistories);
        DeleteHistories actualDeleteHistories = Q1.deleted(UserTest.JAVAJIGI);

        assertEquals(actualDeleteHistories, expectedDeleteHistories);
    }
}
