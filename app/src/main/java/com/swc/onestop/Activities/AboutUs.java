package com.swc.onestop.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.swc.onestop.R;
import com.swc.onestop.Url;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
    String feedbackurl ="https://goo.gl/forms/jk1nzkzHSXbBoa542";
    String reportbugurl ="https://goo.gl/forms/BLPlCF1FlTRonoXU2";
    String tusharfb ="https://www.facebook.com/ydv.tushar";
    String nikhilfb ="https://www.facebook.com/nikhil.gaddam.5";
    String ashwinfb ="https://www.facebook.com/ashwin.kulkarni.90";
    String ankurfb ="https://www.facebook.com/ankur.ingale.35";
    String anshumanfb ="https://www.facebook.com/anshuman.dhar.9";
    String shivamfb ="https://www.facebook.com/shivamkr286";
    String vivekfb ="https://www.facebook.com/SuperVivekRaj";
    String rishabfb ="https://www.facebook.com/rishabh.sood.98";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                startActivity(new Intent(AboutUs.this,Main2Activity.class));
                finish();
            }
        });
                 final ImageView tusharpic = findViewById(R.id.tusharpic);
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F50502200_1530490733720412_3046734879342460928_n%20(1).jpg?alt=media&token=410909c0-25b8-4881-a185-96dfdae7007b").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(tusharpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F50502200_1530490733720412_3046734879342460928_n%20(1).jpg?alt=media&token=410909c0-25b8-4881-a185-96dfdae7007b").placeholder(R.drawable.user).into(tusharpic);
                                    }
                                });
        final ImageView shubhampic = findViewById(R.id.shubhampic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Fshubham-kumar-gupta.jpg?alt=media&token=6fbf2b32-400c-4f84-8d65-cf9935b96380").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(shubhampic, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Fshubham-kumar-gupta.jpg?alt=media&token=6fbf2b32-400c-4f84-8d65-cf9935b96380").placeholder(R.drawable.user).into(shubhampic);
            }
        });
             final ImageView nikhilpic = findViewById(R.id.nikhilpic);
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F20190203_210454_4%20(1).jpg?alt=media&token=3ca2058a-9c13-4c0c-8227-6bbb8ee0e59c").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(nikhilpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F20190203_210454_4%20(1).jpg?alt=media&token=3ca2058a-9c13-4c0c-8227-6bbb8ee0e59c").placeholder(R.drawable.user).into(nikhilpic);
                                    }
                                });
            final ImageView ashwinpic = findViewById(R.id.ashwinpic);
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F29496883_1756852191042325_5958279705923682304_n%20(1).jpg?alt=media&token=6f274a25-846a-41f2-8f0f-74bd5e2afb68").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(ashwinpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F29496883_1756852191042325_5958279705923682304_n%20(1).jpg?alt=media&token=6f274a25-846a-41f2-8f0f-74bd5e2afb68").placeholder(R.drawable.user).into(ashwinpic);
                                    }
                                });
   
        final ImageView ankurpic = findViewById(R.id.ankurpic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F45866477_2109294219136911_544194844851961856_n%20(1).jpg?alt=media&token=b7b1a83d-be0f-450d-8ca5-7c8dfff67f15").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(ankurpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F45866477_2109294219136911_544194844851961856_n%20(1).jpg?alt=media&token=b7b1a83d-be0f-450d-8ca5-7c8dfff67f15").placeholder(R.drawable.user).into(ankurpic);
                                    }
                                });

        final ImageView anshumanpic = findViewById(R.id.anshumanpic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F50160322_10210378196171550_1965820966764281856_n%20(1).jpg?alt=media&token=55b9900a-abf2-4f18-8f43-ba43726a9904").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(anshumanpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F50160322_10210378196171550_1965820966764281856_n%20(1).jpg?alt=media&token=55b9900a-abf2-4f18-8f43-ba43726a9904").placeholder(R.drawable.user).into(anshumanpic);
                                    }
                                });

        final ImageView vivekpic = findViewById(R.id.vivekpic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Fvivek-compressed.jpg?alt=media&token=9483f3e4-75e9-4e41-88ab-2c03208d5553").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(vivekpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Fvivek-compressed.jpg?alt=media&token=9483f3e4-75e9-4e41-88ab-2c03208d5553").placeholder(R.drawable.user).into(vivekpic);
                                    }
                                });
        final ImageView arhanpic = findViewById(R.id.arhanpic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Farhanpic.jpg?alt=media&token=356e547d-5259-4fa4-8e65-0e52337551cc").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(arhanpic, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Farhanpic.jpg?alt=media&token=356e547d-5259-4fa4-8e65-0e52337551cc").placeholder(R.drawable.user).into(arhanpic);
            }
        });

        final ImageView shivampic = findViewById(R.id.shivampic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F40675716_885072011686383_5229715384071880704_n%20(1).jpg?alt=media&token=ab00bb9c-8fe4-43aa-84af-0f5ed7afca5d").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(shivampic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2F40675716_885072011686383_5229715384071880704_n%20(1).jpg?alt=media&token=ab00bb9c-8fe4-43aa-84af-0f5ed7afca5d").placeholder(R.drawable.user).into(shivampic);
                                    }
                                });

        final ImageView rishabpic = findViewById(R.id.rishabpic);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Frishab%20(1).jpg?alt=media&token=a316a603-7152-427f-ab1b-c97bc35f5d4a").networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.user).into(rishabpic, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/onestopiitg.appspot.com/o/Aboutus%20Pics%2Frishab%20(1).jpg?alt=media&token=a316a603-7152-427f-ab1b-c97bc35f5d4a").placeholder(R.drawable.user).into(rishabpic);
                                    }
                                });

        final LinearLayout feedback = (LinearLayout) findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",feedbackurl);
                i.putExtra("heading","Feedback Form");
                //i.setData(Uri.parse(feedbackurl));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout reportbug = (LinearLayout) findViewById(R.id.reportbug);
        reportbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_VIEW);
                Intent i=new Intent(getApplicationContext(),Url.class);
                //i.setData(Uri.parse(reportbugurl));
                i.putExtra("url",reportbugurl);
                i.putExtra("heading","Bug Report Form");
                startActivity(i);
                finish();
            }
        });

        final LinearLayout tushar = (LinearLayout) findViewById(R.id.tushar);
        tushar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_VIEW);
                Intent i=new Intent(getApplicationContext(), Url.class);
                i.putExtra("url",tusharfb);
                i.putExtra("heading","TUSHAR");
                //i.setData(Uri.parse(tusharfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout nikhil = (LinearLayout) findViewById(R.id.nikhil);
        nikhil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",nikhilfb);
                i.putExtra("heading","NIKHIL");
                //i.setData(Uri.parse(nikhilfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout ashwin = (LinearLayout) findViewById(R.id.ashwin);
        ashwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",ashwinfb);
                i.putExtra("heading","ASHWIN");
               // i.setData(Uri.parse(ashwinfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout ankur = (LinearLayout) findViewById(R.id.ankur);
        ankur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",ankurfb);
                i.putExtra("heading","ANKUR");
                //i.setData(Uri.parse(ankurfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout anshuman = (LinearLayout) findViewById(R.id.anshunam);
        anshuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",anshumanfb);
                i.putExtra("heading","ANSHUMAN");
                //i.setData(Uri.parse(anshumanfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout shivam = (LinearLayout) findViewById(R.id.shivam);
        shivam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",shivamfb);
                i.putExtra("heading","SHIVAM");
                //i.setData(Uri.parse(shivamfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout vivek = (LinearLayout) findViewById(R.id.vivek);
        vivek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",vivekfb);
                i.putExtra("heading","VIVEK");
                //i.setData(Uri.parse(vivekfb));
                startActivity(i);
                finish();
            }
        });
        final LinearLayout rishab = (LinearLayout) findViewById(R.id.rishab);
        rishab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Url.class);
                i.putExtra("url",rishabfb);
                i.putExtra("heading","RISHAB");
                //i.setData(Uri.parse(rishabfb));
                startActivity(i);
                finish();
            }
        });





    }
    public void onClick(View v){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AboutUs.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }
}
