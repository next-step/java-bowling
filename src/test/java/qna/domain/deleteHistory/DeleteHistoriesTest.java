package qna.domain.deleteHistory;

import java.util.List;

public class DeleteHistoriesTest {
    public static final DeleteHistories SAME_USER = new DeleteHistories(List.of(
            DeleteHistoryTest.DH1,
            DeleteHistoryTest.DH2
    ));
    public static final DeleteHistories ONLY_QUESTION = new DeleteHistories(List.of(
            DeleteHistoryTest.DH1
    ));
}
