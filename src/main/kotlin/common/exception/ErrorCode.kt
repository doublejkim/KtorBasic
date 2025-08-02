package com.example.common.exception

enum class ErrorCode (
    override val code: Int,
    override var message: String,
) : CodeInterface {
    FAILED_TO_FIND_REQUEST_SOURCE(-1, "failed to find request source"),
    FAILED_TO_READ_BODY_REQUEST(-1, "failed to read body"),
}