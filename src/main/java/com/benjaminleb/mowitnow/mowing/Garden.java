package com.benjaminleb.mowitnow.mowing;

import com.benjaminleb.mowitnow.mowing.parser.MowingPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Garden {
    private int lengthX;
    private int lengthY;
    private List<Mower> mowers;


    public boolean areCoordinatesReachable(Coordinates coordinates) {
        return 0 <= coordinates.getX() && coordinates.getX() <= lengthX
                && 0 <= coordinates.getY() && coordinates.getY() <= lengthY
                && !isThereAnObstacleAtCoordinates(coordinates);
    }

    private boolean isThereAnObstacleAtCoordinates(Coordinates coordinates){
        if (mowers == null) return false;
        return mowers.stream().anyMatch(c -> c.getCoordinates().getY() == coordinates.getY() && c.getCoordinates().getX() == coordinates.getX());
    }

    public static Garden fromPlan(MowingPlan mowingPlan) {
        Garden garden = new Garden(
                mowingPlan.getGardenSizeX(),
                mowingPlan.getGardenSizeY(),
                new ArrayList<>()
        );

        mowingPlan.getMowerPlans().forEach(mowerPlan -> {
            Mower mower = Mower.fromPlan(mowerPlan);
            if(garden.areCoordinatesReachable(mower.getCoordinates())) {
                garden.getMowers().add(mower);
            } else throw new IllegalArgumentException("Incoherent instructions given");
        });

        return garden;
    }


}
