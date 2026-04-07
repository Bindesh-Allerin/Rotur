package com.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/ExtentReport.html");

            spark.config().setReportName("Rotur Test Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "Rotur - Release Management");
            extent.setSystemInfo("Test Engineer", "Bindesh");
        }

        return extent;
    }
}

