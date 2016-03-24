/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A RecyclerView that can synchronize its scrolling after one or more other RecyclerViews.
 *
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
public class AligningRecyclerView extends RecyclerView {

  public static final int ALIGN_ORIENTATION_VERTICAL = 1,
      ALIGN_ORIENTATION_HORIZONTAL = 2;

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({ALIGN_ORIENTATION_VERTICAL, ALIGN_ORIENTATION_HORIZONTAL})
  public @interface AlignOrientation {
  }

  private OnScrollListenerManagerOnItemTouchListener mOSLManager;
  private PositionTrackingOnScrollListener mOSL;

  /**
   * {@inheritDoc}
   *
   * @param context {@inheritDoc}
   */
  public AligningRecyclerView(final Context context) {
    super(context);
    init();
  }

  /**
   * {@inheritDoc}
   *
   * @param context {@inheritDoc}
   * @param attrs   {@inheritDoc}
   */
  public AligningRecyclerView(final Context context, final @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
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
    init();
  }

  private void init() {
    addOnItemTouchListener(mOSLManager = new OnScrollListenerManagerOnItemTouchListener());
    addOnScrollListener(mOSL = new PositionTrackingOnScrollListener());
  }

  /**
   * Binds this AligningRecyclerView to another AligningRecyclerView. This is an unidirectional
   * binding, meaning that calling this method implies that scrolling this AligningRecyclerView will cause {@code target} to scroll.
   * Calling this method does not modify the behavior of the symmetric binding, if any.
   *
   * @param target        AligningRecyclerView The target to bind to.
   * @param alignmentMode int The alignment mode to use for the binding.
   * @return The success of the operation. Usually the operation would fail if {@code target} is the
   * own object or the binding already exists.
   */
  public boolean bindTo(final @NonNull AligningRecyclerView target, final @AlignOrientation int alignmentMode) {
    return !isBoundTo(target, alignmentMode) && mOSLManager.createBinding(new Binding(this,
        target, alignmentMode));
  }

  /**
   * Unbinds this AligningRecyclerView from another AligningRecyclerView. This is an
   * unidirectional unbinding, meaning that calling this method implies that scrolling {@code
   * target} will no longer cause this AligningRecyclerView to scroll.
   * Calling this method does not modify the behavior of the symmetric binding, if any.
   *
   * @param target        AligningRecyclerView The target to unbind from.
   * @param alignmentMode int The alignment mode for the binging to be removed.
   * @return The success of the operation. Usually the operation would fail if {@code target} is the
   * own object or the binding does not already exists.
   */
  public boolean unbindFrom(final @NonNull AligningRecyclerView target, final @AlignOrientation
  int alignmentMode) {
    return isBoundTo(target, alignmentMode) && mOSLManager.destroyBinding(new Binding(this,
        target, alignmentMode));
  }

  /**
   * Verifies is this AligningRecyclerView is bound to the given AligningRecyclerView.
   *
   * @param target        AligningRecyclerView The target towards which the existence of the binding shall be verified.
   * @param alignmentMode int The alignment mode to check for.
   * @return {@code true} if there is a binding from this object towards {@code target};
   * {@code false} otherwise.
   */
  public boolean isBoundTo(final @NonNull AligningRecyclerView target, final @AlignOrientation int alignmentMode) {
    return mOSLManager.bindingExists(new Binding(this, target, alignmentMode));
  }

  PositionTrackingOnScrollListener getOSL() {
    return mOSL;
  }
}
