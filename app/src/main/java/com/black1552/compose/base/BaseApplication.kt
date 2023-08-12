package com.black1552.compose.base

import android.app.Application
import com.black1552.compose.http.ApiManager
import com.black1552.compose.update.UpdateStart
import com.black1552.compose.update.Updateinit

abstract class BaseApplication : Application() {
    /**
     * 域名
     * @var base string
     */
    abstract var base: String

    /**
     * 基础请求地址 传入信息
     * @var baseUrl string
     */
    abstract var baseUrl: String

    /**
     * Agent 传入信息
     * @var info string
     *
     * "${DeviceUtils.getMacAddress()}_${SPStaticUtils.getInt(STAFF_ID,0)}_${DeviceUtils.getManufacturer()}_${DeviceUtils.getModel()}"
     */
    abstract var info: String
    override fun onCreate() {
        super.onCreate()
        Updateinit.url = "http://autoupdate.magicany.cc/upload/checkVersion"
        Updateinit.context = this
        Updateinit.initXAOP()
        Updateinit.initUpdate()
        Updateinit.initOKHttpUtils()
        ApiManager.baseUrl = baseUrl
        ApiManager.base = base
        ApiManager.info = info
        UpdateStart.start(this)
        iniDate()
    }

    abstract fun iniDate()
}