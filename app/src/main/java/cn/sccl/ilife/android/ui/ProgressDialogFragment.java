package cn.sccl.ilife.android.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.sccl.ilife.android.R;
import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProgressDialogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProgressDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressDialogFragment extends RoboDialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PROGRESS_TITLE = "正在载入";

    // TODO: Rename and change types of parameters
    private String progressTitle;
    private View v;

    private OnFragmentInteractionListener mListener;

    @InjectView(R.id.progressbar)
    private ProgressBar progressBar;

    private TextView titleTextView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment ProgressDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressDialogFragment newInstance(String title) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString(PROGRESS_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ProgressDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            progressTitle = getArguments().getString(PROGRESS_TITLE);
        }
        setStyle(STYLE_NO_INPUT, android.R.style.Theme_Translucent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_progress_dialog, container, false);
        titleTextView = (TextView) v.findViewById(R.id.progressbar_title);
        titleTextView.setText(progressTitle);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
