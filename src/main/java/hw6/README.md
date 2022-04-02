# Discussion

## Unit testing TreapMap
- Since Treap internally generates random priorities for its nodes, it's generally hard to test on reproducible data. Therefore, a seed is used in generating random priorities to ensure both randomness of data and consistence of result.

- In test `insertLeftRotation()`, generated nodes are `2:2:-1727040520`, `3:3:-1657178909` and `1:1:-1160871061`.

   1      insert(2)       1       leftRotation        2       insert(3)         2
         ---------->       \      ----------->       /       ---------->       / \     
                            2                       1                         1   3

After inserting 2 to the right of 1 based on BST rules, observe that 2's priority is higher than 1's, so a leftRotation is performed.

- In test `removeOneChildRightRotation()`, generated nodes are `3:3:-1704868423`, `2:2:-1150867590`, `1:1:884779003`.
       
       3                           3                           3
      /        remove(2)          /         remove(2)         /
     2        ---------->        1         ---------->       1
    /        rightRotation        \
   1                               2

To remove 2 that has one child, first perform a rightRotation to make 2 a leaf, and then 2 can be directly removed.

## Benchmarking
- Using `moby_dick.txt`

| Benchmark                 | Mode | Cnt | Score    | Error | Units |
|---------------------------|------|-----|----------|-------|-------|
| JmhRuntimeTest.arrayMap   | avgt | 2   | 3537.487 |       | ms/op |
| JmhRuntimeTest.avlTreeMap | avgt | 2   | 181.362  |       | ms/op |
| JmhRuntimeTest.bstMap     | avgt | 2   | 185.616  |       | ms/op |
| JmhRuntimeTest.treapMap   | avgt | 2   | 214.963  |       | ms/op | 

- Using `pride_and_prejudice.txt`

| Benchmark                 | Mode | Cnt | Score   | Error | Units |
|---------------------------|------|-----|---------|-------|-------|
| JmhRuntimeTest.arrayMap   | avgt | 2   | 784.533 |       | ms/op |
| JmhRuntimeTest.avlTreeMap | avgt | 2   | 98.964  |       | ms/op |
| JmhRuntimeTest.bstMap     | avgt | 2   | 111.035 |       | ms/op |
| JmhRuntimeTest.treapMap   | avgt | 2   | 116.274 |       | ms/op |

