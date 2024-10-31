package com.hpl.my_music.component.api;

import com.hpl.my_music.component.sheet.model.Sheet;
import com.hpl.my_music.component.sheet.model.SheetWrapper;
import com.hpl.my_music.model.response.DetailResponse;
import com.hpl.my_music.model.response.ListResponse;

import org.w3c.dom.Comment;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 默认远程数据源
 */
public interface DefaultService {
    /**
     * 歌单列表
     *
     * @return
     */
    @GET("v1/sheets")
    Observable<ListResponse<Sheet>> sheets(@Query(value = "category") String category, @Query(value = "size") int size);

    /**
     * 歌单详情
     *
     * @param testHeader 可以通过@Header这种方式针对单个请求传递请求头，这里就是测试，无实际作用
     * @param id
     * @return
     */
    @GET("v1/sheets/{id}")
    Observable<DetailResponse<Sheet>> sheetDetail(@Header("testHeader") String testHeader, @Path("id") String id);

    /**
     * 评论列表
     *
     * @return
     */
    @GET("v1/comments")
    Observable<ListResponse<Comment>> comments();
}

