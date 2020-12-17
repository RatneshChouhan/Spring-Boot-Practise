package com.rc.coronavirustracker.controllers;

import com.rc.coronavirustracker.services.CoronaVirusDataService;
import com.rc.coronavirustracker.services.models.GlobalStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService dataService;

    @GetMapping("/")
    public String home(Model model) {
        List<GlobalStats> allStateData = dataService.getAllStatesData();
        long totalRptdCases = Long.parseLong(String.valueOf(allStateData.stream().mapToLong(s -> s.getLatestTotalCases()).sum()));
        long totalNewCases = allStateData.stream().mapToInt(s -> (int) s.getDiffFrmPrvDay()).sum();
        model.addAttribute("globalStates" , allStateData);
        model.addAttribute("totalRptdCases" , totalRptdCases);
        model.addAttribute("totalNewCases" , totalNewCases);
        return "home";
    }
}
