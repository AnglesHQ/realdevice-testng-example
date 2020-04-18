package com.qa.tests.parallel.example_07;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;


public class BasicMethodInterceptor implements IMethodInterceptor {

    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> result = new ArrayList<IMethodInstance>();

        // go through each of the test methods and check if they meet the criteria set out.
        for (IMethodInstance m : methods) {
            // random condition to not run a test that contains the number 3
            if (!m.getMethod().getMethodName().contains("3")) {
                //add your own conditions to include the test on not.
                result.add(m);
            }
        }
        return result;
    }
}
