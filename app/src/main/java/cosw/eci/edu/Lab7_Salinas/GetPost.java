package cosw.eci.edu.Lab7_Salinas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import cosw.eci.edu.lab8.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GetPost.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GetPost#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetPost extends Fragment {

    ///////////////////////////////////// GENERATED ///////////////////////////////////////

    private OnFragmentInteractionListener mListener;

    public GetPost() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GetPost newInstance(String param1, String param2) {
        GetPost fragment = new GetPost();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    private String text;
    private Uri path;
    private ImageView image;
    private TextView textView;

    public void setMessage(String message){
        this.text=message;
    }

    public void setUri (Uri uri){ this.path=uri; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_post, container, false);
        image = (ImageView) rootView.findViewById(R.id.image);
        image.setImageURI(path);
        textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText(text);
        return rootView;
    }
}
