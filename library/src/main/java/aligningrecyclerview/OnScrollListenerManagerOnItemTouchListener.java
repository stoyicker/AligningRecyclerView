/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
final class OnScrollListenerManagerOnItemTouchListener implements RecyclerView.OnItemTouchListener {

  private final List<AligningRecyclerView> mScrollWatchers = new ArrayList<>();
  private int mLastX, mLastY;

  @Override
  public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
  MotionEvent e) {
    if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
      onTouchEvent(rv, e);
    }
    return Boolean.FALSE;
  }

  @Override
  public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
    for (final AligningRecyclerView x : mScrollWatchers) {
      handleTouchEvent((AligningRecyclerView) rv, e, x);
    }
  }

  private void handleTouchEvent(@NonNull final AligningRecyclerView thisRecyclerView, @NonNull final
  MotionEvent e, @NonNull final AligningRecyclerView paired) {
    final int action;
    final PositionTrackingOnScrollListener thisOSL = thisRecyclerView.getOSL();

    if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && paired
        .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
      Log.d("JORGETEST", "IF STATEMENT");
      mLastX = thisOSL.getScrolledX();
      mLastY = thisOSL.getScrolledY();
      thisRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

        private AligningRecyclerView mPaired;

        private RecyclerView.OnScrollListener init(final @NonNull AligningRecyclerView paired) {
          mPaired = paired;
          return this;
        }

        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
          super.onScrolled(recyclerView, dx, dy);
          final int orientation;
          mPaired.scrollBy((orientation = mPaired.getAlignOrientation()) == AligningRecyclerView.ALIGN_ORIENTATION_VERTICAL ? 0 : dx, orientation == AligningRecyclerView
              .ALIGN_ORIENTATION_HORIZONTAL ? 0 : dy);
        }

        @Override
        public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
          super.onScrollStateChanged(recyclerView, newState);
          if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
          }
        }
      }.init(paired));
    }
    else {
      Log.d("JORGETEST", "ELSE STATEMENT");
      final @AligningRecyclerView.AlignOrientation int orientation = thisRecyclerView
          .getAlignOrientation();
      final int scrolledX = thisOSL.getScrolledX(), scrolledY = thisOSL.getScrolledY();
      if (action == MotionEvent.ACTION_UP && (orientation == AligningRecyclerView
          .ALIGN_ORIENTATION_VERTICAL && mLastY == scrolledY || orientation == AligningRecyclerView.ALIGN_ORIENTATION_HORIZONTAL && mLastX == scrolledX || mLastY == scrolledY && mLastX == scrolledX)) {
        Log.d("JORGETEST", "ELSE -> IF STATEMENT");
        thisRecyclerView.clearOnScrollListeners(); //TODO Remove the concrete one only
      }
    }
  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
  }

  boolean bindTo(final @NonNull AligningRecyclerView target) {
    return mScrollWatchers.add(target);
  }

  boolean unbindFrom(final @NonNull AligningRecyclerView target) {
    return mScrollWatchers.remove(target);
  }

  boolean isBoundTo(final @NonNull AligningRecyclerView target) {
    return mScrollWatchers.contains(target);
  }
}
