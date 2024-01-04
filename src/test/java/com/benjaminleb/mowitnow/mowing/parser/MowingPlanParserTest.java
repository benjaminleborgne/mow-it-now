package com.benjaminleb.mowitnow.mowing.parser;
import com.benjaminleb.mowitnow.mowing.CardinalDirection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class MowingPlanParserTest {

    @Test()
    public void should_throw_exception_given_incorrect_instructions(){
        String invalidInstructions = "AK 11 DJA";
        Assertions.assertThatThrownBy(() -> MowingPlanParser.fromInstructions(invalidInstructions)).isInstanceOf(IllegalArgumentException.class);

        String missingGardenLength = "1 3 N GAGAGAGAA 3 3 E AADAADADDA";
        Assertions.assertThatThrownBy(() -> MowingPlanParser.fromInstructions(missingGardenLength)).isInstanceOf(IllegalArgumentException.class);

        String invalidCardinals = "5 5 1 1 F GGGA";
        Assertions.assertThatThrownBy(() -> MowingPlanParser.fromInstructions(invalidCardinals)).isInstanceOf(IllegalArgumentException.class);

        String invalidMovements = "5 5 1 1 N DDGAX";
        Assertions.assertThatThrownBy(() -> MowingPlanParser.fromInstructions(invalidMovements)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test()
    public void should_build_mowing_plans(){
        String instructions = "5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA";
        MowingPlan parsedPlan = MowingPlanParser.fromInstructions(instructions);

        MowerPlan expectedMowerPlan1 = new MowerPlan(1, 2, CardinalDirection.N, "GAGAGAGAA");
        MowerPlan expectedMowerPlan2 = new MowerPlan(3, 3, CardinalDirection.E, "AADAADADDA");
        MowingPlan expectedPlan = new MowingPlan(5, 5, List.of(expectedMowerPlan1, expectedMowerPlan2));

        Assertions.assertThat(parsedPlan).isEqualToComparingFieldByFieldRecursively(expectedPlan);
    }
}
