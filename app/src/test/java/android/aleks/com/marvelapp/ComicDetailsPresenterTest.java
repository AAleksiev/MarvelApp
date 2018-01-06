package android.aleks.com.marvelapp;

import android.accounts.NetworkErrorException;
import android.aleks.com.marvelapp.models.ComicDetailsViewModel;
import android.aleks.com.marvelapp.rest.MarvelService;
import android.aleks.com.marvelapp.rest.auth.RequestAuthProvider;
import android.aleks.com.marvelapp.ui.details.ComicDetailsPresenter;
import android.aleks.com.marvelapp.ui.details.ComicDetailsView;

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

public class ComicDetailsPresenterTest extends BaseTest {

    @Mock
    MarvelService marvelService;
    @Mock
    RequestAuthProvider requestAuthProvider;
    @Mock
    SchedulersProvider testSchedulersProvider;
    @Mock
    ComicDetailsView comicDetailsView;
    @InjectMocks
    protected ComicDetailsPresenter mockComicDetailsPresenter;

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

                mockComicDetailsPresenter.onAttach( comicDetailsView );
                mockComicDetailsPresenter.loadComicDetails( 1 );
                return 1;
            }
        } ).when( comicDetailsView ).viewAttached();
    }

    @Test
    public void comicsShouldBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( comicDetailsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.just( comicDataWrapper );
            }
        } ).when( marvelService ).getComicDetails( Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString() );

        comicDetailsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).onComicLoaded( Mockito.nullable( ComicDetailsViewModel.class ) );
    }

    @Test
    public void comicsShouldNotBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( comicDetailsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.error( new NetworkErrorException() );
            }
        } ).when( marvelService ).getComicDetails( Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString() );

        comicDetailsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( comicDetailsView, Mockito.times( 1 ) ).onError( Mockito.nullable( String.class ) );
    }
}