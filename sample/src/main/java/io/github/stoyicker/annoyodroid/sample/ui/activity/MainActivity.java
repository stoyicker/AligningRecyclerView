package io.github.stoyicker.annoyodroid.sample.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.view_response)
    TextView mResponseField;

    @Bind(R.id.spinner_methods)
    Spinner mMethodSpinner;

    @Bind(R.id.button_sync)
    Button mButtonSync;

    @Bind(R.id.button_async)
    Button mButtonASync;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mResponseField.setMovementMethod(new ScrollingMovementMethod());
    }

    @OnClick(R.id.button_async)
    void requestAsync() {
        final int position = mMethodSpinner.getSelectedItemPosition();
        final Callback<Object> genericCallback = new Callback<Object>() {
            @Override
            public void success(final @Nullable Object o, final Response response) {
                MainActivity.this.setText(response.getStatus() + ":" + (o != null ? o.toString() : "Empty response received"));
            }

            @Override
            public void failure(final RetrofitError error) {
                final Response response = error.getResponse();
                MainActivity.this.setText(response.getStatus() + ":" + response.getReason());
            }
        };
    }

    @OnClick(R.id.button_sync)
    void requestSync() {
        final int position = mMethodSpinner.getSelectedItemPosition();
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(final Void... params) {
                return null;
            }

            @Override
            protected void onPostExecute(final @Nullable Object o) {
                MainActivity.this.setText((o != null ? o.toString() : "Empty response received"));
            }
        }.executeOnExecutor(Executors.newSingleThreadExecutor());
    }

    @OnClick(R.id.button_rxandroid)
    void requestRx() {
        Observable.just(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        MainActivity.this.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(final Object o) {
                        MainActivity.this.setText((o != null ? o.toString() : "Empty response received"));
                    }
                });
    }

    @OnItemSelected(R.id.spinner_methods)
    void updateEnabledButtons(final int position) {
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
