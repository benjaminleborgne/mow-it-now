package com.benjaminleb.mowitnow.mowing.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MowingPlan {
    private int gardenSizeX;
    private int gardenSizeY;
    private List<MowerPlan> mowerPlans;
}
