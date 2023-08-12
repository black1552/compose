package com.black1552.compose.update

import android.content.Context
import com.xuexiang.xupdate.XUpdate

object UpdateStart {
    fun start(context: Context) {
        XUpdate.newBuild(context).updateUrl(Updateinit.url).update()
    }
}