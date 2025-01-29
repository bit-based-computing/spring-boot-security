package org.foysal.security.controllers;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class HelloController {

    @Autowired
    private FilterChainProxy filterChainProxy;

    @GetMapping("/hello")
    String hello() {

//        List<Filter> filters = filterChainProxy.getFilters("/**");
//        for (Filter filter : filters) {
//            System.out.println(filter.getClass().getSimpleName());
//        }

        return "Hello World";
    }
}
