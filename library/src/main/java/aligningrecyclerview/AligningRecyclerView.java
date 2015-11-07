/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * A RecyclerView that can synchronize its scrolling after one or more other RecyclerViews.
 */
public class AligningRecyclerView extends RecyclerView {

  /**
   * {@inheritDoc}
   *
   * @param context {@inheritDoc}
   */
  public AligningRecyclerView(final Context context) {
    super(context);
  }

  /**
   * {@inheritDoc}
   *
   * @param context {@inheritDoc}
   * @param attrs   {@inheritDoc}
   */
  public AligningRecyclerView(final Context context, final @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * {@inheritDoc}
   *
   * @param context  {@inheritDoc}
   * @param attrs    {@inheritDoc}
   * @param defStyle {@inheritDoc}
   */
  public AligningRecyclerView(final Context context, final @Nullable AttributeSet attrs, final int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * Binds this AligningRecyclerView to a RecyclerView. This is an unidirectional binding, meaning that calling this method implies that scrolling {@code target} will cause this AligningRecyclerView to scroll.
   * Calling this method does not modify the behavior of the symmetric binding, if any.
   *
   * @param target RecyclerView The target to bind to.
   * @return The success of the operation. Usually the operation would fail if {@code target} is the
   * own
   * object or the binding already exists.
   */
  public boolean bindTo(final @NonNull RecyclerView target) {
    if (isBoundTo(target)) {
      return false;
    }
    throw new UnsupportedOperationException("Not yet implemented");
  }

  /**
   * Unbinds this AligningRecyclerView from a RecyclerView. This is an
   * unidirectional unbinding, meaning that calling this method implies that scrolling {@code
   * target} will no longer cause this AligningRecyclerView to scroll.
   * Calling this method does not modify the behavior of the symmetric binding, if any.
   *
   * @param target RecyclerView The target to unbind from.
   * @return The success of the operation. Usually the operation would fail if {@code target} is the
   * own object or the binding does not already exists.
   */
  public boolean unbindFrom(final @NonNull RecyclerView target) {
    if (!isBoundTo(target)) {
      return false;
    }
    throw new UnsupportedOperationException("Not yet implemented");
  }

  /**
   * Verifies is this AligningRecyclerView is bound to the given RecyclerView.
   *
   * @param target RecyclerView The target towards which the existence of the binding
   *               shall be verified.
   * @return {@code true} if there is a binding from this object towards {@code target};
   * {@code false} otherwise.
   */
  public boolean isBoundTo(final @NonNull RecyclerView target) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
