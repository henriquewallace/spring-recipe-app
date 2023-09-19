package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_VALUE);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        assertEquals(ID_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}