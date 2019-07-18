package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController
{
    @Autowired
    TODORepository todoRepository;

    @RequestMapping("/")
    public String listform(Model model)
    {
        model.addAttribute("todos",todoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoform(Model model)
    {
        model.addAttribute("todo", new TODO());
        return "todoform";
    }

    @PostMapping("/process")
    public String processTvForm(@Valid TODO todo,
                                BindingResult result)
    {
        if(result.hasErrors())
        {
            return "todoform";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showtodo(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updatetodo(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "courseform";

    }

    @RequestMapping("/delete/{id}")
    public String deltodo(@PathVariable("id") Long id)
    {
        todoRepository.deleteById(id);
        return "redirect:/";

    }
}
