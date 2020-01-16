package com.swc.onestop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import com.swc.onestop.Activities.Freshers.Details.DetailsData;
import com.swc.onestop.Activities.Freshers.GarlandApp;
import com.swc.onestop.Activities.Freshers.inner.InnerData;
import com.swc.onestop.Activities.Freshers.inner.InnerItem;
import com.swc.onestop.Activities.Freshers.outer.OuterAdapter;
import com.swc.onestop.Activities.Freshers.profile.ProfileActivity;

import com.swc.onestop.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.bloco.faker.Faker;


public class FreshersActivity extends AppCompatActivity implements GarlandApp.FakerReadyListener {
    final List<List<InnerData>> outerData_Firebase = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freshers);
        ((GarlandApp)getApplication()).addListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(FreshersActivity.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onFakerReady(final Faker faker) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference freshRef = rootRef.collection("Freshers");
        Query freshersQuery=freshRef.orderBy("time", Query.Direction.ASCENDING);
       // final Query freshRefQuery = freshRef.orderBy("time", Query.Direction.DESCENDING);
        freshersQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (final DocumentSnapshot outerDocument : task.getResult()) {
                        final List<InnerData> innerData_Firebase = new ArrayList<>();

                        freshRef.document(outerDocument.getId()).collection("SubPosts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (DocumentSnapshot innerDocument : task.getResult()) {
                                        Map singleData = innerDocument.getData();
                                        int order=10;
                                        try{
                                            order=Integer.valueOf(singleData.get("order").toString());
                                        }
                                        catch(Exception e){};
                                        innerData_Firebase.add(new InnerData(singleData.get("title").toString(),
                                                singleData.get("name").toString(),
                                                singleData.get("address").toString(),
                                                singleData.get("imageUrl").toString(),
                                                innerDocument.getId(),
                                                outerDocument.getId(),
                                                order));

                                        Log.d("Inner_Firebase", singleData.get("title").toString());

                                    }
                                } else {
                                    Log.d("InnerData", "error");
                                }
                                Collections.sort(innerData_Firebase,new Comparator<InnerData>()
                                {
                                    @Override
                                    public int compare(InnerData o1, InnerData o2) {
                                        Log.i("Order-", o1.title+" "+o1.order+" "+o2.title+" "+o2.order);
                                        return o1.order-(o2.order);
                                    }
                                });
                                outerData_Firebase.add(innerData_Firebase);
                                Log.d("Outter_Firebase", outerData_Firebase.toString());

                                initRecyclerView(outerData_Firebase);
                            }
                        });
                    }
                }
            }
        });
    }

    private void initRecyclerView(List<List<InnerData>> data) {
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        final TailRecyclerView rv=findViewById(R.id.recycler_view);
        ((TailLayoutManager)rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setOnFlingListener(null);
        rv.setAdapter(new OuterAdapter(data));

        new TailSnapHelper().attachToRecyclerView(rv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // startActivity(new Intent(ProfileActivity.this, FreshersActivity.class));
        finish();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnInnerItemClick(final InnerItem item) {
        final InnerData itemData = item.getItemData();
        if (itemData == null) {
            return;
        }
       final ProgressDialog mProgressDialog=new ProgressDialog(FreshersActivity.this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.show();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference freshRef = rootRef.collection("Freshers");
        String outerDocumentID=item.getItemData().outerID;
        String InnerDocumentID=item.getItemData().innerID;
        final CollectionReference subPosts=freshRef.document(outerDocumentID).collection("SubPosts");
        {
            CollectionReference details = subPosts.document(InnerDocumentID).collection("details");
            details.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<DetailsData>  mListData = new ArrayList<>();

                        for (DocumentSnapshot document : task.getResult()) {
                            Map singleData = document.getData();
                            String freshersPostLink = "";
                            try {
                                freshersPostLink = singleData.get("link").toString();
                            } catch (Exception e) {
                            }
                            mListData.add(new DetailsData(singleData.get("title").toString(),
                                    singleData.get("name").toString().replace('_', '\n'), freshersPostLink));
                        }

                            mProgressDialog.dismiss();
                            ProfileActivity.start(FreshersActivity.this,
                                    item.getItemData().avatarUrl,
                                    item.getItemData().title,
                                    item.getItemData().title,
                                    "",
                                    findViewById(R.id.avatar),
                                    findViewById(R.id.card),
                                    findViewById(R.id.iv_background),
                                    findViewById(R.id.recycler_view),
                                    mListData);
                    }
                }
            });
        }
    }

}


