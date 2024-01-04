package com.benjaminleb.mowitnow.mowing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MowerTest {

    @Test()
    public void should_turn() {
        Mower mower = new Mower(new Coordinates(1, 1), CardinalDirection.N);
        //to west
        mower.turnLeft();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.W);
        //to south
        mower.turnLeft();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.S);
        //to west
        mower.turnRight();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.W);
        //to north
        mower.turnRight();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.N);
        //to east
        mower.turnRight();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.E);
        //to south
        mower.turnRight();
        Assertions.assertThat(mower.getFacingDirection()).isEqualTo(CardinalDirection.S);
    }

    @Test()
    public void should_move() {
        Mower mower = new Mower(new Coordinates(1, 1), CardinalDirection.N);

        Coordinates expectedCoordinates1 = new Coordinates(1, 2);
        mower.moveOnTo(expectedCoordinates1);
        Assertions.assertThat(mower.getCoordinates()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates1);

        Coordinates expectedCoordinates2 = new Coordinates(3, 3);
        mower.moveOnTo(expectedCoordinates2);
        Assertions.assertThat(mower.getCoordinates()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates2);
    }

    @Test
    public void should_calculate_next_move() {
        Mower mower1 = new Mower(new Coordinates(4, 8), CardinalDirection.N);
        Coordinates expectedCoordinates1 = new Coordinates(4, 9);
        Assertions.assertThat(mower1.calculateNextMove()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates1);

        Mower mower2 = new Mower(new Coordinates(4, 8), CardinalDirection.W);
        Coordinates expectedCoordinates2 = new Coordinates(3, 8);
        Assertions.assertThat(mower2.calculateNextMove()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates2);

        Mower mower3 = new Mower(new Coordinates(4, 8), CardinalDirection.S);
        Coordinates expectedCoordinates3 = new Coordinates(4, 7);
        Assertions.assertThat(mower3.calculateNextMove()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates3);

        Mower mower4 = new Mower(new Coordinates(4, 8), CardinalDirection.E);
        Coordinates expectedCoordinates4 = new Coordinates(5, 8);
        Assertions.assertThat(mower4.calculateNextMove()).isEqualToComparingFieldByFieldRecursively(expectedCoordinates4);

    }
}
