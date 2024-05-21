package com.course.example.mongodbfirstexample;

import android.os.Bundle;
import android.util.Log;

import com.mongodb.MongoClient;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;

import androidx.appcompat.app.AppCompatActivity;

import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(task);
        t.start();
    }

    Runnable task = new Runnable(){
        public void run(){

            MongoClient mongoClient = new MongoClient("frodo.bentley.edu", 27017);
            MongoDatabase database = mongoClient.getDatabase("yelp");
            MongoCollection<Document> collection = database.getCollection("rest");

            MongoCursor<Document> cursor = collection.find().iterator();

                while (cursor.hasNext()) {
                    Log.i("Cities", (String) (cursor.next().toJson()));
                }
                cursor.close();
        }
    };
}
