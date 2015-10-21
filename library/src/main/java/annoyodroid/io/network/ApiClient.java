/*
 * AnnoyODroid (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AnnoyODroid is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package annoyodroid.io.network;

import retrofit.Retrofit;

import javax.validation.constraints.NotNull;

public final class ApiClient {

  private static IApiService service;
  private static final Object LOCK = new Object();
  private static String currentHost;
  private static boolean isMocking = false;

  private ApiClient() {
    throw new IllegalAccessError("No instances");
  }

  /**
   * Creates a Retrofit instance
   *
   * @param host The host to use for the instance.
   * @return The instance created
   */
  private static Retrofit createRetrofit(final @NotNull String host) {
    final Retrofit.Builder builder = new Retrofit.Builder();

    return builder
        .baseUrl(host)
        .build();
  }

  /**
   * Injects a service. <strong>To be used for testing purposes only</strong>
   *
   * @param _service The service to inject
   */
  static void setApiService(final @NotNull IApiService _service) {
    ApiClient.service = _service;
    isMocking = true;
  }

  /**
   * Gets the singleton service for a given host. If it does not match the current one, a new
   * instance is created to replace it
   *
   * @param host The corresponding host
   * @return The singleton service instance
   */
  public static synchronized IApiService getApiService(@NotNull final String host) {
    IApiService ret = service;

    if (service == null || !isMocking && !host.contentEquals(currentHost)) {
      synchronized (LOCK) {
        ret = service;
        if (service == null || !isMocking && !host.contentEquals(currentHost)) {
          currentHost = host;
          ret = createRetrofit(host).create(IApiService.class);
          service = ret;
        }
      }
    }

    return ret;
  }
}
