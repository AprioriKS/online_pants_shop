package com.example.online_pants_shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
  @GetMapping()
  public String hello() {
    return "Hello from Pants api. Use /swagger-ui/index.html to test";
  }

}
