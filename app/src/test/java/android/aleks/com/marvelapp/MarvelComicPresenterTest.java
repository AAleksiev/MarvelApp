package android.aleks.com.marvelapp;

import android.accounts.NetworkErrorException;
import android.aleks.com.marvelapp.models.ComicItemViewModel;
import android.aleks.com.marvelapp.rest.MarvelService;
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider;
import android.aleks.com.marvelapp.ui.comics.MarvelComicsPresenter;
import android.aleks.com.marvelapp.ui.comics.MarvelComicsView;
import android.support.v7.util.DiffUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import example.aleks.com.postapp.schedulers.SchedulersProvider;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by Aleksandar on 5.1.2018 г..
 */

public class MarvelComicPresenterTest extends BaseTest {

    @Mock
    MarvelService marvelService;
    @Mock
    RequestAuthProvider requestAuthProvider;
    @Mock
    MarvelComicsView marvelComicsView;
    @Mock
    SchedulersProvider testSchedulersProvider;
    @InjectMocks
    protected MarvelComicsPresenter marvelComicsPresenter;

    private final TestScheduler testScheduler = new TestScheduler();

    @Before
    public void setup() {

        MockitoAnnotations.initMocks( this );

        Mockito.doReturn(testScheduler).when(testSchedulersProvider).ioScheduler();
        Mockito.doReturn(testScheduler).when(testSchedulersProvider).mainScheduler();
        Mockito.doReturn(testScheduler).when(testSchedulersProvider).computationScheduler();

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                marvelComicsPresenter.onAttach( marvelComicsView );
                return 1;
            }
        } ).when( marvelComicsView ).viewAttached();
    }

    @Test
    public void comicsShouldBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( marvelComicsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.just( comicDataWrapper );
            }
        } ).when( marvelService ).getComics( Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString() );

        marvelComicsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).onComicsLoaded( Mockito.<ComicItemViewModel>anyList(), Mockito.any( DiffUtil.DiffResult.class ) );
    }

    @Test
    public void comicsShouldNotBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( marvelComicsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.error( new NetworkErrorException() );
            }
        } ).when( marvelService ).getComics( Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString() );

        marvelComicsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( marvelComicsView, Mockito.times( 1 ) ).onError( Mockito.nullable( String.class ) );
    }
}
