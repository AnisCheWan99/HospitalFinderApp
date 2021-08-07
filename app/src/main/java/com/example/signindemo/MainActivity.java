package com.example.signindemo;

//
//685025913085-nh1l8iu2jfq4gt6ol6j5m8c6s0m3la5a.apps.googleusercontent.com
//

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    private Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);


        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account ==  null){
            //null - the user haven't sign in yet!!

            Toast.makeText(this,"Please sign in with Gmail account", Toast.LENGTH_SHORT).show();

        }else{
            //User already sign in, so show the Secret Page!!
            Intent intent = new Intent(this, RahsiaActivity.class);
            intent.putExtra("Name",account.getDisplayName());
            intent.putExtra("Email",account.getEmail());

            startActivity(intent);
        }


        about= (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                openActivity2();
            }
        });

    }

    public void openActivity2(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.sign_in_button:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 10);
                break;
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 10) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //Buka activity baru, yang rahsia tu "secret place
            Toast.makeText(this, "Already sign in!!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, RahsiaActivity.class);
            intent.putExtra("Name",account.getDisplayName());
            intent.putExtra("Email",account.getEmail());

            startActivity(intent);


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR", "signInResult:failed code=" + e.getStatusCode());

            //TAKBOLEH SIGN IN...
            Toast.makeText(this,"Sorry, you need to try again !",Toast.LENGTH_SHORT).show();

            //updateUI(null);
        }
    }






    //call menu_nav (menu bar) dari main activity

    public void openProfile(){

        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.profile:
                startActivity(new Intent(MainActivity.this,About.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}