class Node {  //每个节点中存储的是键值对
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

class DoubleList {  
    private Node head, tail; // 头尾虚节点
    private int size; // 链表元素数

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表头部添加节点 x
    public void addFirst(Node x) {
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    public void remove(Node x) {   //往HashMap添加数据的时候，如果已经存在，先删除，然后再把数据添加到链表头部
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }
    
    // 删除链表中最后一个节点，并返回该节点
    public Node removeLast() {  //当链表容量已满，删除链表中最后一个节点
        if (tail.prev == head)
            return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }
    
    // 返回链表长度
    public int size() { return size; }
}

/*为什么必须要用双向链表?因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)

*/
class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;
    
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }
    
    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);
        
        if (map.containsKey(key)) {  //如果这个节点在缓存中存在
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key)); 
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
                /*当缓存容量已满，我们不仅仅要删除缓存中最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。如果 Node 结构中只存储 val，
                那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误*/
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }
}


//o(1)时间内插入和删除，所以可以用链表（LinkedList,不是ArrayList,ArrayList是长度可变的数组），插入删除快，但是查找慢。。。查找也要快，哈希查的快，数据无固定顺序，链表有顺序；
//结合一下构成新的数据结构：哈希链表
//LRU机制是淘汰最近不常用的缓存淘汰，所以得有顺序之分，以区分常用的和许久未使用的数据，而且我们要在 cache 中查找键是否已存在；如果容量满了要删除最后一个数据；每次访问还要把数据插入到队头。

 /* Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
