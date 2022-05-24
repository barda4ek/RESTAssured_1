package my.utils;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogListener implements ITestListener {

    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(response, true);

    public void onStart(ITestContext iTestContext) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.STATUS, responseVar),
                new ResponseLoggingFilter(LogDetail.HEADERS, responseVar),
                new RequestLoggingFilter(LogDetail.METHOD, requestVar),
                new RequestLoggingFilter(LogDetail.URI, requestVar),
                new RequestLoggingFilter(LogDetail.HEADERS, requestVar),
                new RequestLoggingFilter(LogDetail.BODY, requestVar));
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    public void onTestFailure(ITestResult iTestResult) {
    }

    @Attachment(value = "request")
    public byte[] logRequest(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    @Attachment(value = "response")
    public byte[] logResponse(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    public byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onFinish(ITestContext iTestContext) {
        //use Allure-restAssured logs
        //logRequest(request);
        //logResponse(response);
    }


}
