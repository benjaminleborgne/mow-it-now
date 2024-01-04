package com.benjaminleb.mowitnow.mowing.parser;

import com.benjaminleb.mowitnow.mowing.CardinalDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MowerPlan {
    private int initialPositionX;
    private int initialPositionY;
    private CardinalDirection initialFacing;
    private String movementInstructions;

}
