package cosw.eci.edu.Lab7_Salinas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import cosw.eci.edu.lab8.R;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewPost.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewPost#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPost extends Fragment{

    ///////////////////////////////////// GENERATED ///////////////////////////////////////

    private OnFragmentInteractionListener mListener;

    public NewPost() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewPost.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPost newInstance(String param1, String param2) {
        NewPost fragment = new NewPost();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    ///////////////////////////////////// BY JHORDY ///////////////////////////////////////

    // elements in view
    private EditText text;
    private Button addPhoto;
    private ImageView image;
    private Button save;
    private CheckedTextView imageChecked;
    private CheckedTextView textChecked;
    // logic
    private String[] optionItems = {"Camera","Gallery"};
    private Uri imagePath;
    public static final int GALLERY = 1;
    public static final int CAMERA = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_post, container,false);
        text = (EditText) rootView.findViewById(R.id.text);
        image = (ImageView) rootView.findViewById(R.id.imageView);
        addPhoto = (Button) rootView.findViewById(R.id.addPhoto);

        imageChecked = (CheckedTextView) rootView.findViewById(R.id.imageChecked);
        textChecked = (CheckedTextView) rootView.findViewById(R.id.textChecked);

        save = (Button) rootView.findViewById(R.id.save);

        image.setVisibility(View.INVISIBLE);
        textChecked.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
        imageChecked.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);

        //button listeners
        /// SAVE
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClickSave(view);
            }
        });
        /// ADD
        addPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClickAddPhoto(view);
            }
        });
        //listener on any change
        text.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>=50) {textChecked.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);}
                else{textChecked.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);}
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        return rootView;
    }

    public void onClickAddPhoto (View v){
        DialogInterface.OnClickListener selectedListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                image.setVisibility(View.VISIBLE);
                imageChecked.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                if (which == 0 ) {
                    //Camera
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();StrictMode.setVmPolicy(builder.build());
                    if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) cameraIntent();
                }
                else{
                    //Gallery
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
                }
                dialog.dismiss();
            }
        };
        // Choose custom option
        createSingleChoiceAlertDialog(getActivity() ,"Select Option", optionItems, selectedListener,null).show();
    }
    @NonNull
    public static Dialog createSingleChoiceAlertDialog(@NonNull Context context, @Nullable String title,
                                                       @NonNull CharSequence[] items,
                                                       @NonNull DialogInterface.OnClickListener optionSelectedListener,
                                                       @Nullable DialogInterface.OnClickListener cancelListener )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder( context, R.style.AppTheme );
        builder.setItems( items, optionSelectedListener );
        if ( cancelListener != null )
        {
            builder.setNegativeButton( R.string.Cancel, cancelListener );
        }
        builder.setTitle( title );
        return builder.create();
    }

    private void cameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String photoFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File photo = File.createTempFile(photoFileName,".jpg", storageDir);
                // If create the file, now:
                imagePath = Uri.fromFile(photo);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                startActivityForResult(takePictureIntent, 2);
            } catch (IOException ex) {
                System.err.println("Error in cameraIntent");
            }
        }
    }

    private void onClickSave(View view) {
        if(text.getText().toString().length()>=50 && image.getVisibility()==View.VISIBLE){
            Intent intent = new Intent(getActivity(), PostActivity.class);
            // Bundle to pass between activities
            Bundle bundle = new Bundle();
            bundle.putSerializable("value", new Post(text.getText().toString(),imagePath.toString()));
            intent.putExtra("bundlePost",bundle);
            //Start the new activity using the intent.
            startActivity(intent);
        }
        else{
            text.setError(getResources().getString(R.string.saveError));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==getActivity().RESULT_OK){
            if (requestCode==GALLERY){
                imagePath = data.getData();
            }
            image.setImageURI(null);
            image.setImageURI(imagePath);
        }
    }
}