/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

/**
 * This is a general-purpose helper to mass-manage binding and unbinding operations in sets of {@link AligningRecyclerView} objects.
 */
public final class AlignmentManager {

  private AlignmentManager() throws IllegalAccessException {
    throw new IllegalAccessException("No instances.");
  }

  /**
   * Bi-directionally binds all the given {@link AligningRecyclerView} objects to each other.
   *
   * @param toJoin {@link AligningRecyclerView}[] The objects to bind.
   */
  public static void join(final AligningRecyclerView... toJoin) {
  }

  /**
   * Removes all bindings withing the given {@link AligningRecyclerView} objects.
   *
   * @param toDisjoin {@link AligningRecyclerView}[] The objects to unbind.
   */
  public static void disjoin(final AligningRecyclerView... toDisjoin) {
  }
}
