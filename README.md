### Summary

A Selenium automation written in Java, with dependencies managed by Maven.

#### Automation Steps
1. Navigates to google.com
2. Searches for futureadvisor.com
3. Verifies landing on the google result page
4. Clicks on the first result link, ignoring ads
5. Verifies landing on the futureadvisor.com homepage
6. Checks for dead links on the page (this step will take about a minute to finish)
7. Hovers over 'What we do' on the nav and then navigates to the retirement page
8. Verifies landing on the retirement page
9. Scrolls to the bottom of the page
10. Clicks on the 'Log In' link
11. Attempts to log in and asserts the expected error message

Source code is located in src/com/pack/FutureAdvisorAutomation.java

To run from the project folder: `open main,.jar`
