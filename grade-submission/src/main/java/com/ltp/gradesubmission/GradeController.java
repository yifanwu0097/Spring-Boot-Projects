package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id){ // if there is no param, it will be null by default
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : studentGrades.get(index));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade){
        int index = getGradeIndex(grade.getId());
        if(index == Constants.NOT_FOUND){
            studentGrades.add(grade); // add a new grade object if it does not exist
        } else {
            studentGrades.set(index, grade); // update the grade object that already exists
        }

        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    public Integer getGradeIndex(String id){
        for(int i = 0; i < studentGrades.size(); i++){
            if(studentGrades.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
}
