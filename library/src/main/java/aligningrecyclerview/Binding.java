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

  @AligningRecyclerView.AlignOrientation
  public int getOrientation() {
    return mOrientation;
  }

  public AligningRecyclerView getTo() {
    return mTo;
  }

  public AligningRecyclerView getFrom() {
    return mFrom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Binding binding = (Binding) o;

    return mOrientation == binding.mOrientation && mFrom.equals(binding.mFrom) && mTo.equals(binding.mTo);

  }

  @Override
  public int hashCode() {
    int result = mFrom.hashCode();
    result = 31 * result + mTo.hashCode();
    result = 31 * result + mOrientation;
    return result;
  }
}
