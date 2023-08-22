package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.domain.Category;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Brazilian");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
