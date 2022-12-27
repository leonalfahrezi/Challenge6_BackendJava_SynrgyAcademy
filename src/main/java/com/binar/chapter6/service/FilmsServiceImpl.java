package com.binar.chapter6.service;

import com.binar.chapter6.model.Films;
import com.binar.chapter6.model.Schedules;
import com.binar.chapter6.model.Seats;
import com.binar.chapter6.model.request.FilmsRequest;
import com.binar.chapter6.repository.FilmsRepository;
import com.binar.chapter6.repository.SchedulesRepository;
import com.binar.chapter6.repository.SeatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmsServiceImpl implements FilmsService {
    private static final Logger logger = LoggerFactory.getLogger(FilmsServiceImpl.class);

    @Autowired
    FilmsRepository filmsRepository;
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    SeatsRepository seatsRepository;

    @Override
    public Films addFilm(Films films) {
        return filmsRepository.save(films);
    }

    @Override
    public Films updateFilm(FilmsRequest filmsRequest) {
        Films filmExist = filmsRepository.getFilmsById(filmsRequest.getFilmCode());
        Films update = new Films();
        if (filmExist == null) {
            logger.info("Film not registered yet, please try again");
        } else
            update.setFilmName(filmsRequest.getFilmName());
            filmsRepository.updateFilmByName(update.getFilmName());

            return null;
    }

    @Override
    public void deleteFilm(Integer filmCode) {
        filmsRepository.deleteById(filmCode);
    }

    @Override
    public Films getFilm() {
        return filmsRepository.getFilmsByName();
    }


    @Override
    public Schedules addSchedule(Schedules schedules) {
        return schedulesRepository.save(schedules);
    }

    @Override
    public Schedules getScheduleDate() {
        return schedulesRepository.getSchedulesDate();
    }

    @Override
    public Schedules getSchedulesFilms() {
        return schedulesRepository.getSchedulesFilms();
    }

    @Override
    public Seats getSeatStudio() {
        return seatsRepository.getSeatStudio();
    }

    @Override
    public List<Seats> getStudioSeatStatus() {
        return seatsRepository.getStudioSeatStatus();
    }

    @Override
    public void updateStatus(String seatNumber, String studioName, String statusUpdated) {
        seatsRepository.updateStatus(seatNumber, studioName, statusUpdated);
    }
}