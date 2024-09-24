package com.ken.githubclient.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.model.RepoOwner

@Preview
@Composable
fun RepoItemPreview() {
    // 创建模拟的 RepoOwner 对象
    val mockOwner = RepoOwner(
        login = "mockOwner",
        id = 123,
        node_id = "mockNodeIdForOwner",
        avatar_url = "mockAvatarUrl",
        gravatar_id = "mockGravatarId",
        url = "mockOwnerUrl",
        html_url = "mockOwnerHtmlUrl",
        followers_url = "mockFollowersUrl",
        following_url = "mockFollowingUrl",
        gists_url = "mockGistsUrl",
        starred_url = "mockStarredUrl",
        subscriptions_url = "mockSubscriptionsUrl",
        organizations_url = "mockOrganizationsUrl",
        repos_url = "mockReposUrl",
        events_url = "mockEventsUrl",
        received_events_url = "mockReceivedEventsUrl",
        type = "mockType",
        site_admin = false
    )
    // 创建模拟的 RepoEntity 对象
    val mockRepoData = RepoEntity(
        id = 1L,
        node_id = "mockNodeId",
        name = "Sample Repo",
        full_name = "mockFullName",
        private = false,
        owner = mockOwner,
        html_url = "mockHtmlUrl",
        description = "This is a sample repository description.",
        fork = false,
        url = "mockUrl",
        forks_url = "mockForksUrl",
        keys_url = "mockKeysUrl",
        collaborators_url = "mockCollaboratorsUrl",
        teams_url = "mockTeamsUrl",
        hooks_url = "mockHooksUrl",
        issue_events_url = "mockIssueEventsUrl",
        events_url = "mockEventsUrl",
        assignees_url = "mockAssigneesUrl",
        branches_url = "mockBranchesUrl",
        tags_url = "mockTagsUrl",
        blobs_url = "mockBlobsUrl",
        git_tags_url = "mockGitTagsUrl",
        git_refs_url = "mockGitRefsUrl",
        trees_url = "mockTreesUrl",
        statuses_url = "mockStatusesUrl",
        languages_url = "mockLanguagesUrl",
        stargazers_url = "mockStargazersUrl",
        contributors_url = "mockContributorsUrl",
        subscribers_url = "mockSubscribersUrl",
        subscription_url = "mockSubscriptionUrl",
        commits_url = "mockCommitsUrl",
        git_commits_url = "mockGitCommitsUrl",
        comments_url = "mockCommentsUrl",
        issue_comment_url = "mockIssueCommentUrl",
        contents_url = "mockContentsUrl",
        compare_url = "mockCompareUrl",
        merges_url = "mockMergesUrl",
        archive_url = "mockArchiveUrl",
        downloads_url = "mockDownloadsUrl",
        issues_url = "mockIssuesUrl",
        pulls_url = "mockPullsUrl",
        milestones_url = "mockMilestonesUrl",
        notifications_url = "mockNotificationsUrl",
        labels_url = "mockLabelsUrl",
        releases_url = "mockReleasesUrl",
        deployments_url = "mockDeploymentsUrl",
        created_at = "mockCreatedAt",
        updated_at = "15小时前",
        pushed_at = "mockPushedAt",
        git_url = "mockGitUrl",
        ssh_url = "mockSshUrl",
        clone_url = "mockCloneUrl",
        svn_url = "mockSvnUrl",
        homepage = "mockHomepage",
        size = 100,
        stargazers_count = 500,
        watchers_count = 300,
        language = "Kotlin",
        has_issues = true,
        has_projects = false,
        has_downloads = true,
        has_wiki = false,
        has_pages = false,
        forks_count = 20,
        mirror_url = "mockMirrorUrl",
        open_issues_count = 10,
        license = null,
        forks = 20,
        open_issues = 10,
        watchers = 300,
        defaultBranch = "main"
    )
    // 创建一个模拟的导航函数
    val navigateToDetail: (Long) -> Unit = {}
    RepoItem(repoData = mockRepoData, navigateToDetail = navigateToDetail)
}