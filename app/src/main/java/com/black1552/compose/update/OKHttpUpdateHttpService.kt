package com.black1552.compose.update

import com.xuexiang.xupdate.proxy.IUpdateHttpService
import com.xuexiang.xupdate.proxy.IUpdateHttpService.DownloadCallback
import com.xuexiang.xupdate.utils.UpdateUtils
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import com.zhy.http.okhttp.callback.StringCallback
import com.zhy.http.okhttp.request.RequestCall
import io.reactivex.annotations.NonNull
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import java.io.File
import java.util.TreeMap


class OKHttpUpdateHttpService @JvmOverloads constructor(private val mIsPostJson: Boolean = false) :
    IUpdateHttpService {
    override fun asyncGet(
        @NonNull url: String,
        @NonNull params: Map<String, Any>,
        @NonNull callBack: IUpdateHttpService.Callback
    ) {
        OkHttpUtils.get()
            .url(url)
            .params(transform(params))
            .build()
            .execute(object : StringCallback() {
                override fun onError(call: Call?, e: Exception?, id: Int) {
                    callBack.onError(e)
                }

                override fun onResponse(response: String, id: Int) {
                    callBack.onSuccess(response)
                }
            })
    }

    override fun asyncPost(
        @NonNull url: String,
        @NonNull params: Map<String, Any>,
        @NonNull callBack: IUpdateHttpService.Callback
    ) {
        //这里默认post的是Form格式，使用json格式的请修改 post -> postString
        val requestCall: RequestCall = if (mIsPostJson) {
            OkHttpUtils.postString()
                .url(url)
                .content(UpdateUtils.toJson(params))
                .mediaType("application/json; charset=utf-8".toMediaTypeOrNull())
                .build()
        } else {
            OkHttpUtils.post()
                .url(url)
                .params(transform(params))
                .build()
        }
        requestCall
            .execute(object : StringCallback() {
                override fun onError(call: Call?, e: Exception?, id: Int) {
                    callBack.onError(e)
                }

                override fun onResponse(response: String, id: Int) {
                    callBack.onSuccess(response)
                }
            })
    }

    override fun download(
        @NonNull url: String,
        @NonNull path: String,
        @NonNull fileName: String,
        @NonNull callback: DownloadCallback
    ) {
        OkHttpUtils.get()
            .url(url)
            .tag(url)
            .build()
            .execute(object : FileCallBack(path, fileName) {
                override fun inProgress(progress: Float, total: Long, id: Int) {
                    callback.onProgress(progress, total)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    callback.onError(e)
                }

                override fun onResponse(response: File?, id: Int) {
                    callback.onSuccess(response)
                }

                override fun onBefore(request: Request?, id: Int) {
                    super.onBefore(request, id)
                    callback.onStart()
                }
            })
    }

    override fun cancelDownload(@NonNull url: String) {
        OkHttpUtils.getInstance().cancelTag(url)
    }

    private fun transform(params: Map<String, Any>): Map<String, String> {
        val map: MutableMap<String, String> = TreeMap()
        for ((key, value) in params) {
            map[key] = value.toString()
        }
        return map
    }
}