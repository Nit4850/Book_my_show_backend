package com.example.Book_my_show_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name="theatre")
@Builder
@AllArgsConstructor
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

    // List of shows

    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;


    //List of theatre seats
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    List<TheatreSeatEntity> theatreSeatEntityList;


    public void setTheatreSeatEntityList(List<TheatreSeatEntity> theatreSeats) {
    }
}
