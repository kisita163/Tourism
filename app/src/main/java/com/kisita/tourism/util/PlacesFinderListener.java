package com.kisita.tourism.util;

import java.util.List;

/**
 * Created by Hugues on 03/10/2016.
 */
public interface PlacesFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Place> place);
}
