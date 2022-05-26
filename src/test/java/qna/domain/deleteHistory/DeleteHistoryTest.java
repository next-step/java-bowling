package qna.domain.deleteHistory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.domain.ContentType;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteHistoryTest {
    public static final DeleteHistory DH1 = new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DH2 = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());

    @Mock
    private DeleteHistoryRepository deleteHistoryRepository;

    @Test
    void saveTo() {
        DH1.saveTo(deleteHistoryRepository);

        verify(deleteHistoryRepository).save(DH1);
    }
}

