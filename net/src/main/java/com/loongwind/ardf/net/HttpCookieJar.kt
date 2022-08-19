package com.loongwind.ardf.net

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

class HttpCookieJar : CookieJar {

    private var cookieCache: MutableMap<String, Cookie> = HashMap()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        for (cookie in cookies) {
            cookieCache[cookie.name] = cookie
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = ArrayList<Cookie>()
        for (cookie in cookieCache.values) {
            if (cookie.matches(url)) {
                cookies.add(cookie)
            }
        }
        return cookies
    }
}
