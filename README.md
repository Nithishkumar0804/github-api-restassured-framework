# GitHub API Automation Framework (RestAssured + Cucumber)

This framework automates **GitHub REST APIs** using **RestAssured, Cucumber, and Java**.  
It covers end-to-end testing for **Repositories, Issues, User profile, Starring, and Rate Limiting APIs** with a **data-driven and BDD approach**.

---

## Features Automated
- **Repositories**: Create, Get, Update, Delete  
- **Issues**: Create, Get, Update  
- **User**: Get profile & repositories  
- **Misc**: Star/Unstar repository, Rate limiting  

---

## Project Highlights
- **RestAssured** for API automation  
- **Cucumber BDD** with step definitions  
- **POJO classes** for request payloads  
- **Excel integration** for dynamic test data (`GitHubAPIData.xlsx`)  
- **Config-driven** via `Global.properties`  
- **Cucumber HTML Reports** generated at `target/Reports/`  

---

## Setup & Execution

1. **Clone the repository**
     ```bash
     git clone https://github.com/Nithishkumar080417/github-api-restassured-framework.git

2.**Configure Global.properties**

    userId = <your-github-username>
    token  = <your-personal-access-token>
    baseUrl = https://api.github.com
    
  Personal Access Token must have repo and user scopes.

3.**Run Tests**
    mvn clean verify

4.**View Reports**
    target/Reports/cucumber-html-report.html
