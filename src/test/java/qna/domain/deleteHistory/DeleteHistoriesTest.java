package qna.domain.deleteHistory;

import qna.domain.ContentType;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteHistoriesTest {
    public static final DeleteHistories SAME_USER = new DeleteHistories(List.of(
            new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now())
    ));
    public static final DeleteHistories ONLY_QUESTION = new DeleteHistories(List.of(
            new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now())
    ));
}
