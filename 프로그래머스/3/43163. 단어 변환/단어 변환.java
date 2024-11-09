import java.util.*;

class Solution {
    public static boolean canGo(String str1, String str2) {
        int diff = 0;
        for (int i=0; i<str1.length(); i++) {
            if (str1.charAt(i)!=str2.charAt(i)) {
                diff++;
                if (diff>1) return false;
            }
        }
        if (diff==1) return true;
        return false;
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        return bfs(begin, target, words);
    }
    
    public static int bfs(String begin, String target, String[] words) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        for (int i=0; i<words.length; i++) {
            if (canGo(words[i], begin)) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        
        int count = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i=0; i<size; i++) {
                Integer top = queue.poll();
                if (words[top].equals(target)) return count;
                for (int j=0; j<words.length; j++) {
                    if (!visited[j] && canGo(words[j], words[top])) {
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
        }
        return 0;
    }
}