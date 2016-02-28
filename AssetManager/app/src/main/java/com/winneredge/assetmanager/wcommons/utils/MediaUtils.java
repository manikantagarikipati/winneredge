package com.winneredge.assetmanager.wcommons.utils;

import android.content.Context;

import com.winneredge.assetmanager.R;
import com.winneredge.assetmanager.wcommons.texticon.ColorGenerator;
import com.winneredge.assetmanager.wcommons.texticon.TextIcons;

/**
 * Created by Manikanta on 2/28/2016.
 */
public class MediaUtils {

    public static TextIcons getRoundedTextIcon(String iconText,Context context){

        iconText = iconText.trim();
        ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
        TextIcons.IBuilder mDrawableBuilder;
        String nextLetter;
        mDrawableBuilder = TextIcons.builder().round();
        String FirstLetter = String.valueOf(iconText.charAt(0)).toUpperCase();
        String[] words = iconText.split("\\s+");
        if(words.length>1){
            nextLetter = String.valueOf(words[1].charAt(0)).toUpperCase();
        }
        else{

            nextLetter = (iconText.length()>1)? String.valueOf(iconText.charAt(1)).toUpperCase():"";
        }

        int fontSize = Math.round(context.getResources().getDimension(R.dimen.updates_text_icon_size));
        return mDrawableBuilder.build(FirstLetter +nextLetter, mColorGenerator.getColor(iconText),fontSize);

    }

}
