package com.example.apt.controller;


import com.example.apt.domain.Apt;
import com.example.apt.entity.AptEntity;
import com.example.apt.service.AptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class AptController {

    private final AptService aptService;

    @Autowired
    public AptController(AptService aptService){
        this.aptService=aptService;
    }

    @GetMapping("/")
    public String home(){
        return "home";

    }

    @GetMapping(value = "/apts")
    public String list(Model model){
        System.out.println("*** apts mappint ***");

        List<Apt.Simple> apts = aptService.findApts();
        model.addAttribute("apts",apts);
        return "apts/aptList";
    }

    @GetMapping(value = "/apts/new")
    public String createForm(){
        return "apts/inputAptForm";
    }

    @PostMapping(value = "/apts/new")
    public String create(Apt.Create form){
        aptService.addApt(form);
        return "redirect:/";
    }

    @GetMapping(value = "/apts/search")
    public String searchForm(){
        return "apts/searchAptForm";
    }

    @PostMapping(value = "/apts/search")
    public String search(Apt.Create form, Model model) {
        List<Apt.Simple> apts = aptService.findCondApts(form);
        model.addAttribute("apts", apts);
        return "apts/aptList";
    }

    @GetMapping("/apts/{aptId}/update")
    public String getMemberUpdateForm(@PathVariable Long aptId, Model model) {
        AptEntity aptEntity = aptService.getAptById(aptId);
        model.addAttribute("apt", aptEntity);
        return "apts/updateAptForm";
    }

    @PostMapping("/apts/{aptId}")
    public String updateApt(@PathVariable Long aptId, Apt.Update updateForm) {
        aptService.updateAptService(aptId, updateForm);
        return "redirect:/apts/" + aptId;
    }

    @GetMapping("/apts/{aptId}")
    public String getAptById(@PathVariable Long aptId, Model model) {

        List<AptEntity> aptSimpleListSingle = new LinkedList<>();
        aptSimpleListSingle.add(aptService.getAptById(aptId));

        model.addAttribute("apts", aptSimpleListSingle);
        return "apts/aptList";
    }

    @GetMapping("/apts/{aptId}/delete")
    public String getAptDeleteForm(@PathVariable Long aptId, Model model) {
        AptEntity aptEntity = aptService.getAptById(aptId);
        model.addAttribute("apt", aptEntity);
        return "apts/deleteAptForm";
    }

    @PostMapping("/apts/{aptId}/delete")
    public String getAptDelete(@PathVariable Long aptId, Model model) {
        model.addAttribute("id", aptId);
        aptService.deleteApt(aptId);
        return "redirect:/apts";
    }

}
