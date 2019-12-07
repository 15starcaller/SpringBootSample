package com.cko.sampleSpringProject;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.model.Authority;
import com.cko.sampleSpringProject.model.Film;
import com.cko.sampleSpringProject.model.User;
import com.cko.sampleSpringProject.service.AuthorityService;
import com.cko.sampleSpringProject.service.SMSCService;
import com.cko.sampleSpringProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    SMSCService smscSender;

    @Autowired
    FilmDAO filmDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void initData() {

//        smscSender.send_sms("89775548911","TEST MESSAGE",1, "", "", 0, "", "");
        initUserAndRoles();
        initFilms();


    }

    private void initFilms() {
        Film film = new Film("Rosomaha",9,18);
        filmDAO.save(film);
        Film film1 = new Film("Rosomaha324",9,18);
        filmDAO.save(film1);
        Film film2 = new Film("Rosomaha234",9,18);
        filmDAO.save(film2);
        Film film3 = new Film("Rosomahagtfhjfgj",9,18);
        filmDAO.save(film3);
    }

    private void initUserAndRoles(){
        Authority adminAuthority = new Authority("ROLE_ADMIN");
        Authority userAuthority = new Authority("ROLE_USER");
        authorityService.insert(adminAuthority);//сохранение в базу
        authorityService.insert(userAuthority);

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(adminAuthority);
        for (int i = 0; i < 10; i++) {


            userService.insert(new User(i+"1@mail.ru", bCryptPasswordEncoder.encode(String.valueOf(i)), authorities));
        }

    }
}
