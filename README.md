#Design and Analysis of Algorithms Assignment 1

This project implements several divide-and-conquer algorithms with classes for collecting metrics like: time, recursion depths, comparisons and assignments.


Implemented Algorithms

- Merge Sort - O(n log n) sort with reusable buffer
- Quick Sort - randomized pivot, worst-case O(n^2)
- Closest Pair of Points - divide-and-conquer algorithm, O(n log n)
- Deterministic select - divide-and-conquer algorithm, O(n)


Metrics & Instrumentation

The project includes metrics:
-OperationCounter - counts comparisons and assignments
-Recursion Tracker - tracks recurshion depth
-Logger - writes results to CSV for later analysis

Each algorithm test writes results to 'metrics.csv'

Algorithm verification:

Each algorithm was analyzed and compared to theoretical expectations.
Master theorem:
T(n) = aT(n/b) + f(n),
where:
n - size
a - number of subproblems
b - size reduction
f(n) - work outside recursion

For Merge Sort = 
T(n) = 2T(n/2) + O(n) = Θ(nlogn)

For Quick Sort = 

T(n) = 2T(n/2) + O(n) = Θ(nlogn)

For Closest pair = 

T(n) = 2T(n/2) + O(n) = Θ(nlogn)

For Deterministic Select = 

T(n) = T(n/5) + T(7n/10) + O(n)

by Akra-Bazzi theorem : 

(1/5)p + (7/10)p = 1

T(n) = Θ(n)
a
