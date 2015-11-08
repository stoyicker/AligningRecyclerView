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

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
final class SelfRemovingPositionTrackingOnScrollListener extends RecyclerView.OnScrollListener {

  private boolean isShouldRemoveItself = false;

  public int getScrollX() {
    return mScrollX;
  }

  public int getScrollY() {
    return mScrollY;
  }

  private int mScrollX, mScrollY;

  @Override
  public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
    mScrollX += dx;
    mScrollY += dy;
    Log.d("JORGETEST", "Scrolling information updated to " + String.format("(%d, %d)",
        mScrollX, mScrollY));
    super.onScrolled(recyclerView, dx, dy);
  }

  @Override
  public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    Log.d("JORGETEST", "OnScrollStateChanged called");
    if (newState == RecyclerView.SCROLL_STATE_IDLE && isShouldRemoveItself) {
      Log.d("JORGETEST", "onScrollListener removed");
      isShouldRemoveItself = false;
      recyclerView.removeOnScrollListener(this);
    }
  }

  public void postShouldRemoveItself(final boolean flag) {
    isShouldRemoveItself = flag;
  }
}
