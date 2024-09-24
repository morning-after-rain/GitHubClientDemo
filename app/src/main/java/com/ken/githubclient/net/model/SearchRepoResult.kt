package com.ken.githubclient.net.model

data class SearchRepoResult(
    val incomplete_results: Boolean,
    val items: MutableList<RepoEntity>,
    val total_count: Int
) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Incomplete Results: $incomplete_results\n")
        stringBuilder.append("Total Count: $total_count\n")
        stringBuilder.append("Items:\n")
        items.forEach { repoEntity ->
            stringBuilder.append(repoEntity.toString()).append("\n")
        }
        return stringBuilder.toString()
    }
}