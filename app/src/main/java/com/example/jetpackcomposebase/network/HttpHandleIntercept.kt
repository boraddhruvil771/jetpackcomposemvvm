package com.example.jetpackcomposebase.network

import com.example.jetpackcomposebase.BuildConfig
import com.example.jetpackcomposebase.MyApp
import com.example.jetpackcomposebase.utils.CommonUtils
import com.example.jetpackcomposebase.utils.Constants
import com.example.jetpackcomposebase.utils.DebugLog
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HttpHandleIntercept : Interceptor {

    /**
     * This method is for Handle intercept.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = when (CommonUtils.IS_LOGGED_IN_OTP) {

            true -> {
                chain.request().newBuilder().headers(getJsonHeader())
                    .addHeader(
                        Constants.AUTHORIZATION,
                        "${Constants.BEARER} ${
                            CommonUtils.getData(
                                MyApp.applicationContext(),
                                CommonUtils.AUTH_TOKEN_OTP
                            )
                        }"
                    ).build()
            }

            else -> {
                chain.request().newBuilder().headers(getJsonHeader())
                    .addHeader(
                        Constants.AUTHORIZATION,
                        "${Constants.BEARER} ${
                            CommonUtils.getData(MyApp.applicationContext(), CommonUtils.AUTH_TOKEN)
                        }"
                    ).build()
            }


        }

        val response: Response? = try {
            chain.proceed(request)
        } catch (e: Exception) {
            null
        }

        if (response == null) {
            return generateCustomResponse(
                401, "SOMETHING_WENT_WRONG",
                chain.request()
            )!!
        } else {

            if (response.code == 401) {
                return generateCustomResponse(
                    response.code, response.message,
                    chain.request()
                )!!

            } else if (response.code == 500) {
                return generateCustomResponse(
                    response.code, response.message,
                    chain.request()
                )!!
            }
        }
        return response
    }

    /**
     * This method is for create Json Header in every API.
     */
    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add(Constants.KEY_CONTENT_TYPE, Constants.APPLICATION_JSON)
        builder.add(Constants.KEY_ACCEPT_LANGUAGE, "en")
        builder.add(Constants.ACCEPT_CAPITAL, Constants.APPLICATION_JSON)
        builder.add(Constants.IS_MOBILE, Constants.IS_MOBILE_1)
        builder.add(Constants.DEVICE_TYPE, Constants.ANDROID)
        builder.add(Constants.LANGUAGE_CODE, Constants.EN)
        builder.add(
            Constants.VERSION,
            BuildConfig.VERSION_NAME
        )
        DebugLog.d("requestHeader" + Gson().toJson(builder))
        return builder.build()
    }

    /**
     * generate custom response for exception.
     */
    private fun generateCustomResponse(code: Int, message: String, request: Request): Response? {

        try {
            val body = ResponseBody.create(
                Constants.APPLICATION_JSON.toMediaTypeOrNull(),
                getJSONObjectForException(message, code).toString()
            )
            return Response.Builder()
                .code(code)
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(body)
                .message(message)
                .build()
        } catch (ex: Exception) {
            DebugLog.print(ex)
            return null
        }
    }


    /**
     * generate JSON object for error case.
     */
    private fun getJSONObjectForException(message: String, code: Int): JSONObject {

        try {
            val jsonMainObject = JSONObject()

            val `object` = JSONObject()
            `object`.put("status", false)
            `object`.put("message", message)
            `object`.put("message_code", code)
            `object`.put("status_code", code)

            jsonMainObject.put("meta", `object`)

            val obj = JSONObject()
            obj.put("error", JSONArray().put(message))

            jsonMainObject.put("errors", obj)

            return jsonMainObject
        } catch (e: JSONException) {
            DebugLog.print(e)

            return JSONObject()
        }
    }
}
