UI Tests for Email Services

Task Description

This project is designed to write UI tests for various public email service  Mail.ru using the Chrome browser and a screen resolution of 1920x1080. 

Preparing for Testing

Install the Chrome browser if it's not already on your computer.
Set up the necessary dependencies for the project (WebDriver, testing frameworks, etc.).

Testing Scenarios

Scenario 1 - Working with Drafts
1. Log in to the email account.
Verify successful login.
2. Create a new email (fill in the recipient, subject, and body).
3. Save the email as a draft.
4. Verify that the email is saved in drafts.
5. Verify the content, recipient, and subject of the email.
6. Send the email.
7. Verify that the email disappeared from drafts.
8. Verify that the email appeared in the "Sent" folder.
9. Log out of the account.

Scenario 2 - Working with Inbox and Trash

1. Log in to the email account.
2. Verify successful login.
3. Create a new email (fill in the recipient, subject, and body).
4. Send the email.
5. Verify that the email appeared in the "Inbox" folder.
6. Verify the content, recipient, and subject of the email.
7. Delete the email.
8. Verify that the email appeared in the "Trash" folder.
9. Log out of the account.

Running the Tests

Clone the repository to your local machine.
Open the project in your preferred IDE.
Configure the browser and resolution for the tests.
Run the tests using your chosen testing framework.

Dependencies

Make sure you have the following dependencies installed:
~~~
Chrome browser
WebDriver for Chrome
Testing framework (e.g., TestNG or JUnit)
Additional dependencies for UI testing (e.g., Selenium WebDriver)
~~~
Author
Irina Veselova 

