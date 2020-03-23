package controller;


import model.Nationality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.NationalityRepository;
import services.NationalityService;

@Controller

public class NationalityController {
    @Autowired
    private NationalityService nationalityService;


    @GetMapping("/nationalities")
    public  String getNationalities(Model model){
        model.addAttribute("nationalities",nationalityService.getNationalities());
        return "nationalities";
    }
    @GetMapping("/onenationality")
    @ResponseBody
    public Nationality getNationalityById(Integer Id, Model model){
        model.addAttribute("onenationality",nationalityService.getNationalityById(Id));
        return nationalityService.getNationalityById(Id);
    }
    @RequestMapping(value = "/addNew",method = {RequestMethod.POST,RequestMethod.PUT})
    public String addNationality(Nationality nationality){
        nationalityService.addNationality(nationality);
        return "redirect:/nationalities";
    }
    @RequestMapping(value = "/save",method = {RequestMethod.PUT,RequestMethod.POST,RequestMethod.GET})
    public String updateNationality(Nationality nationality){
        nationalityService.updateNationality(nationality);
        return "redirect:/nationalities";
    }
    @RequestMapping(value = "/delete",method = {RequestMethod.DELETE,RequestMethod.GET})
    public String deleteNationality(Integer Id){
        nationalityService.deleteNationality(Id);
        return "redirect:/nationalities";

    }

}
