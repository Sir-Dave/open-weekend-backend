package com.project.open_weekend.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e where e.isApproved = true")
    Page<Event> getAllEvents(Pageable pageable);

    Page<Event> findAllByOrderByStartTime(Pageable pageable);
    @Query("SELECT e FROM Event e WHERE e.location = :city")
    Page<Event> findByCity(Pageable pageable, @Param("city") String city);

    @Query("SELECT e FROM Event e WHERE e.creator.id = ?1")
    Page<Event> getAllEventsByUser(long userId, Pageable pageable);


    void deleteById(@NonNull Long eventId);
}
