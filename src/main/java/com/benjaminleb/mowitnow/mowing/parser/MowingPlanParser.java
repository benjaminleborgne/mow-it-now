package com.benjaminleb.mowitnow.mowing.parser;

import com.benjaminleb.mowitnow.mowing.CardinalDirection;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MowingPlanParser {

    public static MowingPlan fromInstructions(String instructions) {
        validateInstructionsFormat(instructions);

        String[] splittedInstructions = instructions.split(" ");
        String[] gardenSize = Arrays.copyOfRange(splittedInstructions, 0, 2);
        String[] mowersInstructions = Arrays.copyOfRange(splittedInstructions, 2, splittedInstructions.length);
        List<MowerPlan> mowerPlans = new ArrayList<>();
        // splitting instructions by mowers
        int numberOfMowers = mowersInstructions.length / 4;
        for (int i = 0; i < numberOfMowers; i++) {
            int initialPositionX = Integer.valueOf(mowersInstructions[i * 4]);
            int initialPositionY = Integer.valueOf(mowersInstructions[(i * 4) + 1]);
            CardinalDirection initialFacing = Enum.valueOf(CardinalDirection.class, mowersInstructions[(i * 4) + 2]);
            String mowerInstructions = mowersInstructions[(i * 4) + 3];
            mowerPlans.add(new MowerPlan(initialPositionX, initialPositionY, initialFacing, mowerInstructions));
        }
        return new MowingPlan(
                Integer.valueOf(gardenSize[0]),
                Integer.valueOf(gardenSize[1]),
                mowerPlans
        );


    }

    private static void validateInstructionsFormat(String instructions) throws IllegalArgumentException {
        if (!instructions.matches("((\\d \\d)( (\\d \\d) ([NSWE]) ([ADG])+)+)")) {
            throw new IllegalArgumentException("Bad format for given instructions");
        }
    }
}
