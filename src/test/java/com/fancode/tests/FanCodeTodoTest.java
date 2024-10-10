package com.fancode.tests;
import java.util.List;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import com.fancode.common.TodoUtils;
import com.fancode.report.ReportUtils;
import com.google.gson.JsonArray;

// import com.fancode.common.TodoUtils;

public class FanCodeTodoTest {
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        ReportUtils.initReport();
        System.out.println("Report initialized.");
    }

    @Test
    public void verifyUserCompletedTasksGreaterThanFiftyPercent() {
        // Initialize the ExtentTest instance
        test = ReportUtils.createTest("Verify that users from FanCode have more than 50% of their tasks completed");

        // Fetch all users
        JsonArray users = TodoUtils.getUsers();
        test.info("Fetched " + users.size() + " users from API.");
        System.out.println("Fetched " + users.size() + " users from API.");

        // Filter users belonging to FanCode city
        List<Integer> fanCodeUserIds = TodoUtils.filterFanCodeUsers(users);
        test.info("Filtered " + fanCodeUserIds.size() + " users belonging to FanCode city.");
        System.out.println("Filtered " + fanCodeUserIds.size() + " users belonging to FanCode city.");

        // Initialize a summary variable to count pass/fail
        int passedCount = 0;
        int failedCount = 0;

        // For each FanCode user, check if completed tasks percentage > 50%
        for (int userId : fanCodeUserIds) {
            double completionPercentage = TodoUtils.getTaskCompletionPercentage(userId);
            // Ensure the completion percentage is calculated correctly
            String logMessage = String.format("User %d has %.2f task completion", userId, completionPercentage);

            // Log task completion percentage for each user
            System.out.println(logMessage);

            // Assert that the completed task percentage is greater than 50%
            if (completionPercentage > 0.5) { // Check if the completion is more than 50%
                test.pass("User " + userId + " has more than 50% tasks completed. " + logMessage);
                System.out.println("PASS: " + logMessage);
                passedCount++;
            } else {
                test.fail("User " + userId + " has less than 50% tasks completed. " + logMessage);
                System.out.println("FAIL: " + logMessage);
                failedCount++;
            }
        }

        // Log summary of results
        String summaryMessage = String.format("Test completed. Passed: %d, Failed: %d", passedCount, failedCount);
        test.info(summaryMessage);
        System.out.println(summaryMessage);
    }

    @AfterClass
    public void tearDown() {
        ReportUtils.flushReport();
        System.out.println("Test execution completed. Reports flushed.");
    }
}
