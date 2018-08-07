package com.zero.yoga.extentions

import android.util.Log
import com.orhanobut.logger.Logger

/**
 * Created by zero on 2018/8/7.
 */
interface YogaLogger {
    /**
     * The logger tag used in extension functions for the [YogaLogger].
     * Note that the tag length should not be more than 23 symbols.
     */
    val loggerTag: String
        get() = getTag(javaClass)
}

fun YogaLogger(clazz: Class<*>): YogaLogger = object : YogaLogger {
    override val loggerTag = getTag(clazz)
}

fun YogaLogger(tag: String): YogaLogger = object : YogaLogger {
    init {
        assert(tag.length <= 23) { "The maximum tag length is 23, got $tag" }
    }
    override val loggerTag = tag
}

inline fun <reified T: Any> YogaLogger(): YogaLogger = YogaLogger(T::class.java)

/**
 * Send a log message with the [Logger.VERBOSE] severity.
 * Note that the log message will not be written if the current log level is above [Logger.VERBOSE].
 * The default log level is [Logger.INFO].
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.v].
 */
fun YogaLogger.verbose(message: Any?, thr: Throwable? = null) {
    log(this, message, thr, Log.VERBOSE,
            { tag, msg -> Log.v(tag, msg) },
            { tag, msg, thr -> Log.v(tag, msg, thr) })
}

/**
 * Send a log message with the [Logger.DEBUG] severity.
 * Note that the log message will not be written if the current log level is above [Logger.DEBUG].
 * The default log level is [Logger.INFO].
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.d].
 */
fun YogaLogger.debug(message: Any?, thr: Throwable? = null) {
    log(this, message, thr, Log.DEBUG,
            { tag, msg -> Log.d(tag, msg) },
            { tag, msg, thr -> Log.d(tag, msg, thr) })
}

/**
 * Send a log message with the [Logger.INFO] severity.
 * Note that the log message will not be written if the current log level is above [Logger.INFO]
 *   (it is the default level).
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.i].
 */
fun YogaLogger.info(message: Any?, thr: Throwable? = null) {
    log(this, message, thr, Log.INFO,
            { tag, msg -> Log.i(tag, msg) },
            { tag, msg, thr -> Log.i(tag, msg, thr) })
}

/**
 * Send a log message with the [Logger.WARN] severity.
 * Note that the log message will not be written if the current log level is above [Logger.WARN].
 * The default log level is [Logger.INFO].
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.w].
 */
fun YogaLogger.warn(message: Any?, thr: Throwable? = null) {
    log(this, message, thr, Log.WARN,
            { tag, msg -> Log.w(tag, msg) },
            { tag, msg, thr -> Log.w(tag, msg, thr) })
}

/**
 * Send a log message with the [Logger.ERROR] severity.
 * Note that the log message will not be written if the current log level is above [Logger.ERROR].
 * The default log level is [Logger.INFO].
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.e].
 */
fun YogaLogger.error(message: Any?, thr: Throwable? = null) {
    log(this, message, thr, Log.ERROR,
            { tag, msg -> Log.e(tag, msg) },
            { tag, msg, thr -> Log.e(tag, msg, thr) })
}

/**
 * Send a log message with the "What a Terrible Failure" severity.
 * Report an exception that should never happen.
 *
 * @param message the message text to log. `null` value will be represent as "null", for any other value
 *   the [Any.toString] will be invoked.
 * @param thr an exception to log (optional).
 *
 * @see [Logger.wtf].
 */
fun YogaLogger.wtf(message: Any?, thr: Throwable? = null) {
    if (thr != null) {
        Logger.wtf(loggerTag, message?.toString() ?: "null", thr)
    } else {
        Logger.wtf(loggerTag, message?.toString() ?: "null")
    }
}

/**
 * Send a log message with the [Logger.VERBOSE] severity.
 * Note that the log message will not be written if the current log level is above [Logger.VERBOSE].
 * The default log level is [Logger.INFO].
 *
 * @param message the function that returns message text to log.
 *   `null` value will be represent as "null", for any other value the [Any.toString] will be invoked.
 *
 * @see [Logger.v].
 */
inline fun YogaLogger.verbose(message: () -> Any?) {
    val tag = loggerTag
    if (Log.isLoggable(tag, Log.VERBOSE)) {
        Logger.v(tag, message()?.toString() ?: "null")
    }
}

/**
 * Send a log message with the [Logger.DEBUG] severity.
 * Note that the log message will not be written if the current log level is above [Logger.DEBUG].
 * The default log level is [Logger.INFO].
 *
 * @param message the function that returns message text to log.
 *   `null` value will be represent as "null", for any other value the [Any.toString] will be invoked.
 *
 * @see [Logger.d].
 */
inline fun YogaLogger.debug(message: () -> Any?) {
    val tag = loggerTag
    if (Log.isLoggable(tag, Log.DEBUG)) {
        Logger.d(tag, message()?.toString() ?: "null")
    }
}

/**
 * Send a log message with the [Logger.INFO] severity.
 * Note that the log message will not be written if the current log level is above [Logger.INFO].
 * The default log level is [Logger.INFO].
 *
 * @param message the function that returns message text to log.
 *   `null` value will be represent as "null", for any other value the [Any.toString] will be invoked.
 *
 * @see [Logger.i].
 */
inline fun YogaLogger.info(message: () -> Any?) {
    val tag = loggerTag
    if (Log.isLoggable(tag, Log.INFO)) {
        Logger.i(tag, message()?.toString() ?: "null")
    }
}

/**
 * Send a log message with the [Logger.WARN] severity.
 * Note that the log message will not be written if the current log level is above [Logger.WARN].
 * The default log level is [Logger.INFO].
 *
 * @param message the function that returns message text to log.
 *   `null` value will be represent as "null", for any other value the [Any.toString] will be invoked.
 *
 * @see [Logger.w].
 */
inline fun YogaLogger.warn(message: () -> Any?) {
    val tag = loggerTag
    if (Log.isLoggable(tag, Log.WARN)) {
        Logger.w(tag, message()?.toString() ?: "null")
    }
}

/**
 * Send a log message with the [Logger.ERROR] severity.
 * Note that the log message will not be written if the current log level is above [Logger.ERROR].
 * The default log level is [Logger.INFO].
 *
 * @param message the function that returns message text to log.
 *   `null` value will be represent as "null", for any other value the [Any.toString] will be invoked.
 *
 * @see [Logger.e].
 */
inline fun YogaLogger.error(message: () -> Any?) {
    val tag = loggerTag
    if (Log.isLoggable(tag, Log.ERROR)) {
        Logger.e(tag, message()?.toString() ?: "null")
    }
}

/**
 * Return the stack trace [String] of a throwable.
 */
inline fun Throwable.getStackTraceString(): String = Log.getStackTraceString(this)

private inline fun log(
        logger: YogaLogger,
        message: Any?,
        thr: Throwable?,
        level: Int,
        f: (String, String) -> Unit,
        fThrowable: (String, String, Throwable) -> Unit) {
    val tag = logger.loggerTag
    if (Log.isLoggable(tag, level)) {
        if (thr != null) {
            fThrowable(tag, message?.toString() ?: "null", thr)
        } else {
            f(tag, message?.toString() ?: "null")
        }
    }
}

private fun getTag(clazz: Class<*>): String {
    val tag = clazz.simpleName
    return if (tag.length <= 23) {
        tag
    } else {
        tag.substring(0, 23)
    }
}