package cz.czechitas.java2webapps.lekce5.people;

import cz.czechitas.java2webapps.lekce5.entity.Gender;
import cz.czechitas.java2webapps.lekce5.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

/**
 * @author Filip Jirsák
 */
@Controller
public class FamousPeopleController {
  private final FamousPeopleService service;

  public FamousPeopleController(FamousPeopleService service) {
    this.service = service;
  }

  @GetMapping("/")
  public ModelAndView list() {
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", service.getAll());
    result.addObject("gender", Gender.values());
    return result;
  }

  @GetMapping(path = "/", params = "query")
  public ModelAndView search(String query) {
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", service.getByName(query));
    result.addObject("gender", Gender.values());
    result.addObject("query", query);
    return result;
  }
  @PostMapping(path = "/delete", params = "id")
  public String delete (int id) {
    service.deleteById(id);
    return "redirect:/";
  }

  @PostMapping(path = "/")
  public String append (Person person) {
    service.append(person);
    return "redirect:/";
  }
  @GetMapping("/test")
  public String redirect() {
    return "redirect:/test-po-presmerovani";
  }
}

