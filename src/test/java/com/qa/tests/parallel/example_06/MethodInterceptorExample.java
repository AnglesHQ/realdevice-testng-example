package com.qa.tests.parallel.example_06;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({ BasicMethodInterceptor.class })
public class MethodInterceptorExample extends BaseTest {

    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ThreadSafeExampleOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ThreadSafeExampleOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod(Method method) {
        testName.set(method.getName());
        Reporter.info("ThreadSafeExampleOne: @BeforeMethod" + "[ " + testName.get() + "]");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ThreadSafeExampleOne: @AfterMethod" + "[ " + testName.get() + "]");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest1() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest1" + "[ " + testName.get() + "]");
    }

    @Test
    private void ParallelInstancesOneTest2() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest2" + "[ " + testName.get() + "]");
    }

    @Test
    private void ParallelInstancesOneTest3() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest3" + "[ " + testName.get() + "]");
    }
}
