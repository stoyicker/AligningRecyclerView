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
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
final class OnScrollListenerManagerOnItemTouchListener implements RecyclerView.OnItemTouchListener {

  private final List<AligningRecyclerView> mScrollWatchers = new ArrayList<>();
  private int mLastY;

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
      handleTouchEvent(rv, e, x);
    }
  }

  private void handleTouchEvent(@NonNull final RecyclerView thisRecyclerView, @NonNull final
  MotionEvent e, @NonNull final AligningRecyclerView paired) {
    final int action;
    final SelfRemovingPositionTrackingOnScrollListener pairedOSL = paired.getOSL();

    if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && paired
        .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
      mLastY = pairedOSL.getScrollY();
      thisRecyclerView.addOnScrollListener(pairedOSL);
    }
    else {
      if (action == MotionEvent.ACTION_UP && pairedOSL.getScrollY() == mLastY && thisRecyclerView
          .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
        pairedOSL.postShouldRemoveItself(true);
        thisRecyclerView.removeOnScrollListener(pairedOSL);
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
