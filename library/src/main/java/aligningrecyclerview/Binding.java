package aligningrecyclerview;

import android.support.annotation.NonNull;

class Binding {

  private final AligningRecyclerView mFrom, mTo;

  private final
  @AligningRecyclerView.AlignOrientation
  int mOrientation;

  Binding(final @NonNull AligningRecyclerView from, final @NonNull AligningRecyclerView to,
          final @AligningRecyclerView.AlignOrientation int orientation) {
    mFrom = from;
    mTo = to;
    mOrientation = orientation;
  }

  int getOrientation() {
    return mOrientation;
  }

  AligningRecyclerView getTo() {
    return mTo;
  }

  AligningRecyclerView getFrom() {
    return mFrom;
  }

}
