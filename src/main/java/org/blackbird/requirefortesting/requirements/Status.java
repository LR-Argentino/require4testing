package org.blackbird.requirefortesting.requirements;

enum Status {
  OPEN,
  IN_PROGRESS,
  CLOSED;

  public boolean isOpen() {
    return this == OPEN;
  }

  public boolean isInProgress() {
    return this == IN_PROGRESS;
  }

  public boolean isClosed() {
    return this == CLOSED;
  }
}
