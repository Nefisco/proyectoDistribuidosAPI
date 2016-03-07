package es.unizar.tmdad.lab0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Usuario on 07/03/2016.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String greeting() {
        return "index";
    }

}

