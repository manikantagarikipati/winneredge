package com.winneredge.assetmanager.wcommons.texticon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Manikanta on 17-07-2015.
 */
public class ColorGenerator {

    public static final ColorGenerator DEFAULT;

    public static final ColorGenerator MATERIAL;

    public static final ColorGenerator BRIGHT;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));
        MATERIAL = create(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae
        ));

        BRIGHT = create(Arrays.asList(
                0xff827717,
                0xff33691E,
                0xff263238,
                0xff3E2723,
                0xffBF360C,
                0xffFF6D00,
                0xffF57F17,
                0xff1B5E20,
                0xff00BFA5,
                0xff00B8D4,
                0xff0091EA,
                0xff0D47A1,
                0xff1A237E,
                0xff311B92,
                0xffB71C1C,
                0xff880E4F,
                0xff4A148C
        ));

    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode() % mColors.size()));
    }
}
