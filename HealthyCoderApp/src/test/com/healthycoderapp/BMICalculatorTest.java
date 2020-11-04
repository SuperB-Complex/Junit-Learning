package com.healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void should_ReturnTrue_When_DietRecommended() {

        // given
        double weight = 77.0;
        double height = 1.72;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        // then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_When_DietRecommended() {

        // given
        double weight = 10.0;
        double height = 10.0;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        // then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_DietRecommended() {

        // given
        double weight = 10.0;
        double height = 0.0;

        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

        // then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_returnCoderWithWorstBMI_When_CoderListNotEmpty() {

        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.85, 64.7));

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        assertEquals(1.82, coderWorstBMI.getHeight());
        assertEquals(98.0, coderWorstBMI.getWeight());
    }

    @Test
    void should_returnCoderWithWorstBMI_When_CoderListNotEmpty_AssertAll() {

        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.85, 64.7));

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        // the reason of using assertAll is that it can force executing all assertions
        // originally you can see where the first assertions failed
        // but sometimes you need to see results of all assertions
        // To sum up, assertAll should be used for assertions that makes sense as a whole
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98.0, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_returnNullWithWorstBMI_When_CoderListIsEmpty() {

        // given
        List<Coder> coders = new ArrayList<>();

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        assertNull(coderWorstBMI);
    }
}