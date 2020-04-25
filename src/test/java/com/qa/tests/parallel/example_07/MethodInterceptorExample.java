package com.qa.tests.parallel.example_07;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({ BasicMethodInterceptor.class })
public class MethodInterceptorExample extends BaseTest {

    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("MethodInterceptorExample: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("MethodInterceptorExample: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod(Method method) {
        testName.set(method.getName());
        Reporter.info("MethodInterceptorExample: @BeforeMethod" + "[ " + testName.get() + "]");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("MethodInterceptorExample: @AfterMethod" + "[ " + testName.get() + "]");
        pause();
    }

    @Test
    private void MethodInterceptorExampleTest1() {
        Reporter.info("MethodInterceptorExample: Test1" + "[ " + testName.get() + "]");
    }

    @Test
    private void MethodInterceptorExampleTest2() {
        Reporter.info("MethodInterceptorExample: Test2" + "[ " + testName.get() + "]");
    }

    // this test will be intercepted
    @Test
    private void MethodInterceptorExampleTest3() {
        Reporter.info("MethodInterceptorExample: Test3" + "[ " + testName.get() + "]");
    }
}
