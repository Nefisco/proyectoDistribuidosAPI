package es.unizar.tmdad.lab0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.unizar.tmdad.lab0.service.TwitterLookupService;
import org.springframework.web.bind.annotation.RestController;


//@Controller
@RestController
public class SearchController {

    @Autowired
    TwitterLookupService twitter;



    @RequestMapping("/search")
    //public String search(@RequestParam("q") String q, Model m) {
    public SearchResults search(@RequestParam("q") String q) {
    	//m.addAttribute("res", twitter.search(q));
       //return "search :: content";

        return twitter.search(q);
    }
}