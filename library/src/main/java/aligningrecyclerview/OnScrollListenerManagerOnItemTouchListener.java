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
public class OnScrollListenerManagerOnItemTouchListener implements RecyclerView.OnItemTouchListener {

  private final List<Binding> mScrollWatchers = new ArrayList<>();
  private int mLastX, mLastY;

  @Override
  public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
  MotionEvent e) {
    boolean ret = false;

    if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
      onTouchEvent(rv, e);
    }

    for (final Binding x : mScrollWatchers) {
      if (x.getFrom() == rv && x.getTo().getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
        ret = true;
        break;
      }
    }

    return ret;
  }

  @Override
  public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
    for (final Binding x : mScrollWatchers) {
      //noinspection ResourceType
      handleTouchEvent((AligningRecyclerView) rv, e, x.getTo(), x.getOrientation());
    }
  }

  private void handleTouchEvent(@NonNull final AligningRecyclerView from, @NonNull final
  MotionEvent e, @NonNull final AligningRecyclerView to, final @AligningRecyclerView
      .AlignOrientation int orientation) {
    final int action;
    final PositionTrackingOnScrollListener thisOSL = from.getOSL();

    if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && to
        .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
      mLastX = thisOSL.getScrolledX();
      mLastY = thisOSL.getScrolledY();
      from.addOnScrollListener(new RecyclerView.OnScrollListener() {

        private AligningRecyclerView mTo;
        private int mOrientation;

        private RecyclerView.OnScrollListener init(final @NonNull AligningRecyclerView to, final
        int orientation) {
          mTo = to;
          mOrientation = orientation;
          return this;
        }

        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
          super.onScrolled(recyclerView, dx, dy);
          mTo.scrollBy(mOrientation == AligningRecyclerView.ALIGN_ORIENTATION_VERTICAL ? 0 : dx, mOrientation == AligningRecyclerView.ALIGN_ORIENTATION_HORIZONTAL ? 0 : dy);
        }

        @Override
        public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
          super.onScrollStateChanged(recyclerView, newState);
          if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
          }
        }
      }.init(to, orientation));
    } else {
      final int scrolledX = thisOSL.getScrolledX(), scrolledY = thisOSL.getScrolledY();
      if (action == MotionEvent.ACTION_UP && (orientation == AligningRecyclerView
          .ALIGN_ORIENTATION_VERTICAL && mLastY == scrolledY || orientation == AligningRecyclerView.ALIGN_ORIENTATION_HORIZONTAL && mLastX == scrolledX || mLastY == scrolledY && mLastX == scrolledX)) {
        from.clearOnScrollListeners();
      }
    }
  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
  }

  boolean createBinding(final @NonNull Binding binding) {
    return mScrollWatchers.add(binding);
  }

  boolean destroyBinding(final @NonNull Binding binding) {
    return mScrollWatchers.remove(binding);
  }

  boolean bindingExists(final @NonNull Binding binding) {
    return mScrollWatchers.contains(binding);
  }
}
