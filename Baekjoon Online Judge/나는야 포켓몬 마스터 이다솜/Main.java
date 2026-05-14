import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] strs = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);

        Map<Integer, String> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for(int i=1; i<=n; i++){
            String pokemon = br.readLine();
            map1.put(i, pokemon);
            map2.put(pokemon, i);
        }
        for(int i=0; i<m; i++){
            String s = br.readLine();
            if(49<=s.charAt(0) && s.charAt(0) <= 57){ //char 타입으로 문자인지 체크
                int key = Integer.parseInt(s);
                sb.append(map1.get(key));
            }else{
                sb.append(map2.get(s));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}