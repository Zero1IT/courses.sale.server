package by.gstu.courses.controller.api;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public final class Limits {
    public static final int PER_PAGE_MAX = 30;

    private Limits() {}

    public static int pageLimit(int size) {
        return size > PER_PAGE_MAX || size <= 0 ? PER_PAGE_MAX : size;
    }
}
