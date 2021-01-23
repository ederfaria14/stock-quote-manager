package com.ederfaria.stockquotemanager.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class StockManagerApiInterface {

    private static StockManagerApiInterface.IStockManagerApiInterface apiInterface;

    public static StockManagerApiInterface.IStockManagerApiInterface getApiClient() {
        if (apiInterface == null) {
            Retrofit client = Api.getClientApi();
            apiInterface = client.create(StockManagerApiInterface.IStockManagerApiInterface.class);
        }
        return apiInterface;
    }

    public interface IStockManagerApiInterface {

        @GET("/stock")
        public Call<ResponseBody> readStock();

        @POST("/notification")
        public Call<ResponseBody> notification(@Body Identification identification);
    }

}
