# 二分搜索树
## 删除节点
1. 删除只有左孩子的节点  --直接删
2. 删除只有右孩子的节点  --直接删
3. 左右都有孩子的节点  --hibbard deletion  
    1. 找到右子树最小的节点
    2. 用这个节点替换要删除的节点
    
    
# AVL树

## 添加节点
- LL
- RR
- LR
- RL

## 删除节点
先按照bst删除规则， 再进行平衡
注意bst删除时deleteMin并没有维护树的平衡