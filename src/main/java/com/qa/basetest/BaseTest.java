package com.qa.basetest;

import com.qa.utils.ExcelHelper;
import com.qa.utils.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Listeners({ BaseMethodsInterceptor.class })
public abstract class BaseTest {


    @BeforeMethod(alwaysRun = true)
    public synchronized void before(Method method) {
        //setup test.
        Reporter.info("### Starting test [" + method.getName() + "] ###");
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void after(Method method) {
        //teardown test.
        Reporter.info("### Finishing test [" + method.getName() + "] ###");
    }

    /**
     *
     * @param deviceName
     * @param platformName
     * @param platformVersion
     * @return
     */
    protected static ArrayList<Object[]> filterDevicesByArguments(String deviceName, String platformName, String platformVersion, String sheetName) {
        ArrayList<Object[]> filterMap = null;
        ExcelHelper excelHelper = new ExcelHelper();
        //grab all devices from a sheet.
        ArrayList<Object[]> allDevicesMap = excelHelper.retrieveAllRows("src/test/resources/devices/devicelist.xlsx", sheetName);

        //do some filtering here and return a single device (can easily be adapted to return multiple devices).
        if (!deviceName.isEmpty()) {
            if (!deviceName.contains(",")) {
                filterMap = excelHelper.filterMapByColumn(allDevicesMap, "name", deviceName);
            } else {
                // deviceName containes multiple devices.
                filterMap = excelHelper.filterMapByColumn(allDevicesMap, "name", deviceName.split(","));
            }
            // if platform is given filter by platform.
        } else if (!platformName.isEmpty()) {
            if (platformVersion.isEmpty()) {
                //filter by platform
                filterMap = excelHelper.filterMapByColumn(allDevicesMap, "platformName", platformName);
            } else  {
                // filter by platform and then version
                filterMap = excelHelper.filterMapByColumn(allDevicesMap, "platformName", platformName);
                filterMap = excelHelper.filterMapByColumn(filterMap, "platformVersion", platformVersion);
            }
        } else if (!platformVersion.isEmpty()) {
            //filter by platform Version
            filterMap = excelHelper.filterMapByColumn(allDevicesMap, "platformVersion", platformVersion);
        }

        return filterMap;
    }

}
