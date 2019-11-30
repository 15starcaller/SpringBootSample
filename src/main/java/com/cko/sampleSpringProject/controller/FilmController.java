package com.cko.sampleSpringProject.controller;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller//annotation

@RequestMapping("/films")
public class FilmController {
    @Autowired//автоматически связать/инджектет
            FilmDAO filmDAO;//просто взывает к контексту. Точка взаимосвязи для скл и этого

    @GetMapping("/all")
    public String showGamePage() {
        return "oldFilms";
    }

    @PostMapping("/newFilm")
    public String addNewFilm(Film film)//готовый метод благодаря одинаковым именам, иначе через @ integer rating
    {
        filmDAO.save(film);
        System.out.println(film.getTitle());
        return "NewFilm_aka_CreaterFilm";
    }//todo

    @GetMapping("/editFilm")
    public ModelAndView showEditFilmPage(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Film> optional = filmDAO.findById(id);
        Film film = optional.get();
        modelAndView.addObject("film", film);
        modelAndView.setViewName("editFilms");
        return modelAndView;
        //http://localhost:8080/films/editFilm?id=4
    }
    @PostMapping("/editFilm")
    public String editFilm(Film film){
        filmDAO.save(film);
        return "oldFilms";//
    }
}
