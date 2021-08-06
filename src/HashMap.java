import java.util.*;

public class HashMap<K,V> {

    public class HMNode{
        K key;
        V value;
        public HMNode(K key,V value){
            this.key=key;
            this.value=value;
        }
    }

    private int size;
    private LinkedList<HMNode>[] buckets;

    public HashMap(){
        size=0;
        initbuckets(4);
    }

    private void initbuckets(int n){
        buckets=new LinkedList[n];
        for(int i=0;i<n;i++){
            buckets[i]=new LinkedList<>();
        }
    }

    public void put(K key,V value){
        int bi=hashfn(key);
        int di=getIndexInBucket(key,bi);
        if(di!=-1){
            //if value already present update it
            HMNode node=buckets[bi].get(di);
            node.value=value;
        }
        else{
            //insert a the node as its not present
            buckets[bi].add(new HMNode(key,value));
            size++;
        }
        //calculating average nodes in a buckets
        //if above a specific threshold, then we apply rehashing
        double lambda=size*1.0 / buckets.length;
        if(lambda>2.0){
            rehash();
        }

    }

    private int hashfn(K key){
        int hc=key.hashCode();
        int abshc=Math.abs(hc);
        return abshc%buckets.length;
    }

    private int getIndexInBucket(K key,int bi){
        int di=0;
        for(HMNode node:buckets[bi]){

            if(node.key.equals(key)){
                return di;
            }
            di++;
        }
        return -1;
    }

    public boolean containsKey(K key){
        int bi=hashfn(key);
        int di=getIndexInBucket(key,bi);
        if(di==-1){
            return false;
        }
        return true;
    }

    public V get(K key){
        int bi=hashfn(key);
        int di=getIndexInBucket(key,bi);
        if(di!=-1){
            return buckets[bi].get(di).value;
        }
        return null;
    }

    public V remove(K key){
        int bi=hashfn(key);
        int di=getIndexInBucket(key,bi);
        if(di==-1){
            return null;
        }

        size--;
        return buckets[bi].remove(di).value;

    }

    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<>();
        for(int i=0;i<buckets.length;i++){
            for(HMNode node:buckets[i]){
                keys.add(node.key);
            }
        }
        return keys;
    }

    private void rehash(){
        //create a new buckets array of double the size so that lambda remains the same
        LinkedList<HMNode>[] oba=buckets;
        initbuckets(oba.length * 2);
        size=0;

        for(int i=0;i<oba.length;i++){
            for(HMNode node:oba[i]){
                put(node.key,node.value);
            }
        }
    }







}
