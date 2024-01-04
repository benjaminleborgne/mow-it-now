package com.benjaminleb.mowitnow.mowing;

import com.benjaminleb.mowitnow.mowing.parser.MowingPlan;
import com.benjaminleb.mowitnow.mowing.parser.MowingPlanParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MowingService {

    public String launchMowing(MultipartFile instructionFile) {
        String instructions = null;
        try {
            instructions = new String(instructionFile.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while reading instruction file");
        }
        return launchMowing(instructions);
    }

    public String launchMowing(String instructions){
        MowingPlan mowingPlan = MowingPlanParser.fromInstructions(instructions);
        Garden garden = initFromMowingPlan(mowingPlan);
        for(int i = 0; i < mowingPlan.getMowerPlans().size(); i++) {
            String[] mowerMovementInstructions = mowingPlan.getMowerPlans().get(i).getMovementInstructions().split("");
            for(String instruction : mowerMovementInstructions) {
                moveMower(garden, i, instruction);
            }
        }

        return garden.getMowers().stream().map(m -> m.toString()).collect(Collectors.joining(" "));
    }

    private void moveMower(Garden garden, int mowerIndex, String movementInstruction) {
        Mower mower = garden.getMowers().get(mowerIndex);
        switch (movementInstruction) {
            case "D" -> mower.turnRight();
            case "G" -> mower.turnLeft();
            case "A" -> {
                Coordinates nextCoordinates = mower.calculateNextMove();
                if (garden.areCoordinatesReachable(nextCoordinates)) {
                    mower.moveOnTo(nextCoordinates);
                }
            }
        }
    }

    private Garden initFromMowingPlan(MowingPlan mowingPlan) {
        List<Mower> mowers = mowingPlan.getMowerPlans()
                .stream()
                .map(Mower::fromPlan)
                .collect(Collectors.toList());
        return Garden.fromPlan(mowingPlan);
    }
}
