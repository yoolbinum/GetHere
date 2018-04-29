package com.example.demo;

import com.example.demo.utils.visa.MethodTypes;
import com.example.demo.utils.visa.VisaAPIClient;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestFundsTransfer {

    String pushFundsRequest;
    VisaAPIClient visaAPIClient;

    @BeforeTest(groups = "visadirect")
    public void setup() {
        this.visaAPIClient = new VisaAPIClient();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        this.pushFundsRequest = 
				"{"
					+ "\"systemsTraceAuditNumber\":451000,"
					+ "\"retrievalReferenceNumber\":\"330000550000\","
					+ "\"localTransactionDateTime\":\""+strDate +"\","
					+ "\"acquiringBin\":408999,\"acquirerCountryCode\":\"840\","
					+ "\"senderAccountNumber\":\"4957030420210470\","
					+ "\"senderCountryCode\":\"USA\","
					+ "\"transactionCurrencyCode\":\"USD\","
					+ "\"senderName\":\"Harry Potter\","
					+ "\"senderAddress\":\"44 Market St.\","
					+ "\"senderCity\":\"San Francisco\","
					+ "\"senderStateCode\":\"CA\","
					+ "\"recipientName\":\"Jon Snow\","
					+ "\"recipientPrimaryAccountNumber\":\"4957030420210462\","
					+ "\"amount\":\"12.00\","
					+ "\"businessApplicationId\":\"AA\","
					+ "\"transactionIdentifier\":381228649430011,"
					+ "\"merchantCategoryCode\":6012,"
					+ "\"sourceOfFundsCode\":\"03\","
					+ "\"cardAcceptor\":{"
										+ "\"name\":\"Acceptor 4\","
										+ "\"terminalId\":\"365539\","
										+ "\"idCode\":\"VMT200911026070\","
										+ "\"address\":{"
														+ "\"state\":\"CA\","
														+ "\"county\":\"081\","
														+ "\"country\":\"USA\","
														+ "\"zipCode\":\"94105\""
											+ "}"
										+ "},"
					+ "\"feeProgramIndicator\":\"123\""
				+ "}";
    }

    @Test(groups = "visadirect")
    public void testPushFundsTransactions() throws Exception {
        String baseUri = "visadirect/";
        String resourcePath = "fundstransfer/v1/pushfundstransactions/";

        CloseableHttpResponse response = this.visaAPIClient.doMutualAuthRequest(baseUri + resourcePath, "Push Funds Transaction Test", this.pushFundsRequest, MethodTypes.POST, new HashMap<String, String>());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        response.close();
    }

}
