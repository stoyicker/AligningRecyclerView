package io.github.stoyicker.annoyodroid.sample.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import io.github.stoyicker.annoyodroid.sample.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("WeakerAccess") //Butterknife
    @Bind(R.id.view_response)
    TextView mResponseField;

    @SuppressWarnings("WeakerAccess") //Butterknife
    @Bind(R.id.spinner_methods)
    Spinner mMethodSpinner;

    @Bind(R.id.button_sync)
    Button mButtonSync;

    @Bind(R.id.button_async)
    Button mButtonASync;

    @SuppressWarnings("WeakerAccess") //Butterknife
    @Bind(R.id.progress_bar)
    View mProgressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mResponseField.setMovementMethod(new ScrollingMovementMethod());
    }

    @OnClick(R.id.button_async)
    void requestAsync() {
        setResponseVisibility(false);
        //You'll have to instatiate it in the switch statement
        final Callback<Object> genericCallback = new Callback<Object>() {

            @Override
            public void success(final Object o, final Response response) {
                MainActivity.this.setText((o != null ? o.toString() : "Empty response received"));
                MainActivity.this.setResponseVisibility(true);
            }

            @Override
            public void failure(final RetrofitError error) {
                final Response response = error.getResponse();
                MainActivity.this.setText(response.getStatus() + ":" + response.getReason());
            }
        };
        final int position;

        switch (position = mMethodSpinner.getSelectedItemPosition()) {
            default:
                throw new IllegalArgumentException("Position " + position + " not properly aligned with a method");
        }
    }

    private void setResponseVisibility(final boolean b) {
        mProgressBar.post(new BooleanParameterRunnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(mBoolean ? View.INVISIBLE : View.VISIBLE);
            }
        }.init(b));
        mResponseField.post(new BooleanParameterRunnable() {
            @Override
            public void run() {
                mResponseField.setVisibility(mBoolean ? View.VISIBLE : View.INVISIBLE);
            }
        }.init(b));
    }

    private static abstract class BooleanParameterRunnable implements Runnable {

        boolean mBoolean;

        BooleanParameterRunnable init(boolean b) {
            this.mBoolean = b;
            return this;
        }

        @Override
        public abstract void run();
    }

    @OnClick(R.id.button_sync)
    void requestSync() {
        setResponseVisibility(false);
        new AsyncTask<Void, Void, Object>() {

            private int mPosition;

            private AsyncTask<Void, Void, Object> init(int position) {
                mPosition = position;
                return this;
            }

            @Override
            protected Object doInBackground(final Void... params) {
                switch (mPosition) {
                    default:
//                throw new IllegalArgumentException("Position " + position + " not properly aligned with a method");
                }
                return null;
            }

            @Override
            protected void onPostExecute(final @Nullable Object o) {
                MainActivity.this.setText((o != null ? o.toString() : "Empty response received"));
                MainActivity.this.setResponseVisibility(true);
            }
        }.init(mMethodSpinner.getSelectedItemPosition()).executeOnExecutor(Executors.newSingleThreadExecutor());
    }

    @OnClick(R.id.button_rxandroid)
    void requestRx() {
        setResponseVisibility(false);
        //You'll have to instatiate in the switch statement
        Observable<Object> genericCallback = null;
        final int position;

        switch (position = mMethodSpinner.getSelectedItemPosition()) {
            default:
//                throw new IllegalArgumentException("Position " + position + " not properly aligned with a method");
        }
        Observable.just(null)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        MainActivity.this.setResponseVisibility(true);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        MainActivity.this.setText(e.getMessage());
                        MainActivity.this.setResponseVisibility(true);
                    }

                    @Override
                    public void onNext(final Object o) {
                        MainActivity.this.setText((o != null ? o.toString() : "Empty response received"));

                    }
                });
    }

    @OnItemSelected(R.id.spinner_methods)
    void updateEnabledButtons(final int position) {
        //Remember to use post
        switch (position) {
            default:
                throw new IllegalArgumentException("Position " + position + " not properly aligned with a method");
        }
    }

    private void setText(final String text) {
        MainActivity.this.mResponseField.post(new Runnable() {

            private String mText;

            private Runnable init(final String text) {
                mText = text;
                return this;
            }

            @Override
            public void run() {
                mResponseField.setText(mText);
            }
        }.init(text));
    }
}
