package de.lostuxos.kata.bowling;

import java.util.List;

public class BowlingKata {

    record Roll(int rollInFrame, int frame, int pins, int bonusRolls, int score, boolean strike, boolean doubleStrike) {}

    public int score(List<Integer> rolls) {

        var result = rolls.stream()
                .reduce(new Roll(-1,-1, 0, 0, 0, false, false),
                        this::accmulate,
                        (x, y) -> y
                );

        return result.score;
    }


    private Roll accmulate(Roll previousRoll, Integer pins) {
        boolean strike = false;
        int bonusRolls = previousRoll.bonusRolls()>0?previousRoll.bonusRolls - 1:0;
        int rollInFrame = previousRoll.strike()?0:(previousRoll.rollInFrame+1) % 2;
        int frame = previousRoll.strike() || (previousRoll.rollInFrame() + 1) % 2==0?previousRoll.frame()+1:previousRoll.frame();

        boolean doubleStrike = false;
        System.out.printf("Frame: %d Roll: %d Bonus: %d\n", frame, rollInFrame, previousRoll.bonusRolls());

        if (rollInFrame==1 && previousRoll.pins+pins == 10) {
            System.out.println("Spare!");
            bonusRolls = 1;
        }

        if (previousRoll.rollInFrame<1 && pins == 10) {
            System.out.println("Strike!!!");

            bonusRolls =2;
            strike = true;
            doubleStrike = previousRoll.strike();

        }

        int multiplicator = previousRoll.doubleStrike()?3:2;
        int score = previousRoll.bonusRolls()>0?previousRoll.score()+multiplicator*pins: previousRoll.score() + pins;
        return new Roll(rollInFrame, frame, pins, bonusRolls, score, strike, doubleStrike);
    }
}
