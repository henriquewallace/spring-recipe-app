package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.domain.Category;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
