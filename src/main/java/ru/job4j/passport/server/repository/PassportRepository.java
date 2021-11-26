package ru.job4j.passport.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.passport.server.domain.Passport;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * PassportRepository class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.11.2021
 */
public interface PassportRepository extends CrudRepository<Passport, Integer> {
    /**
     * Find by serial optional.
     *
     * @param serial the serial
     * @return the optional
     */
    Optional<Passport> findBySerial(Long serial);

    /**
     * Find all by end date before list.
     *
     * @param date the date
     * @return the list
     */
    List<Passport> findAllByEndDateBefore(Date date);

    /**
     * Find all by end date between list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    List<Passport> findAllByEndDateBetween(Date startDate, Date endDate);
}
