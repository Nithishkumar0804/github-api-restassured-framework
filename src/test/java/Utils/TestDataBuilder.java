package Utils;

import pojoClasses.CreateIssuePojo;
import pojoClasses.CreateRepoPojo;
import pojoClasses.UpdateIssuePojo;
import pojoClasses.UpdateRepoPojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataBuilder {
    static String repoName = "AutomationTestingRepo";

    public static CreateRepoPojo createRepoPayload() throws IOException {
        CreateRepoPojo createRepoPojo = new CreateRepoPojo();
        HashMap<String, String> data = DataReader.getCreateRepoData();
        createRepoPojo.setName(data.get("name"));
        createRepoPojo.setDescription(data.get("description"));
        createRepoPojo.setHomepage(data.get("homepage"));
        Boolean isPrivate = "TRUE".equalsIgnoreCase(data.get("private").trim());
        createRepoPojo.setPrivate(isPrivate);
/*        createRepoPojo.setName(repoName);
        createRepoPojo.setDescription("This is a repo created by API Automation");
        createRepoPojo.setHomepage("https://github.com");
        createRepoPojo.setPrivate(false);*/
        return createRepoPojo;
    }

    public static CreateRepoPojo createRepoForIssue() {
        CreateRepoPojo forIssue = new CreateRepoPojo();
        forIssue.setName("RepoForIssue");
        forIssue.setDescription("This is a repo created by API Automation");
        forIssue.setHomepage("https://github.com");
        forIssue.setPrivate(false);
        return forIssue;
    }

    public static String getRepoName() {
        return repoName;
    }

    public static UpdateRepoPojo getUpdateRepo() {
        UpdateRepoPojo pojo = new UpdateRepoPojo();
        pojo.setName(repoName);
        pojo.setDescription("This Repo has been changed to private");
        pojo.setHomepage("https://github.com");
        pojo.setPrivate(true);
        return pojo;
    }

    public static CreateIssuePojo getCreateIssue() {
        CreateIssuePojo createIssuePojo = new CreateIssuePojo();
        List<String> labelsList = new ArrayList<>();
        labelsList.add("bug");
        createIssuePojo.setTitle("Found a bug");
        createIssuePojo.setBody("I'm having a problem with this.");
        createIssuePojo.setLabels(labelsList);
        return createIssuePojo;
    }

    public static UpdateIssuePojo getUpdateIssue() {
        UpdateIssuePojo updateIssuePojo = new UpdateIssuePojo();
        updateIssuePojo.setTitle("Bug has been Resolved");
        updateIssuePojo.setBody("The bug has been closed because its been resolved!");
        updateIssuePojo.setState("close");
        List<String> labelsList = new ArrayList<>();
        labelsList.add("bug");
        updateIssuePojo.setLabels(labelsList);
        return updateIssuePojo;
    }
}
