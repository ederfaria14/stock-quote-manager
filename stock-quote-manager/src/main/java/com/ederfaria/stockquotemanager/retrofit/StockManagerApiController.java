package com.ederfaria.stockquotemanager.retrofit;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.ResponseBody;
import retrofit2.Call;

@Service
public class StockManagerApiController {

    private final StockManagerApiInterface.IStockManagerApiInterface serviceApi;

    public StockManagerApiController() {
        serviceApi = StockManagerApiInterface.getApiClient();
    }

    public boolean containStock(String id) {
        boolean flag = false;

        Call<ResponseBody> call = serviceApi.readStock();
        try {
            ResponseBody response = call.execute().body();
            String json = response.string();

            Type listType = new TypeToken<ArrayList<StockManager>>() {
            }.getType();
            List<StockManager> list = new Gson().fromJson(json, listType);

            flag = list.stream().anyMatch(h -> h.getId().equals(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void registerOnStockManager() {
        Call<ResponseBody> call = serviceApi.notification(new Identification("localhost", "8081"));
        try {
            ResponseBody response = call.execute().body();
            System.out.println("Register on stock-manager");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
