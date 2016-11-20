package com.spacemagical.spacemagical.models

data class Issue(
    val id: Int,
    val issueCategory: IssueCategory,
    val user: User
)
