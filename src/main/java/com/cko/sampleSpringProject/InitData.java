package com.cko.sampleSpringProject;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.dao.ProductDAO;
import com.cko.sampleSpringProject.model.Authority;
import com.cko.sampleSpringProject.model.Product;
import com.cko.sampleSpringProject.model.User;
import com.cko.sampleSpringProject.model.Film;
import com.cko.sampleSpringProject.service.AuthorityService;
import com.cko.sampleSpringProject.service.SMSCService;
import com.cko.sampleSpringProject.service.UserService;

import com.github.javafaker.Faker;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    FilmDAO filmDAO;

    @Autowired
    ProductDAO productDAO;

    Faker faker = new Faker();


    public void initData() {
        initFilms();
        initUserAndRoles();
    }
    public void initFilms() {

//        smscSender.send_sms("89775548911","TEST MESSAGE",1, "", "", 0, "", "");
        for (int i = 0; i < 10; i++) {
            Product Product = new Product(faker.food().ingredient(), (int) ( Math.random() * 100 ), (int) ( Math.random() * 50 ));
            productDAO.save(Product);
        }

        for (int i = 0; i < 10; i++) {
            Film film = new Film(faker.superhero().name(), i, i * 2);
            filmDAO.save(film);
        }
    }

    private void initUserAndRoles() {
        Authority adminAuthority = new Authority("ROLE_ADMIN");
        Authority userAuthority = new Authority("ROLE_USER");
        authorityService.insert(adminAuthority);
        authorityService.insert(userAuthority);

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(adminAuthority);
        userService.insert(new User("1@mail.ru", bCryptPasswordEncoder.encode("1"), authorities));


    }
}