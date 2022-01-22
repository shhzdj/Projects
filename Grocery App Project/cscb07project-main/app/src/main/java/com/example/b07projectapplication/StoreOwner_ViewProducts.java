package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StoreOwner_ViewProducts extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userUID;
    private StoreOwner owner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_view_products);
        getSupportActionBar().hide();

        //READ THE CURRENT USER FROM THE DATABASE
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        userUID = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(userUID);
        ValueEventListener listener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateListView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StoreOwner_ViewProducts.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        };
        ref.addValueEventListener(listener);

        updateListView();
    }

    public void sentToNewProduct(View view){
        Intent intent = new Intent(StoreOwner_ViewProducts.this, StoreOwner_AddNewProduct.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void updateListView(){
        //UPDATE THE LISTVIEW

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if ( task.isSuccessful() ) {
                    owner = task.getResult().getValue(StoreOwner.class);
                    TextView tv = findViewById(R.id.textViewProducts);
                    tv.setText("Viewing all products of " + owner.getStoreName());

                    ArrayList<Product> productSet = owner.getProducts();
                    HashMap<String, String> productMap = new HashMap<>();
                    ListView list = (ListView) findViewById(R.id.productList);

                    if ( productSet == null || productSet.isEmpty() ){
                        Toast.makeText(StoreOwner_ViewProducts.this, "No Recorded Products, Please Add New Product!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for (Product product: productSet){
                            //Put all products in a HashMap with name as key, price as value
                            productMap.put( product.getName(), product.getPriceString() );
                        }

                        /**-------------------------------CODE COURTESY OF https://www.youtube.com/watch?v=VYDLTBjdliY------------------------------------------------*/
                        List<HashMap<String, String>> listItems = new ArrayList<>();
                        SimpleAdapter adapter = new SimpleAdapter(StoreOwner_ViewProducts.this, listItems, R.layout.owner_listproducts_resources,
                                new String[]{"First Line", "Second Line"}, new int[]{R.id.listViewText1, R.id.listViewText2});

                        Iterator it = productMap.entrySet().iterator();
                        while ( it.hasNext() ){
                            HashMap<String, String> resultsMap = new HashMap<>();
                            Map.Entry pair = (Map.Entry)it.next();
                            resultsMap.put( "First Line", pair.getKey().toString() );
                            resultsMap.put( "Second Line", pair.getValue().toString() );
                            listItems.add(resultsMap);
                        }
                        list.setAdapter(adapter);
                        /**------------------------------------------------------------------------------------------------------------------------------------------*/

                    }
                }
                else{
                    Toast.makeText(StoreOwner_ViewProducts.this, "Database error!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(StoreOwner_ViewProducts.this, StoreOwnerHomepage.class);
        startActivity(back);
        finish();
    }

}