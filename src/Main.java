
public class Main {

    public static void main(String[] args) {
	// write your code here

        HashMap<Integer,Boolean> map=new HashMap<>();
        map.put(1,true);
        map.put(2,false);
        map.put(3,true);
        map.put(4,false);

        //Example of printing the values inside the map
        for(Integer k:map.keySet()){
            System.out.println(k +" "+map.get(k));
        }

    }
}
