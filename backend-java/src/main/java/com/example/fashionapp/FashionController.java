package com.example.fashionapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fashion")
public class FashionController {

    @GetMapping("/recommend")
    public String recommendClothing(@RequestParam double temp, @RequestParam String condition) {

        if (condition.equalsIgnoreCase("Rain")) {
            return "Wear a waterproof jacket and avoid silk fabrics.";
        } else if (temp > 30) {
            return "Choose light cotton clothes to stay cool.";
        } else if (temp < 20) {
            return "Prefer wool or denim for warmth.";
        } else {
            return "Opt for sustainable fabrics like bamboo or hemp.";
        }
    }
}
