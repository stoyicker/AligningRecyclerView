package annoyodroid.io.network;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.ResponseBody;

import retrofit.Response;

class MockResponseWrapper<T> {

  private final Response<T> mResponse;

  public Response<T> getResponse() {
    return mResponse;
  }

  public MockResponseWrapper(final int statusCode, final T successfulBody) {
    mResponse = Response.success(successfulBody, new com.squareup.okhttp.Response.Builder()
        .code(statusCode)
        .protocol(Protocol.HTTP_1_1)
        .request(new com.squareup.okhttp.Request.Builder().url(HttpUrl.parse
            ("http://localhost"))
            .build())
        .build());
  }

  public MockResponseWrapper(final int statusCode, final ResponseBody errorBody) {
    mResponse = Response.error(statusCode, errorBody);
  }
}
