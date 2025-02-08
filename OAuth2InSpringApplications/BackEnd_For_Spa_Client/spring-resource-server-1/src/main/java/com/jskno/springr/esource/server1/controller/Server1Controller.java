package com.jskno.springr.esource.server1.controller;

import com.jskno.springr.esource.server1.Task;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Server1Controller {

    @GetMapping
    public List<Task> getTasks() {
        return List.of(
                Task.builder().name("Medications").frequency("TwiceADay").type("Screen").build(),
                Task.builder().name("StudyKafka").frequency("OnceADay").type("Screen").build(),
                Task.builder().name("Exercise").frequency("OnceADay").type("Screen").build()
        );
    }
}
