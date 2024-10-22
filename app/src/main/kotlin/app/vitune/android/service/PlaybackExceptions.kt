@file:OptIn(UnstableApi::class)

package app.vitune.android.service

import androidx.annotation.OptIn
import androidx.media3.common.PlaybackException
import androidx.media3.common.util.UnstableApi

class PlayableFormatNotFoundException : PlaybackException(
    /* message = */ "Playable format not found",
    /* cause = */ null,
    /* errorCode = */ ERROR_CODE_IO_FILE_NOT_FOUND
)

class UnplayableException : PlaybackException(
    /* message = */ "Unplayable",
    /* cause = */ null,
    /* errorCode = */ ERROR_CODE_IO_UNSPECIFIED
)

class LoginRequiredException : PlaybackException(
    /* message = */ "Login required",
    /* cause = */ null,
    /* errorCode = */ ERROR_CODE_AUTHENTICATION_EXPIRED
)

class VideoIdMismatchException : PlaybackException(
    /* message = */ "Requested video ID doesn't match returned video ID",
    /* cause = */ null,
    /* errorCode = */ ERROR_CODE_IO_UNSPECIFIED
)
