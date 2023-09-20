package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesCommandConverter;
    private final CategoryToCategoryCommand categoryCommandConverter;
    private final IngredientToIngredientCommand ingredientCommandConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesCommandConverter, CategoryToCategoryCommand categoryCommandConverter, IngredientToIngredientCommand ingredientCommandConverter) {
        this.notesCommandConverter = notesCommandConverter;
        this.categoryCommandConverter = categoryCommandConverter;
        this.ingredientCommandConverter = ingredientCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesCommandConverter.convert(source.getNotes()));

        if (!source.getCategories().isEmpty()) {
            source.getCategories().forEach(category -> recipeCommand.getCategories().add(categoryCommandConverter.convert(category)));
        }

        if (!source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientCommandConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
