package com.jskno.spring.auth.client.controller;

import com.jskno.spring.auth.client.model.ProjectModel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class ProjectClientController {

    private final WebClient webClient;

    private final String projectApiUrl;

    public ProjectClientController(WebClient webClient, @Value("${resourceserver.api.project.url}") String projectApiUrl) {
        this.webClient = webClient;
        this.projectApiUrl = projectApiUrl;
    }

    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<ProjectModel> projects = webClient.get()
            .uri(projectApiUrl)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<ProjectModel>>() { })
            .block();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/addproject")
    public String addNewProject(Model model) {
        model.addAttribute("project", new ProjectModel(0L, "", LocalDate.now()));
        return "addproject";
    }

    @PostMapping("/projects")
    public String saveProject(ProjectModel project, Model model) {
        try {
            webClient.post()
                .uri(projectApiUrl)
                .bodyValue(project)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
            return "redirect:/projects";
        } catch (final HttpServerErrorException ex) {
            model.addAttribute("msg", ex.getResponseBodyAsString());
            return "addProject";
        }
    }
}
