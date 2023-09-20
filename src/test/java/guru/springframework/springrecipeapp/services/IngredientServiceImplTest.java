package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(5L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setUom(unitOfMeasure);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);
        ingredient2.setUom(unitOfMeasure);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setUom(unitOfMeasure);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //then
        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}