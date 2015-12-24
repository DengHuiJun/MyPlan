package com.zero.myplan.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by zero on 15-12-24.
 */
public class CommonDialogFragment extends DialogFragment{
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_POSITIVE_BTN = "positiveBtn";
    public static final String KEY_NEGATIVE_BTN = "negativeBtn";

    private String mMessage = "not init";
    private String mPositiveBtnStr = "not init";
    private String mNegativeBtnStr = "not init";

    public interface CommonDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    CommonDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CommonDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement CommonDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle args = getArguments();
        mMessage = args.getString(KEY_MESSAGE);
        mPositiveBtnStr = args.getString(KEY_POSITIVE_BTN);
        mNegativeBtnStr = args.getString(KEY_NEGATIVE_BTN);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(mMessage)
                .setPositiveButton(mPositiveBtnStr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CommonDialogFragment.this);
                    }
                })
                .setNegativeButton(mNegativeBtnStr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CommonDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
