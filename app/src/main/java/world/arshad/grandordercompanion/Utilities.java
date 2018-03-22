package world.arshad.grandordercompanion;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by arsha on 20/03/2018.
 */

public class Utilities {

    public static Drawable loadImageFromAssets(String filename, AssetManager assetManager) {
        try {
            return Drawable.createFromStream(assetManager.open(filename), null);
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }

        return null;

    }
}
