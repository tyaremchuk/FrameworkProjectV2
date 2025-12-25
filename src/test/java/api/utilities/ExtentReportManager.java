package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext context)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter("./reports/"+repName); //specify location for report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//report title
        sparkReporter.config().setReportName("PetStore Users API");//name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","PetStore Users API");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("Username",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("User","Taras");
    }

    public void onTestSuccess(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,"Test PASSED");
    }

    public void onTestFailure(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,"Test FAILED");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,"Test SKIPPED");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
}
