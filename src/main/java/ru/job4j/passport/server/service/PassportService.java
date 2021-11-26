package ru.job4j.passport.server.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.server.domain.Passport;
import ru.job4j.passport.server.repository.PassportRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * PassportService class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.11.2021
 */
@Service
public class PassportService {
    private final PassportRepository repository;

    /**
     * Instantiates a new Passport service.
     *
     * @param repository the repository
     */
    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    /**
     * Save passport.
     *
     * @param passport the passport
     * @return the passport
     */
    public Passport save(Passport passport) {
        Passport result = passport;
        if (passport.check()) {
            result = repository.save(passport);
        }
        return result;
    }

    /**
     * Find all passports.
     *
     * @return the list
     */
    public List<Passport> findAll() {
        return (List<Passport>) repository.findAll();
    }

    /**
     * Find by serial passports.
     *
     * @param serial the serial
     * @return the list
     */
    public List<Passport> findBySerial(long serial) {
        return Collections.singletonList(repository.findBySerial(serial).orElse(new Passport()));
    }

    /**
     * Delete passport.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean delete(int id) {
        AtomicBoolean result = new AtomicBoolean(false);
        repository.findById(id).ifPresent(passport -> {
            result.set(true);
            repository.delete(passport);
        });
        return result.get();
    }

    /**
     * Update passport.
     *
     * @param id       the id
     * @param passport the passport
     * @return the passport
     */
    public Passport update(int id, Passport passport) {
        AtomicReference<Passport> result = new AtomicReference<>(new Passport());
        if (passport.check()) {
            repository.findById(id).ifPresent(find -> result.set(save(passport.setId(find.getId()))));
        }
        return result.get();
    }

    /**
     * Find all unavailable passports.
     *
     * @return the list
     */
    public List<Passport> findAllUnavailable() {
        return repository.findAllByEndDateBefore(new Date(System.currentTimeMillis()));
    }

    /**
     * Find all replaceable passports.
     *
     * @return the list
     */
    public List<Passport> findAllReplaceable() {
        GregorianCalendar calendar = new GregorianCalendar();
        Date startDate = new Date(System.currentTimeMillis());
        calendar.setTime(startDate);
        calendar.add(GregorianCalendar.MONTH, 3);
        return repository.findAllByEndDateBetween(startDate, calendar.getTime());
    }
}
