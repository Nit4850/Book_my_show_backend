package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Dtos.ShowRequestDto;
import com.example.Book_my_show_backend.Models.*;
import com.example.Book_my_show_backend.Repository.MovieRepository;
import com.example.Book_my_show_backend.Repository.ShowRepository;
import com.example.Book_my_show_backend.Repository.ShowSeatRepository;
import com.example.Book_my_show_backend.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;
    public String addShow(ShowRequestDto showRequestDto){

        // show Entity
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        // you need to get the movieRepo
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //you need to get the theatre Repository
        TheatreEntity theatreEntity = theatreRepository.findById(showRequestDto.getTheatreId()).get();

        showEntity.setTheatre(theatreEntity);
        showEntity.setMovie(movieEntity);

        //optional things
        movieEntity.getListOfShows().add(showEntity);
        theatreEntity.getListOfShows().add(showEntity);

//        theatreRepository.save(theatreEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theatreEntity.getTheatreSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        // For each show seat: we need to mark that to which show its belongs(foreign key will be filled)
        for(ShowSeatEntity showSeat: seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theatreRepository.save(theatreEntity);
        //showRepository.save(showEntity);
        return "Show Added successfully";
    }
    public List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatEntityList){

        List<ShowSeatEntity> seats = new ArrayList<>();

        for(TheatreSeatEntity theatreSeat:theatreSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theatreSeat.getSeatNo()).seatType(theatreSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);
        return seats;


    }
}
