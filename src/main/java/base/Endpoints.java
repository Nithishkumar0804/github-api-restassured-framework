package base;

public enum Endpoints {

    getUserProfile("/user"),
    getUserRepos("/user/repos"),
    createRepo("/user/repos"),
    updateRepo("/repos/{owner}/{repo}"),
    deleteRepo("/repos/{owner}/{repo}"),
    createIssue("/repos/{owner}/{repo}/issues"),
    getIssue("/repos/{owner}/{repo}/issues/{issue_number}"),
    updateIssue("/repos/{owner}/{repo}/issues/{issue_number}"),
    lockIssue("/repos/{owner}/{repo}/issues/{ISSUE_NUMBER}/lock"),
    unlockIssue("/repos/{owner}/{repo}/issues/{ISSUE_NUMBER}/lock"),
    getRateLimiting("/rate_limit"),
    starring("/user/starred/{owner}/{repo}"),
    unStarring("/user/starred/{owner}/{repo}");

    private final String resources;

    Endpoints(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }
}
