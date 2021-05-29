package qna.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {
  @Test
  @DisplayName("DeleteHistory 리스트를 통하여 생성할 수 있다")
  public void create_with_deleteHistoryList() {
    //given
    List<DeleteHistory> createTargetList = Arrays
        .asList(DeleteHistoryTest.DQ1,
            DeleteHistoryTest.DQ2,
            DeleteHistoryTest.DA1,
            DeleteHistoryTest.DA2);
    //when
    DeleteHistories deleteHistories = new DeleteHistories(createTargetList);

    //then
    assertEquals(deleteHistories.get(), createTargetList);
  }

  @Test
  @DisplayName("DeleteHistories에 DeleteHistory를 추가할 수 있다")
  public void add_deleteHistory_in_deleteHistories() throws Exception {
    //given
    DeleteHistories deleteHistories = new DeleteHistories();
    //when
    deleteHistories.add(DeleteHistoryTest.DQ1);
    //then
    assertEquals(deleteHistories.get().get(0), DeleteHistoryTest.DQ1);
  }
}