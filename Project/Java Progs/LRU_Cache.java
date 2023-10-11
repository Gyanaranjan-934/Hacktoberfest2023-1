class Node{
    int key;
    int val;
    Node next;
    Node prev;
    Node(int a,int b){
        this.key = a;
        this.val = b;
        this.next=null;
        this.prev=null;
    }
}
class LRUCache {
    Node head,tail;
    HashMap<Integer,Node> map;
    int cap;
    public LRUCache(int capacity) {
        map = new HashMap<Integer,Node>();
        this.cap = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))remove(map.get(key));
        if(map.size()==cap)remove(tail.prev);
        insert(new Node(key,value));
    }

    private void insert(Node node){
        map.put(node.key,node);
        node.next = head.next;
        head.next.prev = node;

        node.prev = head;
        head.next = node;
    }

    private void remove(Node node){
        map.remove(node.key);
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
