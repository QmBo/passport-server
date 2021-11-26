package ru.job4j.passport.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.server.domain.Passport;
import ru.job4j.passport.server.service.PassportService;

import java.util.LinkedList;
import java.util.List;

/**
 * PassportController class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.11.2021
 */
@RestController
@RequestMapping("/api/passport")
public class PassportController {
    private final Logger logger = LoggerFactory.getLogger(PassportController.class);
    private final PassportService service;

    /**
     * Instantiates a new Passport controller.
     *
     * @param service the service
     */
    public PassportController(PassportService service) {
        this.service = service;
    }

    /**
     * Find all list.
     *
     * @param serial the serial
     * @return the list
     */
    @GetMapping("/find")
    public List<Passport> findAll(@RequestParam(name = "serial", required = false) String serial) {
        List<Passport> result = null;
        if (serial != null) {
            try {
                long parseLong = Long.parseLong(serial);
                result = new LinkedList<>(service.findBySerial(parseLong));
            } catch (Exception e) {
                logger.error("Long casting error. From serial parameter.", e);
            }
        } else {
            result = service.findAll();
        }
        return result;
    }

    /**
     * Save passport.
     *
     * @param passport the passport
     * @return the passport
     */
    @PostMapping("/save")
    public Passport save(@RequestBody Passport passport) {
        return service.save(passport);
    }

    /**
     * Delete passport.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(name = "id")String id) {
        boolean status = false;
        try {
            int parseInt = Integer.parseInt(id);
            status = service.delete(parseInt);
        } catch (Exception e) {
            logger.error("Integer cast exception in id.", e);
        }
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    /**
     * Update passport.
     *
     * @param id       the id
     * @param passport the passport
     * @return the passport
     */
    @PutMapping("/update")
    public Passport update(@RequestParam(name = "id")String id, @RequestBody()Passport passport) {
        Passport result = new Passport();
        try {
            int parseInt = Integer.parseInt(id);
            result = service.update(parseInt, passport);
        } catch (Exception e) {
            logger.error("Integer cast exception in id.", e);
        }
        return result;
    }

    /**
     * Find all unavailable passports.
     *
     * @return the list
     */
    @GetMapping("/unavailable")
    public List<Passport> findAllUnavailable() {
        return service.findAllUnavailable();
    }

    /**
     * Find all replaceable passports.
     *
     * @return the list
     */
    @GetMapping("/find-replaceable")
    public List<Passport> findAllReplaceable() {
        return service.findAllReplaceable();
    }
}
