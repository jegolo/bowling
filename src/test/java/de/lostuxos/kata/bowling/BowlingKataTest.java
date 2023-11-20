package de.lostuxos.kata.bowling;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingKataTest {

    @Test
    void testScoreWithoutStrikeAndSpare() {
        var bowlingKate = new BowlingKata();
        //Create a List of 10 Elements with 1 as entry
        var rolls = IntStream.generate( () -> 1).limit(10).boxed().toList();

        var score = bowlingKate.score(rolls);

        assertThat(score).isEqualTo(10);

    }

    @Test
    void testScoreWithASpare() {
        var bowlingKata = new BowlingKata();
        var rolls = List.of(1,9,2,1);

        var score = bowlingKata.score(rolls);

        assertThat(score).isEqualTo(15);
    }

    @Test
    void testScoreWithAStrike() {
        var bowlingKata = new BowlingKata();
        var rolls = List.of(10,2,3);

        var score = bowlingKata.score(rolls);

        assertThat(score).isEqualTo(20);
    }

    @Test
    void testScoreWithTwotrike() {
        var bowlingKata = new BowlingKata();
        var rolls = List.of(10,10,1,2,3,4);

        var score = bowlingKata.score(rolls);

        assertThat(score).isEqualTo(44);
    }

    @Test
    void testScoreWithThreestrike() {
        var bowlingKata = new BowlingKata();
        var rolls = List.of(10,10,10,1,2,3,4);

        var score = bowlingKata.score(rolls);

        assertThat(score).isEqualTo(74);
    }

}