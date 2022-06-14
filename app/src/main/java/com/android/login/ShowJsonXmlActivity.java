package com.android.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Adapter.JsonDataAdapter;
import Adapter.ObjectXmlAdapter;
import model.JsonModel;
import model.ObjectXmlModel;

public class ShowJsonXmlActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ObjectXmlModel> xmlList;
    private ObjectXmlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_json_xml);
        initViews();
        try {
            jsonData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewListObject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        xmlList = new ArrayList<>();
        adapter = new ObjectXmlAdapter(xmlList, this);
    }

    private String inputFile() throws Exception {
        InputStream is = getResources().openRawResource(R.raw.object);

        InputStreamReader isReader = new InputStreamReader(is);
        //Creating a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("sb string : " + sb.toString());
        String result = sb.toString();
        return result;
    }

    private void jsonData() throws Exception {
        String strJson = inputFile();
        JSONArray jsonArray = new JSONArray(strJson);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String type = jsonObject.optString("type").toString();
            int height = Integer.parseInt(jsonObject.optString("height").toString());

            ObjectXmlModel objectModel = new ObjectXmlModel();
            objectModel.setType(type);
            objectModel.setHeight(height);
            System.out.println("type: " + type);
            System.out.println("height : " + height);

            xmlList.add(objectModel);
        }
        adapter.addObject(xmlList);
        System.out.println("xmlList : " + xmlList);
        recyclerView.setAdapter(adapter);
    }
}