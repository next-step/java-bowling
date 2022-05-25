package qna.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.domain.deleteHistory.DeleteHistoriesTest;
import qna.domain.deleteHistory.DeleteHistoryRepository;
import qna.domain.deleteHistory.DeleteHistoryTest;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteHistoryServiceTest {
    @Mock
    private DeleteHistoryRepository deleteHistoryRepository;

    @InjectMocks
    private DeleteHistoryService deleteHistoryService;

    @Test
    void saveAll() {
        deleteHistoryService.saveAll(DeleteHistoriesTest.SAME_USER);

        verify(deleteHistoryRepository).saveAll(List.of(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2));
    }
}
