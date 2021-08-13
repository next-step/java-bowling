package bowling.util;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaginationTest {
    @DisplayName("다음 Element 호출")
    @Test
    void next() {
        Frame nextFrame = NormalFrame.firstFrame();

        Pagination<Frame> pagination = samplePagination();
        pagination.newNextPage(nextFrame);

        assertThat(nextFrame)
                .isEqualTo(nextFrame);
    }

    @DisplayName("현재 Element 호출")
    @Test
    void currentElement() {
        Frame frame = NormalFrame.firstFrame();
        Pagination<Frame> pagination = new Pagination<>(1, frame, Pagination.empty());

        assertThat(pagination.currentElement())
                .isEqualTo(frame);
    }

    @DisplayName("현재 페이지 번호 호출")
    @Test
    void currentPageNumber() {
        assertThat(samplePagination().currentPageNumber())
                .isEqualTo(1);
    }

    @DisplayName("다음 페이지 번호 호출")
    @Test
    void nextPageNumber() {
        assertThat(samplePagination().nextPageNumber())
                .isEqualTo(2);
    }

    @DisplayName("newNext 메서드를 호출할 시 Pagination next변수에 자동 등록 된다.")
    @Test
    void newNextPageTest() {
        Pagination<Frame> pagination = samplePagination();
        pagination.newNextPage(NormalFrame.firstFrame());

        assertThat(pagination.next().isEmpty())
                .isFalse();
    }

    @DisplayName("empty 테스트")
    @Test
    void emptyTest() {
        assertThat(Pagination.empty().isEmpty())
                .isTrue();
    }

    private Pagination<Frame> samplePagination() {
        return new Pagination<>(1, NormalFrame.firstFrame(), Pagination.empty());
    }
}