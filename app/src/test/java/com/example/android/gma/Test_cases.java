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


    @Test
    public void test_gm1() {
        assertEquals(global_test.gm_check(8),true);
    }

    @Test
    public void test_gm2() {
        assertEquals(global_test.gm_check(20),true);
    }

    @Test
    public void test_gm3() {
        assertEquals(global_test.gm_check(-10),true);
    }


    @Test
    public void test_p1() {
        assertEquals(global_test.p1_p2(-10),true);
    }

    @Test
    public void test_p2() {
        assertEquals(global_test.p1_p2(100),true);
    }

    @Test
    public void test_p3() {
        assertEquals(global_test.p1_p2(47),true);
    }

    @Test
    public void test_es1() {
        assertEquals(global_test.es(100),true);
    }

    @Test
    public void test_es2() {
        assertEquals(global_test.es(0),true);
    }

    @Test
    public void test_es3() {
        assertEquals(global_test.es(-1),true);
    }

    @Test
    public void test_at1() {
        assertEquals(global_test.at(-1),true);
    }

    @Test
    public void test_at2() {
        assertEquals(global_test.at(10),true);
    }

    @Test
    public void test_at3() {
        assertEquals(global_test.at(4),true);
    }

    @Test
    public void test_ca1() {
        assertEquals(global_test.ca(5),true);
    }

    @Test
    public void test_ca2() {
        assertEquals(global_test.ca(-3),true);
    }

    @Test
    public void test_ca3() {
        assertEquals(global_test.ca(30),true);
    }



}