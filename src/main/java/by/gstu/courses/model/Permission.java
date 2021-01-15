package by.gstu.courses.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * suffix: R - read, W - create/update/delete
 * prefix: U - only for user which creates manipulated object
 *         without prefix - access for all users in category
 * @author Alexander Petrushkin
 */
public enum Permission implements GrantedAuthority {
    LECTURE_R,
    LECTURE_W,
    U_LECTURE_W,
    U_USER_R,
    USER_R
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
