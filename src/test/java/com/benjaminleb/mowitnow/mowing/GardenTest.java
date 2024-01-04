package com.benjaminleb.mowitnow.mowing;

import com.benjaminleb.mowitnow.mowing.parser.MowerPlan;
import com.benjaminleb.mowitnow.mowing.parser.MowingPlan;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GardenTest {

    @Test
    public void should_not_build_garden_with_mowers_at_the_same_coordinates() {
        MowingPlan plan = new MowingPlan(
                5,
                5,
                List.of(
                        new MowerPlan(1, 1, CardinalDirection.N, ""),
                        new MowerPlan(1, 1, CardinalDirection.W, "")
                ));
        Assertions.assertThatThrownBy(() -> Garden.fromPlan(plan)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_not_build_garden_with_mower_out_of_it() {
        MowingPlan plan1 = new MowingPlan(
                5,
                5,
                List.of(
                        new MowerPlan(8, 1, CardinalDirection.N, "")
                ));
        Assertions.assertThatThrownBy(() -> Garden.fromPlan(plan1)).isInstanceOf(IllegalArgumentException.class);

        MowingPlan plan2 = new MowingPlan(
                5,
                5,
                List.of(
                        new MowerPlan(1, 8, CardinalDirection.N, "")
                ));
        Assertions.assertThatThrownBy(() -> Garden.fromPlan(plan2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_build_according_to_plan() {
        MowingPlan plan = new MowingPlan(
                5,
                5,
                List.of(
                        new MowerPlan(1, 2, CardinalDirection.N, ""),
                        new MowerPlan(3, 1, CardinalDirection.W, "")
                ));

        Garden expectedGarden = new Garden(
                5,
                5,
                List.of(
                        new Mower(new Coordinates(1, 2), CardinalDirection.N),
                        new Mower(new Coordinates(3, 1), CardinalDirection.W)
                ));

        Assertions.assertThat(expectedGarden).isEqualToComparingFieldByFieldRecursively(Garden.fromPlan(plan));
    }

    @Test
    public void should_validate_coordinates() {
        Garden garden = new Garden(5, 5, List.of(new Mower(new Coordinates(1, 2), CardinalDirection.N)));

        Coordinates validCoordinates = new Coordinates(0, 2);
        Assertions.assertThat(garden.areCoordinatesReachable(validCoordinates)).isTrue();

        Coordinates outsidingCoordinates = new Coordinates(6, 2);
        Assertions.assertThat(garden.areCoordinatesReachable(outsidingCoordinates)).isFalse();

        Coordinates negativeCoordinates = new Coordinates(-2, 2);
        Assertions.assertThat(garden.areCoordinatesReachable(negativeCoordinates)).isFalse();

        Coordinates alreadyOccupiedCoordinates = new Coordinates(1, 2);
        Assertions.assertThat(garden.areCoordinatesReachable(alreadyOccupiedCoordinates)).isFalse();
    }
}
