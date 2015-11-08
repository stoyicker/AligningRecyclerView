/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * @author Jorge Antonio Diaz-Benito Soriano (github.com/Stoyicker).
 */
final class PositionTrackingOnScrollListener extends RecyclerView.OnScrollListener {

  private int mScrolledX, mScrolledY;

  public int getScrolledX() {
    return mScrolledX;
  }

  public int getScrolledY() {
    return mScrolledY;
  }

  @Override
  public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
    mScrolledX += dx;
    mScrolledY += dy;
    super.onScrolled(recyclerView, dx, dy);
  }
}
