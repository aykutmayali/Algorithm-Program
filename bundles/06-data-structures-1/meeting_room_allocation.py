"""Utility functions for meeting room allocation problems."""

from __future__ import annotations

import heapq
from typing import Iterable, List, Tuple

Interval = Tuple[int, int]


def find_sets(intervals: Iterable[Interval]) -> int:
    """Return the minimum number of rooms needed for the given intervals.

    The function expects an iterable of ``(start, end)`` pairs where ``start``
    and ``end`` are comparable values (typically integers representing time).
    It computes how many meetings overlap at any moment, which corresponds to
    the number of rooms required to schedule them without conflicts.

    Args:
        intervals: Iterable with the start and end times of the meetings.

    Returns:
        The minimum number of rooms required to accommodate all meetings.
    """

    sorted_intervals: List[Interval] = sorted(intervals, key=lambda interval: interval[0])

    if not sorted_intervals:
        return 0

    # Keep the earliest finishing meeting on the top of the heap.
    room_end_times: List[int] = []

    for start, end in sorted_intervals:
        if room_end_times and room_end_times[0] <= start:
            heapq.heapreplace(room_end_times, end)
        else:
            heapq.heappush(room_end_times, end)

    return len(room_end_times)


if __name__ == "__main__":  # pragma: no cover - simple usage example
    example = [(2, 8), (3, 4), (8, 11)]
    print(find_sets(example))
