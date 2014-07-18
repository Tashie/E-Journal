package com.edu.system.swagger.web.services;

import com.wordnik.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Path;

@Controller
@Path("/hello")
@Api(value = "/hello")
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloWorldService {

    @RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody String sayHelloWorld(){

        return "Hello World!";
    }
}
