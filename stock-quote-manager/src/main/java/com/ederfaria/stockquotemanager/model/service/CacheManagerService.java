package com.ederfaria.stockquotemanager.model.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ederfaria.stockquotemanager.retrofit.StockManager;
import com.ederfaria.stockquotemanager.retrofit.StockManagerApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;

@Service
public class CacheManagerService {

    private final StockManagerApiInterface.IStockManagerApiInterface serviceApi;

    private List<StockManager> list;

    public CacheManagerService() {
        list = new ArrayList<StockManager>();
        serviceApi = StockManagerApiInterface.getApiClient();
    }

    public List<StockManager> getList() {
        return list;
    }

    public void setList(List<StockManager> list) {
        this.list = list;
    }

    public boolean containStock(String id) {
        boolean contem = false;
        if (list.isEmpty()) {
            Call<ResponseBody> call = serviceApi.readStock();
            try {
                ResponseBody response = call.execute().body();
                String json = response.string();

                Type listType = new TypeToken<ArrayList<StockManager>>() {
                }.getType();
                List<StockManager> listRest = new Gson().fromJson(json, listType);
                this.list.addAll(listRest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        contem = this.list.stream().anyMatch(h -> h.getId().equals(id));
        return contem;
    }

    public void clearMap() {
        System.out.println("Clear cache");
        list.clear();
    }
}
