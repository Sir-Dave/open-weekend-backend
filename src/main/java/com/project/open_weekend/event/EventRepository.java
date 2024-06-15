package com.project.open_weekend.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e")
    Page<Event> getAllEvents(Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.creator.id = ?1")
    Page<Event> getAllEventsByUser(long userId, Pageable pageable);

    List<Event> findByLocation(String location);
    void deleteById(@NonNull Long eventId);
}
