package com.project.open_weekend.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByOrderByStartTime(Pageable pageable);
    Page<Event> findByCity(Pageable pageable, String city);

    @Query("SELECT e FROM Event e WHERE e.creator.id = ?1")
    Page<Event> getAllEventsByUser(long userId, Pageable pageable);


    void deleteById(@NonNull Long eventId);
}
