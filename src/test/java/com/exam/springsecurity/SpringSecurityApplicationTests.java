package com.exam.springsecurity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
//class SpringSecurityApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}


class SpringSecurityApplicationTests {

    Calculator calculatorTest = new Calculator();

    @Test
    void itShouldAddNumbers() {
        //given
        int one = 29;
        int two = 51;

        //when
        int result = calculatorTest.add(one, two);

        //then
        int expected = 80;
        assertThat(result).isEqualTo(expected);

    }

    class Calculator {

        int add(int x, int y) {
            return x + y;
        }
    }

}
