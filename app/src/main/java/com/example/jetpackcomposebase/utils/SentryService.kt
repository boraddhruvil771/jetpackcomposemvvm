package com.example.jetpackcomposebase.utils

import io.sentry.Sentry
import io.sentry.SentryEvent
import io.sentry.SentryLevel
import io.sentry.SentryOptions
import io.sentry.protocol.User

/**
 * This is Sentry Service class for Logs
 */
object SentryService {

    fun init(dsn: String) {
        Sentry.init { options: SentryOptions ->
            options.dsn = dsn
            options.isDebug = true // Set to false in production
            options.tracesSampleRate = 1.0
        }
    }

    // Set config scope in Sentry to help search event/exception by email and id
    fun configScope(sentryUserId: String?, sentryUserEmail: String?) {
        Sentry.configureScope { scope ->
            scope.user = User().apply {
                id = sentryUserId
                email = sentryUserEmail
            }
        }
    }

    // Capture event in Sentry
    fun captureEvent(event: String, tagKey: String = "base_structure-tag", tagValue: String = "base_structure-event") {
        Sentry.captureEvent(
            SentryEvent().apply {
                message = io.sentry.protocol.Message().apply { this.message = event }
                setTag(tagKey, tagValue)
                level = SentryLevel.INFO
            }
        )
    }

    // Capture exception in Sentry
    fun captureException(exception: Throwable, tagKey: String = "base_structure-tag", tagValue: String = "base_structure-exception") {
        Sentry.captureException(exception) { scope ->
            scope.setTag(tagKey, tagValue)
            scope.level = SentryLevel.WARNING
        }
    }
}
