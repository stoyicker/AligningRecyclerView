/*
 * AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano
 *
 * AligningRecyclerView is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * You should have received a copy of the license along with this work. If not, see http://creativecommons.org/licenses/by/4.0/.
 */

package aligningrecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class AligningRecyclerView extends RecyclerView {

  public AligningRecyclerView(final Context context) {
    super(context);
  }

  public AligningRecyclerView(final Context context, final @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public AligningRecyclerView(final Context context, final @Nullable AttributeSet attrs, final int defStyle) {
    super(context, attrs, defStyle);
  }
}
