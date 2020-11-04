package com.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    // must be static and will be executed before all unit tests
    // usually it is used for expensive setting ups
    @BeforeAll
    static void beforeAllUnitTests() {
        System.out.println("Before all unit tests starting~");
    }

    // must be static
    @AfterAll
    static void afterAllUnitTests() {
        System.out.println("After all unit tests ending~");
    }

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

    @ParameterizedTest
    @ValueSource(doubles = {89.0, 95.0, 110.0})
    void should_ReturnTrue_When_DietRecommended(Double coderWeight) {

        // given
        double weight = coderWeight;
        double height = 1.72;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        // then
        assertTrue(recommended);
    }

    // the parameter value is mandatory
    @ParameterizedTest
    @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
    void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {

        // given
        double weight = coderWeight;
        double height = coderHeight;

        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

        // then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "weight = {0}, height = {1}")
    @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
    void should_ReturnTrue_When_DietRecommended_Parameterized(Double coderWeight, Double coderHeight) {

        // given
        double weight = coderWeight;
        double height = coderHeight;

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

    @Test
    void should_returnCorrectBMIScoreArray_When_CoderListNotEmpty() {

        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};

        // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);

        // then
        assertArrayEquals(expected, bmiScores);
    }
}