package com.benjaminleb.mowitnow.mowing;

import com.benjaminleb.mowitnow.mowing.parser.MowerPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Mower {
    private Coordinates coordinates;
    private CardinalDirection facingDirection;

    public void moveOnTo(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates calculateNextMove() {
        Coordinates nextMove = new Coordinates(coordinates.getX(), coordinates.getY());
        switch (facingDirection) {
            case N -> nextMove.setY(nextMove.getY() + 1);
            case S -> nextMove.setY(nextMove.getY() - 1);
            case E -> nextMove.setX(nextMove.getX() + 1);
            case W -> nextMove.setX(nextMove.getX() - 1);
        }
        return nextMove;
    }

    public void turnRight() {
        switch (facingDirection) {
            case N -> facingDirection = CardinalDirection.E;
            case E -> facingDirection = CardinalDirection.S;
            case S -> facingDirection = CardinalDirection.W;
            case W -> facingDirection = CardinalDirection.N;
        }
    }

    public void turnLeft() {
        switch (facingDirection) {
            case N -> facingDirection = CardinalDirection.W;
            case W -> facingDirection = CardinalDirection.S;
            case S -> facingDirection = CardinalDirection.E;
            case E -> facingDirection = CardinalDirection.N;
        }
    }

    public static Mower fromPlan(MowerPlan mowerPlan) {
        return new Mower(new Coordinates(mowerPlan.getInitialPositionX(), mowerPlan.getInitialPositionY()), mowerPlan.getInitialFacing());
    }

    @Override
    public String toString() {
        return coordinates.getX() + " " + coordinates.getY() + " " + facingDirection;
    }
}
