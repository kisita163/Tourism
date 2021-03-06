package com.kisita.tourism.util;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.kisita.tourism.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugues on 30/10/2016.
 */
public class ServicesDialog extends DialogFragment {
    public static final int lodging = 1;
    public static final int restoration = 0;
    public static final int events = 2;
    public static final int culture = 3;
    public static final int health = 4;
    public static final int stActivities = 5;
    public static final int beauty = 6;


    private List<Integer> mSelectedItems;
    private int serviceType;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        serviceType = this.getArguments().getInt("service");
        Log.i("GridView", "service type  = " + serviceType);

        builder.setTitle(getDialogTitle(serviceType))
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(getDialogArray(serviceType), null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isCkhecked) {
                                if (isCkhecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        //...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //...
                    }
                });

        return builder.create();
    }

    private int getDialogTitle(int serviceType) {
        switch (serviceType){
            case lodging:
                return R.string.lodging;
            case events:
                return R.string.events;
            case culture:
                return R.string.culture;
            case restoration:
                return R.string.restoration;
            case health:
                return R.string.health;
            case stActivities:
                return R.string.st_activities;
            case beauty:
                return R.string.beauty;
            default:
                return -1;
        }
    }

    private int getDialogArray(int serviceType) {
        switch(serviceType)
        {
            case lodging:
                return R.array.lodging_dialog;
            case events:
                return R.array.events_dialog;
            case culture:
                return R.array.culture_dialog;
            case stActivities:
                return R.array.st_activities;
            case restoration:
                return R.array.restoration;
            case health:
                return R.array.health;
            case beauty:
                return R.array.beauty;
            default:
                return -1;
        }
    }
}
