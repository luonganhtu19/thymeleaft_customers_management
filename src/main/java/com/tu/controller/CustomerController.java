package com.tu.controller;

import com.tu.model.Customer;
import com.tu.service.customerService.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @GetMapping("/")
    public String index(Model model){
        List customers= iCustomerService.findAll();
        model.addAttribute("customers",customers);
        return "/index";
    }
    @GetMapping("/customer/create")
    public ModelAndView create(){
        ModelAndView modelAndView=new ModelAndView("/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/customer/save")
    public ModelAndView save(@ModelAttribute("customer") Customer customerA,RedirectAttributes redirectAttributes){
        ModelAndView modelAndView=new ModelAndView("redirect:/");
        iCustomerService.save(customerA);
        redirectAttributes.addFlashAttribute("message","success");
        return modelAndView;
    }
    @GetMapping("/customer/{id}/edit")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView("/edit");
        modelAndView.addObject("customer",iCustomerService.findById(id));
        return modelAndView;
    }
    @PostMapping("/customer/edit")
    public ModelAndView saveEdit(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        iCustomerService.update(customer.getId(), customer);
        ModelAndView modelAndView= new ModelAndView("redirect:/customer/"+customer.getId()+"/edit");
        redirectAttributes.addFlashAttribute("message","success");
        return modelAndView;
    }
    @GetMapping("/customer/{id}/delete")
    public ModelAndView showInfoDelete(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView("/delete");
        modelAndView.addObject("customer",iCustomerService.findById(id));
        return modelAndView;
    }
    @PostMapping("customer/delete")
    public ModelAndView modelAndView(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        iCustomerService.remove(customer.getId());
        ModelAndView modelAndView= new ModelAndView("redirect:/customer/"+customer.getId()+"/delete");
        redirectAttributes.addFlashAttribute("message","success");
        return modelAndView;
    }
    @GetMapping("customer/{id}/view")
    public ModelAndView modelAndView(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView("/view");
        modelAndView.addObject("customer",iCustomerService.findById(id));
        return modelAndView;
    }
}
