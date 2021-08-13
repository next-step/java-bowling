package bowling.util;

@SuppressWarnings("ALL")
public class Pagination<T> {
    private final int currentPageNumber;
    private final T currentElement;
    private Pagination<T> next;

    public Pagination(int currentPage, final T currentElement, final Pagination<T> next) {
        this.currentPageNumber = currentPage;
        this.currentElement = currentElement;
        this.next = next;
    }

    public Pagination<T> next() {
        return next;
    }

    public T currentElement() {
        return currentElement;
    }

    public int currentPageNumber() {
        return currentPageNumber;
    }

    public int nextPageNumber() {
        return currentPageNumber + 1;
    }

    public boolean isEmpty() {
        return false;
    }

    public Pagination<T> newNextPage(T instance) {
        Pagination<T> nextPage = new Pagination<>(
                nextPageNumber(), instance, Pagination.empty()
        );
        this.next = nextPage;

        return nextPage;
    }

    public static<T> Pagination<T> empty() {
        return InnerLazyClass.EMPTY;
    }

    private static class InnerLazyClass {
        private static final Pagination EMPTY = new Pagination(0, null, empty()) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
