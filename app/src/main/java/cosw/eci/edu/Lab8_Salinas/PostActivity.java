package cosw.eci.edu.Lab7_Salinas;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import cosw.eci.edu.lab8.R;

public class PostActivity extends AppCompatActivity implements GetPost.OnFragmentInteractionListener {
    private Uri imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        //Obtain the object
        Post p=(Post) intent.getBundleExtra("bundlePost").getSerializable("value");
        //Show fragment
        GetPost sf = new GetPost();
        sf.setMessage(p.getText());
        sf.setUri(Uri.parse(p.getImagePath()));
        showFragment(sf,false);

        Fragment NewPostFragment = new Fragment();
    }

    public void showFragment(android.support.v4.app.Fragment fragment, boolean addToBackStack)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        String tag = fragment.getClass().getSimpleName();
        if ( addToBackStack )
        {
            transaction.addToBackStack( tag );
        }
        transaction.replace( R.id.fragment_container, fragment, tag );
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
