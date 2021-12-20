package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("삭제 이력들 테스트")
class DeleteHistoriesTest {
    public static DeleteHistories DHS1 = DeleteHistories.from(Collections.singletonList(DeleteHistoryTest.DH1));
    public static DeleteHistories DHS12 = DeleteHistories.from(Arrays.asList(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2));

    @DisplayName("삭제 이력들 생성")
    @Test
    void createTest() {
        assertThat(DeleteHistories.from(Arrays.asList(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2)))
                .isEqualTo(DeleteHistories.from(Arrays.asList(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2)));
    }

    @DisplayName("삭제 이력들 생성 예외 확인 - null 전달")
    @ParameterizedTest
    @NullSource
    void createFailedTest(List<DeleteHistory> deleteHistories) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> DeleteHistories.from(deleteHistories)
        );
    }

    @DisplayName("삭제 이력들의 내부 삭제 이력 리스트 반환")
    @Test
    void getDeleteHistoriesTest() {
        assertThat(DeleteHistories.from(Arrays.asList(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2)).getDeleteHistories())
                .isEqualTo(Arrays.asList(DeleteHistoryTest.DH1, DeleteHistoryTest.DH2));
    }

    @DisplayName("삭제 이력 추가")
    @Test
    void appendTest() {
        assertThat(DHS1.append(DeleteHistoryTest.DH2)).isEqualTo(DHS12);
    }

    @DisplayName("삭제 이력 추가 예외 확인 - null 전달")
    @ParameterizedTest
    @NullSource
    void appendFailedTest(DeleteHistory deleteHistory) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> DHS1.append(deleteHistory)
        );
    }
}
