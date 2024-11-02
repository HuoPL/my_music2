package com.hpl.my_music.util;

import android.text.TextUtils;

import com.hpl.my_music.R;
import com.hpl.my_music.model.response.BaseResponse;

import org.apache.commons.lang3.StringUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 * 错误处理辅助方法
 */
public class ExceptionHandlerUtil {
    /**
     * 网络请求错误处理
     *
     * @param data
     * @param error
     */
    public static <T> void handlerRequest(T data, Throwable error) {
        if (error != null) {
            //先处理有异常的请求
            handleException(error);
        } else {
            if (data instanceof Response) {
                //retrofit里面的对象

                //获取响应对象
                Response response = (Response) data;

                //获取响应码
                int code = response.code();

                //判断响应码
                if (code >= 200 && code <= 299) {
                    //网络请求正常
                } else {
                    handleHttpError(code);
                }
            } else if (data instanceof BaseResponse) {
                //判断具体的业务请求是否成功
                BaseResponse response = (BaseResponse) data;

                if (TextUtils.isEmpty(response.getMessage())) {
                    //没有错误提示信息
                    TipUtil.showError(R.string.error_unknown);
                } else {
                    TipUtil.showError(response.getMessage());
                }
            }
        }
    }

    /**
     * 处理异常
     */
    public static void handleException(Throwable error) {
        if (error.getCause() != null) {
            //如果发生这个异常有原因，就先判断原因
            error = error.getCause();
        }

        //判断错误类型
        if (error instanceof UnknownHostException) {
            TipUtil.showError(R.string.error_network_unknown_host);
        } else if (error instanceof ConnectException) {
            TipUtil.showError(R.string.network_error);
        } else if (error instanceof SocketTimeoutException) {
            TipUtil.showError(R.string.error_network_timeout);
        } else if (error instanceof HttpException) {
            HttpException exception = (HttpException) error;

            //获取响应码
            int code = exception.code();

            handleHttpError(code);
        } else if (error instanceof IllegalArgumentException) {
            //本地参数错误
            TipUtil.showError(R.string.error_parameter);
        } else {
            String message = error.getLocalizedMessage();
            if (StringUtils.isNotBlank(message)) {
                message = AppContext.getInstance().getString(R.string.error_unknown_format, message);
            } else {
                message = AppContext.getInstance().getString(R.string.error_unknown);
            }
            TipUtil.showError(message);
        }
    }

    /**
     * 处理Http错误
     *
     * @param code
     */
    private static void handleHttpError(int code) {
        if (code == 401) {
            TipUtil.showError(R.string.error_network_not_auth);

            AppContext.getInstance().logout();
        } else if (code == 403) {
            TipUtil.showError(R.string.error_network_not_permission);
        } else if (code == 404) {
            TipUtil.showError(R.string.error_network_not_found);
        } else if (code >= 500) {
            TipUtil.showError(R.string.error_network_server);
        } else {
            TipUtil.showError(R.string.error_unknown);
        }
    }
}
