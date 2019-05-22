package smktelkom_mlg.macitourapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by MirzaUY on 3/18/2018.
 */

public class DialogClass extends AppCompatDialogFragment {
    int min = 1, max = 5, current = 2;
    private EditText etComment;
    private TextView etRate;
    private SeekBar seekBar;
    private DialogClassListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        etComment = (EditText) view.findViewById(R.id.comment);
        etRate = (TextView) view.findViewById(R.id.rate);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(max - min);
        seekBar.setProgress(current - min);
        etRate.setText("" + current);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current = progress + min;
                etRate.setText("" + current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(view).setTitle("Review")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String comment = etComment.getText().toString();
                        String rate = etRate.getText().toString();
                        listener.sendReview(comment, rate);
                    }
                });

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogClassListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogClassListener");
        }
    }

    public interface DialogClassListener {
        void sendReview(String comment, String rate);
    }
}
