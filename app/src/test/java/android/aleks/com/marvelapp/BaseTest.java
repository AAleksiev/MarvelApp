package android.aleks.com.marvelapp;

import android.aleks.com.marvelapp.rest.models.Comic;
import android.aleks.com.marvelapp.rest.models.ComicDataContainer;
import android.aleks.com.marvelapp.rest.models.ComicDataWrapper;
import android.aleks.com.marvelapp.rest.models.CreatorList;
import android.aleks.com.marvelapp.rest.models.CreatorSummary;
import android.aleks.com.marvelapp.rest.models.Image;

import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar on 5.1.2018 г..
 */

public class BaseTest {

    //region fields
    protected static ComicDataWrapper comicDataWrapper;
    //endregion

    @BeforeClass
    public static void initClassData() {

        final List< Comic > comics = new ArrayList<>();
        final List< CreatorSummary > creators = new ArrayList<>();

        creators.add( new CreatorSummary( "Cr 1", "A" ) );
        creators.add( new CreatorSummary( "Cr 2", "B" ) );

        final CreatorList creatorList = new CreatorList( creators );

        comics.add( new Comic( 1, "First", "First Descr", 10, new Image( "thumbPath" ), null, creatorList ) );
        comics.add( new Comic( 2, "Second", "Second Descr", 20, new Image( "thumbPath" ), null, creatorList ) );
        comics.add( new Comic( 3, "Third", "Third Descr", 30, new Image( "thumbPath" ), null, creatorList ) );


        final ComicDataContainer comicDataContainer = new ComicDataContainer( comics );
        comicDataWrapper = new ComicDataWrapper( 200, "OK", comicDataContainer );
    }
}
