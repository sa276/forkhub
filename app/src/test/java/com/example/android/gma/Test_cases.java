package com.example.android.gma;

import org.junit.Test;

import static org.junit.Assert.*;

public class Test_cases {

    //@Test
    //public void test_username1() {
        //assertEquals(global_test.test_username("GMA.venkat"),true);
    //}

    //@Test
    //public void test_username2() { assertEquals(global_test.test_username("ADV.KVS"),true);
    //}

    @Test
    public void test_username3() {
        assertEquals(global_test.test_username("SUB.sabarish"),true);
    }

    @Test
    public void test_username4() {
        assertEquals(global_test.test_username("EC.aakash"),true);
    }

    @Test
    public void test_username5() {
        assertEquals(global_test.test_username("CM.ganesh"),true);
    }

    @Test
    public void test_username6() {
        assertEquals(global_test.test_username("ABC.venkat"),true);
    }

    @Test
    public void test_username7() {
        assertEquals(global_test.test_username(""),true);
    }

    @Test
    public void test_password1() {
        assertEquals(global_test.test_password("sabarish"),true);
    }

    @Test
    public void test_password2() {
        assertEquals(global_test.test_password(""),true);
    }


    @Test
    public void test_password3() {
        assertEquals(global_test.test_password("skfjd"),true);
    }

    @Test
    public void test_username8() {
        assertEquals(global_test.test_username("cm.ganesh"),true);
    }

    @Test
    public void test_username9() {
        assertEquals(global_test.test_username("cm.GANESH"),true);
    }

    @Test
    public void test_password0() {
        assertEquals(global_test.test_password("123453"),true);
    }

    @Test
    public void test_password11() {
        assertEquals(global_test.test_password("1"),true);
    }

    @Test
    public void test_password12() {
        assertEquals(global_test.test_password("ASD123"),true);
    }

    @Test
    public void test_password13() {
        assertEquals(global_test.test_password("ASD123@#$%"),true);
    }


    @Test
    public void test_username11() {
        assertEquals(global_test.test_username("ASD.GANESH"),true);
    }

    @Test
    public void test_username12() {
        assertEquals(global_test.test_username("GMA.GANESH@"),true);
    }

    @Test
    public void test_username13() {
        assertEquals(global_test.test_username("GMA.GANESH123"),true);
    }

    @Test
    public void test_username14() {
        assertEquals(global_test.test_username("GMA@.GANESH@"),true);
    }

    @Test
    public void test_username15() {
        assertEquals(global_test.test_username("GMA123.GANESH@"),true);
    }

    @Test
    public void test_username16() {
        assertEquals(global_test.test_username("GMA.GANESH@"),true);
    }

    @Test
    public void test_password15() {
        assertEquals(global_test.test_password("."),true);
    }

    @Test
    public void test_password16() {
        assertEquals(global_test.test_password("@1234ASafsdfg"),true);
    }




}