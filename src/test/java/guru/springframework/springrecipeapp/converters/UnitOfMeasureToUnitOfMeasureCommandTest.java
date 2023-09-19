package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    UnitOfMeasureToUnitOfMeasureCommand converter;
    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertEquals(ID_VALUE, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}