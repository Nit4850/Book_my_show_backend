package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Dtos.TheatreRequestDto;
import com.example.Book_my_show_backend.Enums.SeatType;
import com.example.Book_my_show_backend.Models.TheatreEntity;
import com.example.Book_my_show_backend.Models.TheatreSeatEntity;
import com.example.Book_my_show_backend.Repository.TheatreRepository;
import com.example.Book_my_show_backend.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    TheatreRepository theatreRepository;
    public String createTheatre(TheatreRequestDto theatreRequestDto){

        TheatreEntity theatre = TheatreEntity.builder().name(theatreRequestDto.getName()).city(theatreRequestDto.getCity()).address(theatreRequestDto.getAddress()).build();

        List<TheatreSeatEntity> theatreSeats = createTheatreSeats();

        theatre.setTheatreSeatEntityList(theatreSeats);

        for(TheatreSeatEntity theatreSeat:theatreSeats){
            theatreSeat.setTheatre(theatre);
        }

        theatreRepository.save(theatre);

        return "Theatre added successfully";
    }

    private List<TheatreSeatEntity> createTheatreSeats(){
        List<TheatreSeatEntity> seats = new ArrayList<>();

        TheatreSeatEntity theatreSeat1 = new TheatreSeatEntity("1A", SeatType.CLASSIC,100);
        TheatreSeatEntity theatreSeat2 = new TheatreSeatEntity("1B", SeatType.CLASSIC,100);
        TheatreSeatEntity theatreSeat3 = new TheatreSeatEntity("1C", SeatType.CLASSIC,100);
        TheatreSeatEntity theatreSeat4 = new TheatreSeatEntity("1D", SeatType.CLASSIC,100);
        TheatreSeatEntity theatreSeat5 = new TheatreSeatEntity("1E", SeatType.CLASSIC,100);
        TheatreSeatEntity theatreSeat6 = new TheatreSeatEntity("2A", SeatType.PLATINUM,200);
        TheatreSeatEntity theatreSeat7 = new TheatreSeatEntity("2B", SeatType.PLATINUM,200);
        TheatreSeatEntity theatreSeat8 = new TheatreSeatEntity("2C", SeatType.PLATINUM,200);
        TheatreSeatEntity theatreSeat9 = new TheatreSeatEntity("2D", SeatType.PLATINUM,200);
        TheatreSeatEntity theatreSeat10 = new TheatreSeatEntity("2E",SeatType.PLATINUM,200);

        seats.add(theatreSeat1);
        seats.add(theatreSeat2);
        seats.add(theatreSeat3);
        seats.add(theatreSeat4);
        seats.add(theatreSeat5);
        seats.add(theatreSeat6);
        seats.add(theatreSeat7);
        seats.add(theatreSeat8);
        seats.add(theatreSeat9);
        seats.add(theatreSeat10);

        theatreSeatRepository.saveAll(seats);

        return seats;

    }
}
