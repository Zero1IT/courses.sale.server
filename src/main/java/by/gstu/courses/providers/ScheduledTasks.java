package by.gstu.courses.providers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledTasks {

    private final SessionFactory sessionFactory;

    @Scheduled(fixedDelay = 3_600_000) // 1 hour
    public void closingStartedCourses() {
        try (Session session = sessionFactory.openSession()) {
            @SuppressWarnings("rawtypes")
            final Query query = session
                    .createQuery("update Course c set c.closed=true where c.startDate < :now");
            query.setParameter("now", Instant.now());
            query.executeUpdate();
        }
    }
}
