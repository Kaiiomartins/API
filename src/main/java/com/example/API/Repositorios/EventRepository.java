package com.example.API.Repositorios;

import com.example.API.Domain.Eventos.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query ("SELECT e FROM Event e LEFT e.address a WHERE e.data >= :currentDate")
    public Page<Event> findUpcomingEvents(@Param("current")Date curentDate,Pageable pegeable  );

    @Query("SELECT e FROM Event e "+
    "LEFT JOIN e.address a"+
    "(:title = '' OR e.title LIKE %:title%) AND "+
    "(:city IS NULL OR a.city LIKE %:city%)AND"+
    "(:uf IS NULL OR a.uf LIKE %:uf%)AND "+
    "(:startDate IS NULL OR e.date >= :startDate)AND "+
    "(:endDate IS NULL OR  e.date <= :endDate)")
    Page<Event> findFilteredEvents(@Param("title") String title,
                                   @Param("city") String city,
                                   @Param("uf") String uf,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   Pageable pageable);

}
