package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Dtos.TheatreRequestDto;
import com.example.Book_my_show_backend.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public String addTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        return theatreService.createTheatre(theatreRequestDto);
    }

//    API  get theatre by theatreId


//    API get all theatres


}
