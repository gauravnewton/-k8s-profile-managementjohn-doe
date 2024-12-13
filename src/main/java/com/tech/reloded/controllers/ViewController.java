/*
 * Copyright gauravnewton
 * 12-12-2024 at 20:41 IST
 */

package com.tech.reloded.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type View controller.
 */
@Controller
public class ViewController {


    /**
     * Index string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
