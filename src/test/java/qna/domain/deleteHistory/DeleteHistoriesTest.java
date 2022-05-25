package qna.domain.deleteHistory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteHistoriesTest {
    public static final DeleteHistories SAME_USER = new DeleteHistories(List.of(
            DeleteHistoryTest.DH1,
            DeleteHistoryTest.DH2
    ));
    public static final DeleteHistories ONLY_QUESTION = new DeleteHistories(List.of(
            DeleteHistoryTest.DH1
    ));

    @Mock
    private DeleteHistoryRepository deleteHistoryRepository;

    @Test
    void saveTo() {
        SAME_USER.saveTo(deleteHistoryRepository);

        verify(deleteHistoryRepository).saveAll(List.of(
                DeleteHistoryTest.DH1, DeleteHistoryTest.DH2
        ));
    }
}
