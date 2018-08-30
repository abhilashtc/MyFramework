package com.facebook.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.StringTokenizer;

import com.facebook.TestBase.TestBase;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class JIRA_Ticket {
	public static String issueSummary, issueDesc, issuePriority;
	public static String testCaseName;
	public static String failedTestScreenCapture;

	//** This method will create a Jira Ticket and will include the screenshot of the failed test case in the issue description
		public static void createJiraIssue(String failedTestScreenShot) throws Exception{
			failedTestScreenCapture = failedTestScreenShot;
			//*** Getting Jira Server Details
			String currentDir = System.getProperty("user.dir");
			String configDir = currentDir + "/Configuration";
			FileReader file = new FileReader(configDir + "/Jira.properties");
			Properties prop = new Properties();
			prop.load(file);
			String jiraProjectName = prop.getProperty("JiraProjectName");
			String url = prop.getProperty("JiraServer");
			String Username = prop.getProperty("JiraUsername");
			String Password = prop.getProperty("JiraPassword");
			file.close();
					
			
			Client client = Client.create();
			
			System.err.println("--------Inside createJiraIssue");
			// Provide the credentials
			client.addFilter(new HTTPBasicAuthFilter(Username, Password));
			WebResource webResource = client.resource(url);			
			
			issueSummary = "Automated Test Issue Summary";
			issueDesc = "This is an automated Test Issue Description. Here is the screen shot for the failed test. " + failedTestScreenShot;
			
			
			System.out.println("~~~~~~~~~~~~~~~~createJiraIssue~~~~~~~~~~~~~~~~~~~");
			// This JSON string contains all values for the ticket
//			String input="{\"fields\":{\"project\":{\"key\":\"" + jiraProjectName + "\"},\"summary\":\"Demo Ticket\",\"description\":\"Script was executed at " + scriptStartTime + "\", \"reporter\": {\"name\": \"" + Username + "\"},\"issuetype\":{\"name\":\"Bug\"}}}";
			
			String input="{\"fields\":{\"project\":"
					+ "{\"key\":\"" + jiraProjectName + "\"},\""
							+ "summary\":\"" + issueSummary + "\", \""
							+ "description\":\"" + issueDesc + "\", \""
									+ "reporter\": {\"name\": \"" + Username + "\"},\""
									+ "priority\":{\"name\":\"High\"},\""
									+ "issuetype\":{\"name\":\"Bug\"}}}";
			
			System.out.println(input);
			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
			String output = response.getEntity(String.class);
			System.out.println("JIRA Server returns:\n" + output);
			TestBase.JIRA_Ticket = getJIRA_Ticket(output);
			System.out.println("Created JIRA Ticket Successfully... " + TestBase.JIRA_Ticket);
			addJiraTicketDetailsToFile(TestBase.JIRA_Ticket);
			
		}

	// *** This method will return the Jira Ticket number from the REST API Output
	// received while creating the Jira Ticket
	public static String getJIRA_Ticket(String ticketDetails) throws Exception {
		String ticket = "";
		StringTokenizer st1 = new StringTokenizer(ticketDetails, ",");
		int ctr = 1;
		while (st1.hasMoreTokens()) {
			String s1 = st1.nextToken();
			if (ctr == 2) {
				StringTokenizer st2 = new StringTokenizer(s1, "\"");

				int ctr2 = 1;
				while (st2.hasMoreTokens()) {
					ticket = st2.nextToken();
					if (ctr2 == 3)
						break;
					ctr2++;
				}
			}
			ctr++;
		}
//		addJiraTicketDetailsToFile(ticket);
		return ticket;
	}

	public static void addJiraTicketDetailsToFile(String jiraTicket) throws Exception {
		String jiraTicketNum = jiraTicket;
		String currentDir = System.getProperty("user.dir");
		String configDir = currentDir + "/TestReport/JiraTickets.txt";
		System.out.println("Directory is " + currentDir);
		System.out.println(">>>>>>>>>>>>>>>>>>>> Inside addJiraTicketDetailsToFile " + jiraTicket);

		Path filePath = Paths.get(configDir);
		if (!Files.exists(filePath)) {
			Files.createFile(filePath);
			System.out.println("---> " + configDir + " has been created successfully.");
			Files.write(filePath, jiraTicket.getBytes(), StandardOpenOption.APPEND);
		} else {
			if (TestBase.jiraFlag == true) {
				Files.write(filePath, jiraTicket.getBytes(), StandardOpenOption.APPEND);
				TestBase.jiraFlag = false;
			} else {
				jiraTicket = ", " + jiraTicket;
				Files.write(filePath, jiraTicket.getBytes(), StandardOpenOption.APPEND);
			}
		}
		System.out.println("JIRA Ticket Details successfully added for " + jiraTicketNum);
	}

	public static void getTestCaseDetails() {
		issueSummary = "Automated Test Issue Summary";
		issueDesc = "This is an automated Test Issue Description. Here is the screen shot for the failed test. "
				+ failedTestScreenCapture;

	}

}
