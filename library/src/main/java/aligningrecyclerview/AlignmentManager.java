/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.support.annotation.NonNull;

/**
 * This is a general-purpose helper to mass-manage binding and unbinding operations in sets of AligningRecyclerView objects.
 *
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
public final class AlignmentManager {

  private AlignmentManager() {
    throw new UnsupportedOperationException("No instances.");
  }

  /**
   * Bi-directionally binds all the given AligningRecyclerView objects to each other.
   *
   * @param orientation int The alignment mode to use.
   * @param toJoin      AligningRecyclerView[] The objects to bind.
   */
  public static void join(final @AligningRecyclerView.AlignOrientation int orientation, final @NonNull AligningRecyclerView... toJoin) {
    for (final AligningRecyclerView currentSrc : toJoin) {
      for (final AligningRecyclerView currentTarget : toJoin) {
        if (!currentSrc.equals(currentTarget)) {
          currentSrc.bindTo(currentTarget, orientation);
        }
      }
    }
  }

  /**
   * Removes all bindings within the given AligningRecyclerView objects.
   *
   * @param toDisjoin   AligningRecyclerView[] The objects to unbind.
   * @param orientation int The alignment mode to watch out for.
   */
  public static void disjoin(final @AligningRecyclerView.AlignOrientation int orientation, final @NonNull AligningRecyclerView... toDisjoin) {
    for (final AligningRecyclerView currentSrc : toDisjoin) {
      for (final AligningRecyclerView currentTarget : toDisjoin) {
        if (!currentSrc.equals(currentTarget)) {
          currentSrc.unbindFrom(currentTarget, orientation);
        }
      }
    }
  }
}
