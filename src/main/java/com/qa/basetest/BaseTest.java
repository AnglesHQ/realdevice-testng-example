package com.qa.basetest;

import com.github.angleshq.angles.listeners.testng.AnglesBaseTest;
import com.qa.utils.ExcelHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;

import java.util.ArrayList;

@Listeners({ BaseMethodsInterceptor.class })
public abstract class BaseTest extends AnglesBaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     *
     * @param deviceName
     * @param platformName
     * @param platformVersion
     * @return
     */
    protected static ArrayList<Object[]> filterDevicesByArguments(String deviceName, String platformName, String platformVersion, Boolean supported, String sheetName) {
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
        } else if (supported) {
            //filter by supported devices.
            filterMap = excelHelper.filterMapByColumn(allDevicesMap, "supported", "true");
        }
        return filterMap;
    }

}
