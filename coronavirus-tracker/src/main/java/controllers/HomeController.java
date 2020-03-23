package controllers;

import models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.CoronaVirusDataService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats=coronaVirusDataService.getAllStats();
        int sum=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int sum1=allStats.stream().mapToInt(stat->stat.getLastDayCases()).sum();
        model.addAttribute("allStats",allStats);
        model.addAttribute("totalReportedCases",sum);
        model.addAttribute("totalNEWCASES",sum1);
        return "home";
    }
}
