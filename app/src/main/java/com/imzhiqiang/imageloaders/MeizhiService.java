package com.imzhiqiang.imageloaders;

import com.imzhiqiang.imageloaders.entity.MeizhiEntity;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Zech on 2016/1/1.
 */
public interface MeizhiService {
    @GET("data/福利/{num}/{page}")
    Observable<MeizhiEntity> getRxMeizhi(@Path("num") int num, @Path("page") int page);

    @GET("data/福利/{num}/{page}")
    Call<MeizhiEntity> getMeizhi(@Path("num") int num, @Path("page") int page);
}
